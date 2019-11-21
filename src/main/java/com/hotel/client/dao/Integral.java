package com.hotel.client.dao;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "integral")
public class Integral {
    @Id
    @Column(name = "i_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer i_id;

    @Column(name = "i_min")
    private Integer i_min;

    @Column(name = "i_max")
    private Integer i_max;

    @Column(name = "i_discount")
    private double i_discount;

    @Column(name = "i_name")
    private String i_name;

}
