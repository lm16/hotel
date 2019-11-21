package com.hotel.recreation.mapper;


import com.hotel.recreation.bean.Recreation;
import com.hotel.recreation.bean.RecreationOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecreationOrderMapper extends JpaRepository<RecreationOrder,Long> {

}
