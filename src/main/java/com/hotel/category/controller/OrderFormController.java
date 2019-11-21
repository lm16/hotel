package com.hotel.category.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers;
import com.hotel.bean.Result;
import com.hotel.bean.ResultType;

import com.hotel.category.bean.OrderForm;
import com.hotel.category.bean.OrderFormDetail;
import com.hotel.category.config.AlipayConfig;
import com.hotel.category.service.OrderFormDetailService;
import com.hotel.category.service.OrderFormService;
import com.hotel.category.service.UploadInformationPictureService;

import com.hotel.client.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 林晓锋
 * @date 2019/10/18
 * modified: 2019/10/20
 * 功能：菜品订单管理接口
 */
@Api(tags = "菜品订单管理接口")
@RequestMapping(value = "/json/orderForm")
@Controller
public class OrderFormController {

    @Autowired
    private OrderFormDetailService orderFormDetailService;

    @Autowired
    private UploadInformationPictureService uploadInformationPictureService;

    @Autowired
    private OrderFormService orderFormService;

    @Autowired
    private CardService cardService;

    @RequiresPermissions(value = { "*:add" }, logical = Logical.OR)
    @ApiOperation(value = "生成订单",notes = "已测试")
    @RequestMapping(value = "/insertOrderForm",method = RequestMethod.POST)
    @ResponseBody
    public Result insertOrderForm(HttpServletRequest request,
            @ApiParam(name = "foOrderName",value = "商品名称",required = true)
            @RequestParam(name = "foOrderName")String foOrderName,
            @ApiParam(name = "fd_id",value = "餐桌id",required = true)
            @RequestParam(name = "fd_id")Long foDeskId,
            @ApiParam(name = "foEmployeeId",value = "经办人",required = false)
            @RequestParam(name = "foEmployeeId") Long foEmployeeId,
            @ApiParam(name = "foMonetary",value = "消费金额",required = false)
            @RequestParam(name = "foMonetary") Double foMonetary,
            @ApiParam(name = "foDescription",value = "备注",required = false)
            @RequestParam(name = "foDescription" ,required = false) String foDescription) {

        HttpSession session = request.getSession();
        //取得购物车商品
        List<HashMap> cartMap = (List<HashMap>)session.getAttribute("cartMap");

        //生成订单
        OrderForm orderForm = new OrderForm();
        //生成唯一订单号
        orderForm.setFoOutTradeNo(orderFormService.getUniqueOutTradeNo());
        orderForm.setFoOrderName(foOrderName);
        orderForm.setFoDeskId(foDeskId);
        orderForm.setFoEmployeeId(foEmployeeId);
        orderForm.setFoMonetary(foMonetary);
        orderForm.setFoDescription(foDescription);
        boolean flag1 = orderFormService.insertOrderForm(orderForm);
        //获得订单的自增id
        Long foId = orderForm.getFoId();

        //遍历购物车
        OrderFormDetail orderFormDetail = new OrderFormDetail();

        for(HashMap hashMap : cartMap) {

            //订单id
            orderFormDetail.setFodOrderformId(foId);
            //取得菜品id
            orderFormDetail.setFodInformationId((Long)hashMap.get("fi_id"));
            //取得菜品份量
            orderFormDetail.setFodNumber((Integer)hashMap.get("num"));
            //生成订单菜品表
            boolean flag = orderFormDetailService.insertOrderFormInformation(orderFormDetail);
            System.out.println("flag = " + flag);
        }
        //生成订单后清空购物车
        session.removeAttribute("cartMap");

        return  Result.build(ResultType.Success).appendData("data","添加订单成功");

    }

    @RequiresPermissions(value = { "*:find" }, logical = Logical.OR)
    @ApiOperation(value = "查询订单",notes = "已测试")
    @RequestMapping(value = "/selectOrderForm",method = RequestMethod.GET)
    @ResponseBody
    public Result selectOrderForm(
            @ApiParam(name = "foStatus",value = "订单状态",required = true)
            @RequestParam(value = "foStatus") Integer foStatus,
            @ApiParam(name = "startTime",value = "开始时间",required = false)
            @RequestParam(value = "startTime",required = false) String startTime,
            @ApiParam(name = "endTime",value = "结束时间",required = false)
            @RequestParam(value = "endTime",required = false) String endTime) throws ParseException {

        Date startDate = null;
        Date endDate = null;
        if((startTime!=""&&startTime!=null)&&(endTime!="")&&endTime!=null) {
            //String 类型转为date类型
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            startDate = format.parse(startTime);
            endDate = format.parse(endTime);
        }

        List<HashMap> orderFormList = orderFormService.selectOrderForm(foStatus,startDate,endDate);

        return Result.build(ResultType.Success).appendData("data",orderFormList);

    }

    @RequiresPermissions(value = { "*:find" }, logical = Logical.OR)
    @ApiOperation(value = "查询订单菜品",notes = "已测试")
    @RequestMapping(value = "/selectOrderFormDetail",method = RequestMethod.GET)
    @ResponseBody
    public Result selectOrderFormDetail(HttpServletRequest request,
            @RequestParam(name = "foId") Long foId) throws UnknownHostException {

        //返回订单菜品信息
        List<HashMap> orderFormDetailList = orderFormDetailService.selectOrderFormDetail(foId);
        //对菜品图片路径进行包装
        List<HashMap> orderFormDetailLists = uploadInformationPictureService.transformUrl(request,orderFormDetailList);

        return Result.build(ResultType.Success).appendData("data",orderFormDetailLists);

    }

    @RequiresPermissions(value = { "*:modify" }, logical = Logical.OR)
    @ApiOperation(value = "取消订单",notes = "已测试")
    @RequestMapping(value = "/cancelOrderForm",method = RequestMethod.PUT)
    @ResponseBody
    public Result cancelOrderForm(
            @ApiParam(name = "foId",value = "订单id",required = true)
            @RequestParam(name = "foId") Long foId) {

        boolean flag = orderFormService.cancelOrderForm(foId);

        return Result.build(ResultType.Success).appendData("data",flag);

    }

    @RequiresPermissions(value = { "*:modify" }, logical = Logical.OR)
    @ApiOperation(value = "完成订单",notes = "正在测试")
    @RequestMapping(value = "/finishOrderForm",method = RequestMethod.POST)
    @ResponseBody
    public Result finishOrderForms(HttpServletRequest request, HttpServletResponse response,
                                   @RequestBody OrderForm orderForm) throws IOException {


        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
//        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        AlipayTradePayModel model = new AlipayTradePayModel();
        //商品描述，可空
        model.setBody(orderForm.getFoDescription());
        //订单名称，必填
        model.setSubject(orderForm.getFoOrderName());
        //商户订单号，商户网站订单系统中唯一订单号，必填
        model.setOutTradeNo(orderForm.getFoOutTradeNo());
//        //付款金额，必填
        String total_amount = orderForm.getFoMonetary().toString();
        model.setTotalAmount(total_amount);
//        // 设置销售产品码，必填
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        alipayRequest.setBizModel(model);

//        boolean flag = orderFormService.finishOrderForm(orderForm);
        //请求
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        session.setAttribute("orderForm",orderForm);

        response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
        //System.out.println("form="+form);
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();

        return Result.build(ResultType.Success).appendData("data","支付成功");

    }

    /**
     * 同步跳转
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping("/returnUrl")
    @ResponseBody
    public void returnUrl(HttpServletRequest request,HttpServletResponse response) throws Exception {

        // 获取支付宝GET过来反馈信息（官方固定代码）
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        System.out.println("params=" + params);
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.sign_type); // 调用SDK验证签名
        // 返回界面
        if (signVerified) {

            //插入数据库
            HttpSession session = request.getSession();

            OrderForm orderForm = (OrderForm)session.getAttribute("orderForm");
            //会员卡不为空
            if(orderForm.getFoCardNumber()!=null&&orderForm.getFoCardNumber()!="") {
                //消费积分
                Double integral = orderForm.getFoMonetary()/10;
                //添加客户消费积分
                boolean flag1 = cardService.updateintegral(integral,orderForm.getFoCardNumber());
                if(flag1) {
                    System.out.println("flag1="+flag1);
                }

            }

            boolean flag = orderFormService.finishOrderForm(orderForm);
            System.out.println("前往支付成功页面");
//            String out_trade_no = request.getParameter("out_trade_no");
//            System.out.println("trade_status"+out_trade_no );
            response.sendRedirect("http://localhost:8080/yueliyue#/home/cateringManagement/completedOrder");

        }

    }

    /**
     * 支付宝服务器异步通知
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping("/notifyUrl")
    @ResponseBody
    public void notifyUrl(HttpServletRequest request) throws Exception {
        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        // 调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.sign_type);
        if (signVerified) { // 验证成功 更新订单信息
            System.out.println("异步通知成功");
            // 商户订单号
            String out_trade_no = request.getParameter("out_trade_no");
            // 交易状态
            String trade_status = request.getParameter("trade_status");
            // 修改数据库
        } else {
            System.out.println("异步通知失败");
        }

    }

}
