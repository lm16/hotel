package com.hotel.image.mapper;


import com.hotel.image.bean.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageMapper extends JpaRepository<Image,Long>, JpaSpecificationExecutor<Image> {


    void deleteByRecreationId(Long Rid);

}
