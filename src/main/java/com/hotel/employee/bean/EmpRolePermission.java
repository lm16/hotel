package com.hotel.employee.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.models.auth.In;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "emp_role_permission")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class EmpRolePermission implements Serializable {

    @Id
    @Column(name = "erp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "erp_d_id")
    private Long departmentId;
    @Column(name = "erp_p_id")
    private Long positionId;
    @Column(name = "erp_per_id")
    private Long permissionId;

//    private List<Em>

}
