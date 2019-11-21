package com.hotel.booking.server.impl;

import com.hotel.bean.Result;
import com.hotel.bean.ResultType;
import com.hotel.booking.bean.Booking;
import com.hotel.booking.bean.RoomType;
import com.hotel.booking.mapper.BookingFinalMapper;
import com.hotel.booking.mapper.BookingMapper;
import com.hotel.booking.mapper.RoomTypeMapper;
import com.hotel.booking.server.Aa;
import com.hotel.booking.server.BookingServer;
import com.hotel.client.service.ClientServiceImpl.CardServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static jdk.nashorn.internal.objects.Global.println;

/**
 * @Classname BookingServerImpl
 * @Description TODO
 * @Date 19-10-21 下午4:58
 * @Created by lanmeng
 */
@Service
public class BookingServerImpl implements BookingServer {

    @Autowired
    private BookingMapper bookingMapper;
    @Autowired
    private BookingFinalMapper bookingFinalMapper;
    @Autowired
    private RoomTypeMapper roomTypeMapper;
    @Autowired
    private CardServiceImpl cardService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Booking create(@NotNull Booking booking) throws Exception{

        //灵活放置位置，从方法换到另一个方法，从方法中换到方法上
        List<LocalDate> list = Aa.INSTANCE.deadline(booking.getStart(), booking.getEnd());

        BigDecimal deposit = roomTypeMapper.getCost(booking.getRoomTypeId()).multiply(new BigDecimal(list.size()));

        booking.setDeposit(deposit);


        if(booking.getMemberCardnum()!=null){
            double discount = cardService.findcount(booking.getMemberCardnum()).getI_discount();
            booking.setMemberDeposit(deposit.multiply(new BigDecimal(Double.toString(discount))));
        }

        //订单交付，为未支付
        bookingMapper.create(booking);



        for(LocalDate item: list){

            booking.setStart(item);

            if(bookingFinalMapper.getCount(booking) == 0){
                throw new Exception("订房已满");
            }else{
                bookingFinalMapper.pay(booking);
            }
        }

        return bookingMapper.getOne(booking.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result pay(int id)throws Exception {

        if(bookingMapper.pay(id)==0){
            return Result.build(ResultType.Failed).appendData("tip", "您的订单状态为: "+bookingMapper.getStatus(id));
        }


        return Result.build(ResultType.Success).appendData("tip", "支付成功");
    }

    @Override
    public void timeout(@NotNull String time) {

        int n = bookingMapper.unpay(time);

        // 一秒一卡真潇洒
        if(n == 1){
            Booking booking = bookingMapper.getByBooking(time);

            // 复位
            List<LocalDate> list =Aa.INSTANCE.deadline(booking.getStart(), booking.getEnd());

            for(LocalDate item: list){

                booking.setStart(item);

                bookingFinalMapper.restore(booking);
            }
        }
    }

    @NotNull
    @Override
    public String money(int id) {

        Booking booking = bookingMapper.getOne(id);

        if(booking.getMemberDeposit() == null){
            return booking.getDeposit().toString();
        }else{
            return booking.getMemberDeposit().toString();
        }

    }
}
