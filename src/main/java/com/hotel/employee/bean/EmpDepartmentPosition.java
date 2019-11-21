package com.hotel.employee.bean;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "emp_department_position")
public class EmpDepartmentPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "edp_id",length = 255)
    private Long id;

    @Column(name = "edp_d_id", length = 255)
    private Long departmentId;

    @Column(name = "edp_p_id",length = 255)
    private Long positionId;

}
