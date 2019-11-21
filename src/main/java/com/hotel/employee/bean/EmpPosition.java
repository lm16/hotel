package com.hotel.employee.bean;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "emp_position")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class EmpPosition {
    @Id
    @Column(name = "ep_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;          //职称id主键
    @Column(name = "ep_name_cn")
    private String nameCn;      //中文职称
    @Column(name = "ep_name_en")
    private String nameEn;      //英文职称


}
