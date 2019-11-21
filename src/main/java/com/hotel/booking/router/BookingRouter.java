package com.hotel.booking.router;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.hotel.bean.Result;
import com.hotel.bean.ResultType;
import com.hotel.booking.bean.Booking;
import com.hotel.booking.bean.form.BookingForm;
import com.hotel.booking.server.Aa;
import com.hotel.booking.server.RoomTimer;
import com.hotel.category.bean.OrderForm;
import com.hotel.category.config.AlipayConfig;
import com.hotel.client.dao.Client;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/*
 * @author lm16
 */

@RestController
@Api(tags="订房业务接口")
@RequestMapping("/booking")
public class BookingRouter extends BookingObject{

    @Autowired
    private RoomTimer roomTimer;

//    @RequiresPermissions(value = { "*:find" }, logical = Logical.OR)
    @ApiOperation("显示")
    @GetMapping
    public Result get(){

//        Map<String, LocalDate> map = new HashMap<>();
//        map.put("start", LocalDate.now());
//        map.put("end", LocalDate.now());

        return Result.build(ResultType.Success).appendData("data", roomTypeMapper.getAll());
    }

//    @RequiresPermissions(value = { "*:find" }, logical = Logical.OR)
    @ApiOperation("查询")
    @GetMapping("/search")
    public Result search(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                         @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end){

        if(!start.isBefore(end)){
            return Result.build(ResultType.Failed).appendData("tip", "两个日期这样搞？");
        }

        Map<String, LocalDate> map = new HashMap<>();
        map.put("start", start);
        map.put("end", end.minusDays(1));

        return Result.build(ResultType.Success).appendData("data", bookingFinalMapper.getByDate(map));
    }

//    @ApiOperation("时间内的房间预订数量")
//    @PostMapping("/dayRoom")
//    public Result post(@RequestBody Map<String, BookingObject> map){
//        return Result.build(ResultType.Success).appendData("list", bookingMapper.getDateBookingCount(map));
//    }


//    @ApiOperation("开房")
//    @GetMapping("/arrange")
//    public Result get(){
//        return Result.build(ResultType.Success).appendData("data", bookingMapper.getDateBookingCount(map));
//    }

//    @RequiresPermissions(value = { "*:add" }, logical = Logical.OR)
    @ApiOperation("提交")
    @PostMapping
    public Result post(@RequestBody BookingForm bookingForm)throws Exception{

        if(bookingForm.getStart().isBefore(LocalDate.now()) || !bookingForm.getStart().isBefore(bookingForm.getEnd())){
            return Result.build(ResultType.Failed);
        }

        Client client = new Client();
        client.setC_name(bookingForm.getName());
        client.setC_phone(bookingForm.getPhone());
        client.setC_sex(bookingForm.getSex());
        clientService.ClientSave(client);

        Booking booking = new Booking();
        BeanUtils.copyProperties(bookingForm, booking);

        Booking result = bookingServer.create(booking);
        roomTimer.start(Aa.INSTANCE.getCron(result.getBooking().plusMinutes(5L)));

        return Result.build(ResultType.Success).appendData("data", result);
    }

//    @GetMapping("取房")
//    @GetMapping("/livein")
//    public Result get(@RequestParam("name") String name){
//        return Result.build(ResultType.Success).appendData("data", liveinMapper.getName())
//    }

    @ApiOperation("支付")
    @GetMapping("/pay")
    public void finishOrderForms(@RequestParam("id") Integer id, HttpServletResponse response) throws Exception {





        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url2);

        AlipayTradePayModel model = new AlipayTradePayModel();
        model.setSubject("name");
        model.setOutTradeNo(id.toString());
        model.setTotalAmount(bookingServer.money(id));
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

    @RequestMapping("/alipay")
    @ResponseBody
    public void returnUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {


        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();

        for (String key: requestParams.keySet()) {
            String[] values = requestParams.get(key);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(key, valueStr);
        }

        bookingServer.pay(Integer.parseInt(params.get("out_trade_no")));


        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.sign_type);

//
//        // 返回界面
//        if (signVerified) {
//
//            //插入数据库
//            HttpSession session = request.getSession();
//
//            OrderForm orderForm = (OrderForm)session.getAttribute("orderForm");
//            //会员卡不为空
//            if(orderForm.getFoCardNumber()!=null&&orderForm.getFoCardNumber()!="") {
//                //消费积分
//                Double integral = orderForm.getFoMonetary()/10;
//                //添加客户消费积分
//                boolean flag1 = cardService.updateintegral(integral,orderForm.getFoCardNumber());
//                if(flag1) {
//                    System.out.println("flag1="+flag1);
//                }
//
//            }
//
//            boolean flag = orderFormService.finishOrderForm(orderForm);
//            System.out.println("前往支付成功页面");
//            String out_trade_no = request.getParameter("out_trade_no");
//            System.out.println("trade_status"+out_trade_no );
//            response.sendRedirect("http://10.20.5.12:8080/yueliyue#/home/cateringManagement/completedOrder");
            response.sendRedirect("http://localhost:8080/#/orderingMeal2");
        }

}

