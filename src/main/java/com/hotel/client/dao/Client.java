package com.hotel.client.dao;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "client")
public class Client extends BaseRowModel {

    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ExcelProperty(value="主键id",index=0)
    private Long c_id;

    @Column(name="c_name")
    @ExcelProperty(value="客户姓名",index=1)
    private String c_name;

    @Column(name="c_phone")
    @ExcelProperty(value="客户电话",index=2)
    private String c_phone;

    @Column(name="c_email")
    @ExcelProperty(value="客户邮件",index=3)
    private String c_email;

    @Column(name="c_sex")
    @ExcelProperty(value="客户性别",index=4)
    private String c_sex;

    @Column(name="c_zj_type")
    @ExcelProperty(value="身份证类型",index=5)
    private String c_zj_type;

    @Column(name="c_zj_no")
    @ExcelProperty(value="身份证号码",index=6)
    private String c_zj_no;

    @Column(name="c_valid")
    @ExcelProperty(value="是否会员",index=7)
    private int c_valid;

    @Column(name="c_remark")
    private String c_remark;

    @Column(name="delmark")
    private int delmark;

    @Column(name="greate_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date greate_time;


}
