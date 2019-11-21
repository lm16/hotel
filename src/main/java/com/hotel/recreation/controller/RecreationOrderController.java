package com.hotel.recreation.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hotel.bean.Result;
import com.hotel.bean.ResultType;
import com.hotel.category.bean.OrderForm;
import com.hotel.category.config.AlipayConfig;
import com.hotel.client.service.CardService;
import com.hotel.recreation.bean.Recreation;
import com.hotel.recreation.bean.RecreationOrder;
import com.hotel.recreation.mapper.RecreationMapper;
import com.hotel.recreation.mapper.RecreationOrderMapper;
import com.hotel.recreation.service.RecreationOrderService;
import com.hotel.recreation.service.RecreationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "娱乐项目订单接口")
@Controller
@RequiresAuthentication
public class RecreationOrderController {

    @Autowired
    private RecreationOrderService recreationOrderService;

    @Autowired
    private RecreationOrderMapper recreationOrderMapper;

    @Autowired
    private CardService cardService;

    /** @ApiOperation：用在请求的方法上，说明方法的用途、作用 value="说明方法的用途、作用"
     * notes="方法的备注说明"
     * @ApiImplicitParams：用在请求的方法上，表示一组参数说明
     * @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面 name：参数名
     * value：参数的汉字说明、解释
     * required：参数是否必须传
     * paramType：参数放在哪个地方
     * · header --> 请求参数的获取：@RequestHeader
     * · query --> 请求参数的获取：@RequestParam
     * · path（用于restful接口）--> 请求参数的获取：@PathVariable
     * · body（不常用）
     * · form（不常用）
     * dataType：参数类型，默认String，其它值dataType="Integer"
     * defaultValue：参数的默认值
     * @ApiResponses：用在请求的方法上，表示一组响应
     * @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息 code：数字，例如400
     * message：信息，例如"请求参数没填好"
     * response：抛出异常的类
     * @ApiModel：用于响应类上，表示一个返回响应数据的信息 （这种一般用在post创建的时候，使用@RequestBody这样的场景，
     * 请求参数无法使用@ApiImplicitParam注解进行描述的时候）
     * @ApiModelProperty：用在属性上，描述响应类的属性
 */

//    @RequestMapping("/hello")
//    public String Test1(){
//        Recreation recreation = recreationService.findRecreationById(1);
//        System.out.println(recreation.toString());
//        return "success";
//    }


    @RequiresPermissions(value = { "*:add","*:modify" }, logical = Logical.OR)
    @ApiOperation(value = "新增订单与修改订单", notes = "传入一个实体类，有对应id就修改，无对应id就新增")
    @ApiImplicitParam(name = "recreationOrder", value = "订单实体类", required = false, dataType = "RecreationOrder")
    @PostMapping("/RecreationOrder/saveRecreationOrder")
    @ResponseBody
    public Result saveRecreationOrder(@RequestBody(required = false) RecreationOrder recreationOrder){
        RecreationOrder ro = recreationOrderMapper.save(recreationOrder);
        if (ro!=null){
            return Result.build(ResultType.Success).appendData("data",ro);
        }
        return Result.build(ResultType.Failed);
    }

    @RequiresPermissions(value = { "*:find" }, logical = Logical.OR)
    @ApiOperation(value = "查询所有娱乐项目订单", notes = "")
    @GetMapping("/RecreationOrder/findAllRecreationOrder")
    @ResponseBody
    public Result findAllRecreationOrder(HttpServletResponse response){
        List<RecreationOrder> recreationOrders = recreationOrderMapper.findAll();
        if (recreationOrders!=null){
            return Result.build(ResultType.Success).appendData("data",recreationOrders);
        }
        return Result.build(ResultType.Failed);
    }

    @RequiresPermissions(value = { "*:delete" }, logical = Logical.OR)
    @ApiOperation(value = "删除娱乐订单", notes = "根据订单id删除图片")
    @ApiImplicitParam(name = "reoId", value = "订单id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("/RecreationOrder/deleteRecreationOrder/{reoId}")
    @ResponseBody
    public Result deleteRecreationOrder(@PathVariable Long reoId){
        recreationOrderMapper.deleteById(reoId);
        return Result.build(ResultType.Success,"操作成功");
    }




    @RequiresPermissions(value = { "*:delete" }, logical = Logical.OR)
    @ApiOperation(value = "删除娱乐订单", notes = "根据订单id删除图片")
    @ApiImplicitParam(name = "reoId", value = "订单id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("/RecreationOrder/alipay")
    @ResponseBody
    public Result alipay(@PathVariable Long reoId){
        recreationOrderMapper.deleteById(reoId);
        return Result.build(ResultType.Success,"操作成功");
    }

    @ApiOperation("支付")
    @GetMapping("/RecreationOrder/pay")
    public void finishOrderForms(@RequestBody RecreationOrder recreationOrder,HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        session.setAttribute("recreationOrder",recreationOrder);

        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url3);

        AlipayTradePayModel model = new AlipayTradePayModel();
        model.setSubject(recreationOrder.getId().toString());
        model.setOutTradeNo(recreationOrder.getId().toString());
        model.setTotalAmount(String.valueOf(recreationOrder.getTprice()));
        model.setProductCode("FAST_INSTANT_TRADE_PAY");

        alipayRequest.setBizModel(model);

        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.sign_type);
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
        response.getWriter().write(form);
        response.getWriter().flush();
        response.getWriter().close();
    }

    @RequestMapping("/RecreationOrder/alipay")
    @ResponseBody
    public void returnUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {


        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();

        for (String key : requestParams.keySet()) {
            String[] values = requestParams.get(key);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(key, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.sign_type); // 调用SDK验证签名
        // 返回界面
        if (signVerified) {

            //插入数据库
            HttpSession session = request.getSession();

            RecreationOrder recreationOrder = (RecreationOrder) session.getAttribute("recreationOrder");
            //会员卡不为空
//            if (recreationOrder.getClientId() != null && recreationOrder.getFoCardNumber() != "") {
//                //消费积分
//                Double integral = recreationOrder.getFoMonetary() / 10;
//                //添加客户消费积分
//                boolean flag1 = cardService.updateintegral(integral, orderForm.getFoCardNumber());
//                if (flag1) {
//                    System.out.println("flag1=" + flag1);
//                }
//
//            }
            RecreationOrder i =  recreationOrderMapper.save(recreationOrder);
            if (i==null||i.equals("")){
                System.out.println("新增订单失败");
            }
//        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.sign_type);

            response.sendRedirect("http://localhost:8080/#/entertainmentManagement/makeOrder");
        }
    }





}
