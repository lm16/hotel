package com.hotel.category.service.Impl;

import com.hotel.category.mapper.UploadInformationPictureMapper;
import com.hotel.category.service.UploadInformationPictureService;
import com.hotel.image.bean.Image;
import com.hotel.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author 林晓锋
 * @date 2019/10/15
 * modified: 2019/10/21
 * 功能：
 */
@Service
public class UploadInformationPictureServiceImpl implements UploadInformationPictureService {

    @Autowired
    private UploadInformationPictureMapper uploadInformationPictureMapper;

    /**
     * 保存菜品途径
     * @param image
     * @return
     */
    @Override
    public boolean insertInformationImages(Image image) {

        return uploadInformationPictureMapper.insertInformationImages(image);
    }

    /**
     * 上传图片
     * @param file
     * @return
     */
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
        //新文件名
        fileName = UUID.randomUUID()+fileName;
        //文件相对路径
        File dest = UploadUtils.getImagePathFile();
        //System.out.println("dest="+dest);

        try {
            //构建文件的真实路径,项目的相对路径读取为硬盘下的绝对路径
            File newfile = new File(dest.getAbsolutePath() + File.separator +fileName);
            System.out.println("文件绝对路径 ="+dest.getAbsolutePath() + File.separator +fileName);
            //保存文件
            file.transferTo(newfile);

        }catch (IOException e) {

            e.printStackTrace();
        }

        //数据库图片路径
        String filePath = UploadUtils.IMG_PATH_PREFIX + fileName;

        return filePath;

    }

    /**
     * 删除图片路径
     * @param fiId
     * @return
     */
    @Override
    public boolean deleteInformationImages(Long fiId) {

        //删除图片路径
        return uploadInformationPictureMapper.deleteInformationImages(fiId);

    }

    /**
     * 获得图片路径
     * @param fiId
     * @return
     */
    @Override
    public String selectInformationImagesPath(Long fiId) {

        return uploadInformationPictureMapper.selectInformationImagesPath(fiId);
    }

    /**
     * 删除项目图片
     * @param filePath
     * @return
     */
    @Override
    public boolean deleteItemImages(String filePath) {

        //项目相对路径
        String path = UploadUtils.IMG_PATH_SUFFIX + filePath;
        File file = new File(path);
        //删除图片
        if (file.exists()&&file.isFile()) {

            file.delete();

        }

        return true;
    }

    /**
     * 图片路径转换为url
     * @param informationList
     * @return
     */
    @Override
    public List<HashMap> transformUrl(HttpServletRequest request,List<HashMap> informationList) throws UnknownHostException {

        //获取ip
        InetAddress ia = InetAddress.getLocalHost();
        String ip = ia.getHostAddress();
        //获取端口
        int host = request.getLocalPort();

        //解析图片保存路径为完整的url
        for (HashMap map: informationList) {

            if(map.containsKey("url")) {

                map.put("url","http://"+ip+ ":" + host +map.get("url"));
            }
        }

        return informationList;
    }

    /**
     * 批量删除图片路径
     * @param informationIdList
     * @return
     */
    @Override
    public boolean deleteInformationImageList(List<Long> informationIdList) {

        return uploadInformationPictureMapper.deleteInformationImageList(informationIdList);

    }
}
