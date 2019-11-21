package com.hotel.employee.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "emp_permission")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class EmpPermission {

    @Id
    @Column(name = "per_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;         //主键
    @Column(name = "per_name")
    private String name;        //权限名称
    @Column(name = "per_permission")
    private String permission;  //授权码
    @Column(name = "per_description")
    private String description; //权限描述


}
