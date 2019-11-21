package com.hotel.category.service;

import com.hotel.image.bean.Image;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

/**
 * @author 林晓锋
 * @date 2019/10/15
 * modified: 2019/10/21
 * 功能：
 */
public interface UploadInformationPictureService {

    /**
     * 保存图片途径
     * @param image
     * @return
     */
    boolean insertInformationImages(Image image);

    /**
     * 上传图片
     * @param file
     * @return
     */
    String uploadPicture(MultipartFile file);

    /**
     * 删除图片路径
     * @param fiId
     * @return
     */
    boolean deleteInformationImages(Long fiId);

    /**
     * 获得图片路径
     * @param fiId
     * @return
     */
    String selectInformationImagesPath(Long fiId);

    /**
     * 删除项目图片
     * @param filePath
     * @return
     */
    boolean deleteItemImages(String filePath);

    /**
     * 图片路径转换为url
     * @param informationList
     * @return
     */
    List<HashMap> transformUrl(HttpServletRequest request,List<HashMap> informationList) throws UnknownHostException;

    /**
     * 批量删除图片路径
     * @param informationIdList
     * @return
     */
    boolean deleteInformationImageList(List<Long> informationIdList);

}
