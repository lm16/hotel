package com.hotel.recreation.bean;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "recreation_order")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class RecreationOrder implements Serializable {

    @Id
    @Column(name = "reo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;             //娱乐项目订单
    @Column(name = "reo_client_id")
    private Long clientId;       //关联客户id
    @Column(name = "reo_recreation_id")
    private Long recreationId;   //关联项目id
    @Column(name = "reo_price")
    private double price;           //娱乐项目单价
    @Column(name = "reo_count")
    private int count;              //购买娱乐套餐的数量
    @Column(name = "reo_tprice")
    private double tprice;          //总价格
    @Column(name = "reo_status")
    private Integer status;         //订单状态
//    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "reo_datetime")
    private Date datetime;          //下单时间
    @Column(name = "reo_description")
    private String description;     //描述


}
