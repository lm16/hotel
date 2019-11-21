package com.hotel.shiro;

import com.hotel.employee.bean.EmpPermission;
import com.hotel.employee.bean.Employee;
import com.hotel.employee.mapper.EmployeeMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 自定义Realm
 */
public class EmployeeRealm extends AuthorizingRealm {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");

        //给资源授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //添加资源的授权字符串
//        info.addStringPermission("user:add");

        //根据数据库查询当前登录用户的授权字符串
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee)subject.getPrincipal();
        System.out.println("===*****************==="+employee);

        List<EmpPermission> empPermissions = employee.getEmpPermissions();
        //添加资源授权字符串
        for ( EmpPermission empPermission: empPermissions) {
            System.out.println("权限="+empPermission.getPermission());
            info.addStringPermission(empPermission.getPermission());
        }
//        info.addStringPermission();

        return info;
    }


    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //查询数据库用户
        //获取传过来的用户名和密码
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        Employee employee = employeeMapper.findEmployeeByNumber(usernamePasswordToken.getUsername());


//        // 认证的实体信息，可以是username，也可以是用户的实体类对象，这里用的用户名
//        Object principal = usernamePasswordToken.getUsername();
//        // 从数据库中查询的密码
//        Object credentials = employee.getPassword();
//        // 颜值加密的颜，可以用用户名
//        ByteSource credentialsSalt = ByteSource.Util.bytes(principal);
//        // 当前realm对象的名称，调用分类的getName()
//        String realmName = this.getName();


//        System.out.println("认证后++++++"+employee);
        //判断用户名
        if(employee==null){
            //用户名不存在
//            System.out.println("++++++weikon"+employee);
            return null;
        }

        return new SimpleAuthenticationInfo(employee,employee.getPassword(),this.getName());
    }
}
