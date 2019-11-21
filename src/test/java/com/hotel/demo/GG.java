package com.hotel.demo;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.hotel.booking.bean.Demo;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author by lanmeng
 * @Classname GG
 * @Description TODO
 * @Date 19-11-20 下午2:42
 */
public class GG extends DemoApplicationTests {



    @Test
    public void demo()throws FileNotFoundException {

        OutputStream out = new FileOutputStream("/media/lanmeng/L2/a.xlsx");

        try{
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);

            Sheet sheet = new Sheet(1, 0, Demo.class);

            List<Demo> collect = new ArrayList<>();
            Demo demo = new Demo();
            demo.setId(1);
            demo.setType("hh");
            collect.add(demo);

            Demo demo2 = new Demo();
            demo2.setId(2);
            demo2.setType("gg");
            collect.add(demo2);

            writer.write(collect, sheet);
            writer.finish();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                out.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
