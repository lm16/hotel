package com.hotel.recreation.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hotel.employee.bean.Employee;
import com.hotel.image.bean.Image;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "recreation")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class Recreation {

    @Id
    @Column(name = "re_id",insertable = false,updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "re_employee_id",insertable=false, updatable=false)
    private Long employeeId;
    @Column(name = "re_name")
    private String name;
    @Column(name = "re_phone")
    private String phone;
    @Column(name = "re_address")
    private String address;
    @Column(name = "re_remark")
    private String remark;
    @Column(name = "re_description")
    private String description;
    @Column(name = "re_price")
    private double price;
    @Column(name = "datetime")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datetime;
    @Column(name = "by_who")
    private Integer byWho;
    @Column(name = "deleted")
    private Integer deleted;

    @OneToMany( fetch = FetchType.LAZY)
    @JoinColumn(name = "i_recreation_id" )
    private List<Image> images;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "re_employee_id")
    private Employee employee;

}