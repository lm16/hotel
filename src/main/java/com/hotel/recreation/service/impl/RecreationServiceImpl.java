package com.hotel.recreation.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.hotel.image.bean.Image;
import com.hotel.recreation.bean.Recreation;
import com.hotel.recreation.mapper.RecreationMapper;
import com.hotel.recreation.service.RecreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecreationServiceImpl implements RecreationService {

    @Autowired
    private RecreationMapper recreationMapper;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Recreation findRecreationById(Long id) {
        return recreationMapper.findById(id).get();
    }

    @Override
    public List<Recreation> findAll() {
        return recreationMapper.findAll();
    }

    @Override
    public Recreation createRecreation(Recreation recreation) {
        return recreationMapper.save(recreation);
    }

    @Override
    public List<Recreation> findRecreationsByRnameandEmployeename(String rname, String ename) {

        System.out.println(rname+"=========="+ename);
        return recreationMapper.findRecreationsByRnameandEmployeename(rname,ename);

//        System.out.println(recreation);
//        StringBuffer sql = new StringBuffer(" select * from recreation where 1=1 ");
//
//        if(null != recreation && StringUtils.isEmpty(recreation.getRname())){
//            sql.append(" re_name like %"+recreation.getRname()+"% ");
//        }
//        System.out.println(sql);
//        if (null != recreation && StringUtils.isEmpty(recreation.getEmployee().getName())){
//            sql.append(" and re_employee_id in ( select e_id from employee where e_name like %"+recreation.getEmployee().getName()+"% ) ");
//        }
//        System.out.println(sql);
//
//        Query dataQuery = entityManager.createNativeQuery(dataSql.toString(), VideoExtend.class);
//        Query countQuery = entityManager.createNativeQuery(countSql.toString());
//
//
//        Specification<Recreation> spec = new Specification<Recreation>() {
//            @Override
//            public Predicate toPredicate(Root<Recreation> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                Predicate re = null;
//                if (null != recreation && StringUtils.isEmpty(recreation.getRname())){
//                    re = criteriaBuilder.like(root.<String> get("re_name"), "%" + recreation.getRname() + "%");
//                }
//                Predicate emp = null;
//                if (null != recreation && null != recreation.getEmployee() &&  StringUtils.isEmpty(recreation.getEmployee().getName())){
//                    emp = criteriaBuilder.like(root.<String> get("employee").<String> get("name"), "%" + recreation.getEmployee().getName() + "%");
//                }
//                if(null != re) criteriaQuery.where(re);
//                if(null != emp) criteriaQuery.where(emp);
//
//                List<Predicate> list = new ArrayList<>();
//                list.add(re);
//                list.add(emp);
//                //此时条件之间是没有任何关系的。
//                Predicate[] arr = new Predicate[list.size()];
//                return criteriaBuilder.and(list.toArray(arr));
//            }
//        };
//        return recreationMapper.findAll(spec);
//        return null;
    }

    @Override
    public List<Recreation> modifyImageUrl(List<Recreation> recreations, HttpServletRequest request) {

        //获取ip
        InetAddress ia = null;
        try {
            ia = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String ip = ia.getHostAddress();
        //获取端口
        int host = request.getLocalPort();
        List<Recreation> list = new ArrayList<Recreation>();
        //解析图片保存路径为完整的url
        for (Recreation recreation : recreations) {
            if(recreation.getImages().size()>0){
                List<Image> images = new ArrayList<Image>();
                for (Image image : recreation.getImages()){
//                    image.setUrl("http://"+ip+ ":" + host +image.getUrl());
                    image.setUrl("http://10.20.5.29:8080" +image.getUrl());
                    images.add(image);
                }
                recreation.setImages(images);
            }
            list.add(recreation);
        }

        return list;
    }

    @Override
    public void deleteRecreationById(Long id) {
        recreationMapper.deleteById(id);
    }
}
