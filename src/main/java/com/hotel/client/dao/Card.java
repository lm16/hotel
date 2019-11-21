package com.hotel.client.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.models.auth.In;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "member_card")
public class Card {

    @Id
    @Column(name = "mc_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long mc_id;
    @Column(name="mc_cardnum")
    private String mc_cardnum;
    @Column(name = "mc_client_id")
    private Long mc_client_id;

    @Column(name="mc_name")
    private String mc_name;

    @Column(name = "mc_pwd")
    private String mc_pwd;

    @Column(name = "mc_integral")
    private double mc_integral;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Column(name = "mc_deadline")
    private Date mc_deadline;

    @Column(name = "mc_integral_id")
    private Integer mc_integral_id;

    @Column(name = "mc_employee_id")
    private Long mc_employee_id;

    @Column(name = "mc_create_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private  Date mc_create_time;

    @Column(name = "mc_remark")
    private  String mc_remark;

    @Column(name = "delmark")
    private  int delmark;

    @Column(name="mc_integral_name")
    private String mc_integral_name;

}
