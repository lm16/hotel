package com.hotel.employee.bean;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Data
@Entity
@Table(name = "emp_department")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class EmpDepartment implements Serializable {
    @Id
    @Column(name = "ed_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;      //部门id主键
    @Column(name = "ed_name")
    private String name;    //部门名称

//    @OneToMany(mappedBy = "empDepartment",targetEntity = Employee.class)
//    private List<Employee> employees;

    @OneToMany( fetch = FetchType.LAZY )
    @JoinTable(
            name="emp_department_position",
            joinColumns={
                    @JoinColumn(name = "edp_d_id", referencedColumnName = "ed_id"),
            },
            inverseJoinColumns=@JoinColumn(name="edp_p_id", referencedColumnName="ep_id")
    )
    private List<EmpPosition> positions;


}
