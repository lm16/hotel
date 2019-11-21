package com.hotel.utils;

import com.hotel.image.bean.Image;

import java.io.File;

/**
 * @author 林晓锋
 * @date 2019/10/14
 * modified: 2019/10/14
 * 功能：上传文件相对路径
 */
public class UploadUtils {
    //项目根路径下的目录
    public final static String IMG_PATH_PREFIX = "/static/images/food/";

    public final static String IMG_PATH_SUFFIX = "src/main/resources";

    public final static String IMG_PATH_PREFIXR="/static/images/room/";

    public final static String IMG_PATH_PREFIXRe="/static/images/recreation/";

    public static File getImagePathFile(Image image) {
        //总的项目文件夹下的路径
        String filePath;
        if(image.getRoomId()!=null){
            filePath  = new String(IMG_PATH_SUFFIX +IMG_PATH_PREFIXR);
        }
       else {
            filePath = new String(IMG_PATH_SUFFIX + IMG_PATH_PREFIXRe);
        }
        //创建文件夹
        File fileDir = new File(filePath);
        if(!fileDir.exists()) {
            //没有则递归生成
            fileDir.mkdirs();

        }
        return fileDir;
    }

    public static File getImagePathFile() {
        //总的项目文件夹下的路径
        String filePath;
            filePath =new String(IMG_PATH_SUFFIX +IMG_PATH_PREFIX);
        //创建文件夹
        File fileDir = new File(filePath);
        if(!fileDir.exists()) {
            //没有则递归生成
            fileDir.mkdirs();

        }
        return fileDir;
    }

    //娱乐项目路径
    public static File getRecreationImagePathFile() {
        //总的项目文件夹下的路径
        String filePath;
        filePath =new String(IMG_PATH_SUFFIX +IMG_PATH_PREFIXRe);
        //创建文件夹
        File fileDir = new File(filePath);
        if(!fileDir.exists()) {
            //没有则递归生成
            fileDir.mkdirs();

        }
        return fileDir;
    }

    public static File getRoomTypeImagePathFile() {

        String filePath;

        filePath = IMG_PATH_SUFFIX + IMG_PATH_PREFIXR;

        File fileDir = new File(filePath);
        if(!fileDir.exists()) {
            fileDir.mkdirs();
        }

        return fileDir;
    }
}
