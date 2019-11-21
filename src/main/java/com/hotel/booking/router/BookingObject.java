package com.hotel.booking.router;

import com.hotel.booking.mapper.*;
import com.hotel.booking.server.BookingServer;
import com.hotel.booking.server.LiveinServer;
import com.hotel.booking.server.RoomInfoServer;
import com.hotel.booking.server.RoomTypeServer;
import com.hotel.client.service.CardService;
import com.hotel.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;



public class BookingObject {

    @Autowired
    protected BookingMapper bookingMapper;

    @Autowired
    protected BookingFinalMapper bookingFinalMapper;

    @Autowired
    protected RoomMapper roomMapper;

    @Autowired
    protected RoomTypeMapper roomTypeMapper;

    @Autowired
    protected LiveinMapper liveinMapper;

    @Autowired
    protected BookingServer bookingServer;

    @Autowired
    protected CardService cardService;

    @Autowired
    protected RoomInfoServer roomServer;

    @Autowired
    protected  RoomTypeMapperJava roomTypeMapperJava;

    @Autowired
    protected LiveinServer liveinServer;

    @Autowired
    protected RoomTypeServer roomTypeServer;

    @Autowired
    protected ClientService clientService;
}
