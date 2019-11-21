package com.hotel.employee.bean;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hotel.image.bean.Image;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "employee")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class Employee implements Serializable {
    @Id
    @Column(name = "e_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;          //员工id主键
    @Column(name = "e_number")
    private String number;    //员工编号
    @Column(name = "e_position_id", insertable = false,updatable = false)
    private Long positionId;  //关联emp_position职位表
    @Column(name = "e_department_id", insertable = false,updatable = false)
    private Long departmentId;//关联emp_department部门表
    @Column(name = "e_username")
    private String username;    //用户名登录id
    @Column(name = "e_password")
    private String password;    //用户密码
    @Column(name = "e_name")
    private String name;        //员工姓名
    @Column(name = "e_sex")
    private String sex;         //员工性别
    @Column(name = "e_birthday")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;      //生日
    @Column(name = "e_address")
    private String address;     //住址
    @Column(name = "e_phone")
    private String phone;       //电话
    @Column(name = "e_zj_type")
    private String zjType;      //证件类型
    @Column(name = "e_zj_no")
    private String zjNo;        //证件号
    @Column(name = "e_nation")
    private String nation;      //民族
    @Column(name = "e_birthplace")
    private String birthplace;  //籍贯
    @Column(name = "e_politics")
    private String politics;    //政治面貌
    @Column(name = "e_level")
    private String level;       //员工职位及权限 0：超级管理员 1：总经理   2：部门经理   3：员工



    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "e_department_id")
    private EmpDepartment empDepartment;

    @ManyToOne(fetch=FetchType.LAZY )
    @JoinColumn(name = "e_position_id")
    private EmpPosition empPosition;

    @OneToMany( fetch = FetchType.LAZY )
    @JoinTable(
            name="emp_role_permission",
            joinColumns={
                    @JoinColumn(name = "erp_d_id", referencedColumnName = "e_department_id"),
                    @JoinColumn(name = "erp_p_id", referencedColumnName = "e_position_id")
            },
            inverseJoinColumns=@JoinColumn(name="erp_per_id", referencedColumnName="per_id")
    )
    private List<EmpPermission> empPermissions;



}
