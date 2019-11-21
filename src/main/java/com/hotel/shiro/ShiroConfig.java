package com.hotel.shiro;

import com.hotel.employee.bean.EmpPermission;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {


    /**
     * Shiro生命周期处理器
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
//    @Bean
//    @ConditionalOnMissingBean
//    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
//        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        advisorAutoProxyCreator.setProxyTargetClass(true);
//        return advisorAutoProxyCreator;
//    }
    /**
     * 开启aop注解支持
     * @param securityManager
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加Shiro内置过滤器
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         *      常用的过滤器：
         *          anon：无需认证（登录）可以访问
         *          authc：必须认证才可以访问
         *          user：如果使用rememberMe的功能可以直接访问
         *          （常用）
         *          perms：该资源必须得到资源权限才可以访问
         *          role： 该资源必须得到角色权限才可以访问
         */

        Map<String,String> filterMap = new LinkedHashMap<String, String>();


        //注意****要 *先发行* *再拦截*   放行才能成功
        //实现无需认证访问（放行）
//        filterMap.put("/login/**","anon");
//        filterMap.put("/logout/**","anon");
//        filterMap.put("/swagger-ui.html","anon");
//        filterMap.put("/v2/**","anon");
//        filterMap.put("/swagger-resources/**","anon");
//        filterMap.put("/**","authc");
//        filterMap.put("/**", "anon");


        //授权过滤器
//        filterMap.put("/Recreation/*","perms[recreation:add]");
//        filterMap.put("/Recreation/deleteRecreationById/*","perms[recreation:add]");
//        filterMap.put("Recreation/**","perms[recreation:*]");
//        filterMap.put("Employee/**","perms[employee:*]");

//        filterMap.put("/*/find/**","perms[*:find]");
//        filterMap.put("/*/add/**","perms[*:add]");
//        filterMap.put("/*/delete/**","perms[*:delete]");
//        filterMap.put("/*/modify/**","perms[*:modify]");

        //实现权限拦截（登录才能访问）
//        filterMap.put("/Recreation/**","authc");
//        filterMap.put("/RecreationOrder/**","authc");
//        filterMap.put("/Employee/**","authc");
//        filterMap.put("/EmpDepartmentPosition/**","authc");
//        filterMap.put("/EmpPermission/**","authc");
//        filterMap.put("/User/**","authc");
//        filterMap.put("/Category/**","authc");
//        filterMap.put("/Room/**","authc");
//        filterMap.put("/Total/**","authc");
//        filterMap.put("/Client/**","authc");
//        filterMap.put("/**", "authc");




        //修改跳转的登录页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        // 登录成功后要跳转的链接
//        shiroFilterFactoryBean.setSuccessUrl("/index");
        //设置未授权的提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

//        //拦截
//        filterMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }


    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("employeeRealm") EmployeeRealm employeeRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(employeeRealm);
        //注入缓存管理器
//        securityManager.setCacheManager(ehCacheManager());
        return securityManager;
    }


    /**
     * 创建Realm
     */
    @Bean(name = "employeeRealm")
    public EmployeeRealm getRealm(){
        EmployeeRealm employeeRealm = new EmployeeRealm();
//        employeeRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return employeeRealm;
    }


    /*
     * 凭证匹配器 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * 所以我们需要修改下doGetAuthenticationInfo中的代码; )
     */
//    @Bean
//    public HashedCredentialsMatcher hashedCredentialsMatcher() {
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
//        hashedCredentialsMatcher.setHashIterations(1024);// 散列的次数，比如散列两次，相当于md5(md5(""));
//        return hashedCredentialsMatcher;
//    }

    /*
     * shiro缓存管理器;
     * 需要注入对应的其它的实体类中-->安全管理器：securityManager可见securityManager是整个shiro的核心；
     */
//    @Bean
//    public EhCacheManager ehCacheManager() {
//        System.out.println("ShiroConfiguration.getEhCacheManager()");
//        EhCacheManager cacheManager = new EhCacheManager();
//        cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
//        return cacheManager;
//    }



}
