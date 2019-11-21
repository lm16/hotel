package com.hotel.category.mapper;

import com.hotel.image.bean.Image;

import java.util.List;

/**
 * @author 林晓锋
 * @date 2019/10/15
 * modified: 2019/10/15
 * 功能：菜品图片接口
 */
public interface UploadInformationPictureMapper {

    /**
     * 保存图片途径
     * @param image
     * @return
     */
    boolean insertInformationImages(Image image);

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
     * 批量删除图片路径
     * @param informationIdList
     * @return
     */
    boolean deleteInformationImageList(List<Long> informationIdList);
}
