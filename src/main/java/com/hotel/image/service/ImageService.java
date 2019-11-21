package com.hotel.image.service;


import com.hotel.image.bean.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    /**
     * 保存图片url
     */
    boolean insertImage(Image image);

    /**
     * 上传图片
     * @param file
     * @return
     */
    String uploadPicture(MultipartFile file);
}
