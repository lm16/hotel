package com.hotel.image.service.impl;

import com.hotel.image.bean.Image;
import com.hotel.image.mapper.ImageMapper;
import com.hotel.image.service.ImageService;
import com.hotel.utils.UploadUtils;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageMapper imageMapper;

    @Override
    public boolean insertImage(Image image) {
        Image image1 = imageMapper.save(image);
        if (image1 == null) {
            return false;
        }
        return true;
    }

    @Override
    public String uploadPicture(MultipartFile file) {
        if (file.isEmpty()) {
             System.out.println("文件为空");
        }
        //原文件名
        String fileName = file.getOriginalFilename();
        //后缀名
    String suffix = fileName.substring(fileName.lastIndexOf("."));
    //        System.out.println("后缀="+suffix);
    // 新文件名
        fileName = UUID.randomUUID()+fileName;
        //文件相对路径

        File dest = UploadUtils.getImagePathFile();
        //System.out.println("dest="+dest);

        try {
            //构建文件的真实路径,项目的相对路径读取为硬盘下的绝对路径
            File newfile = new File(dest.getAbsolutePath() + File.separator +fileName);
            //System.out.println("文件绝对路径 ="+dest.getAbsolutePath() + File.separator +fileName);
            //保存文件
            file.transferTo(newfile);

        }catch (IOException e) {

            e.printStackTrace();
        }

        //数据库图片路径
        String filePath = UploadUtils.IMG_PATH_PREFIX + fileName;

        return filePath;

    }
    }

