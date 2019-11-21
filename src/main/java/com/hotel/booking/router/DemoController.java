package com.hotel.booking.router;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.hotel.booking.bean.Demo;
import com.hotel.booking.bean.RoomType;
import com.hotel.booking.bean.RoomType2;
import com.hotel.booking.mapper.RoomTypeMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author by lanmeng
 * @Classname DemoController
 * @Description TODO
 * @Date 19-11-20 下午3:01
 */
@Api(tags="发光发热")
@RestController
@RequestMapping("/fire")
public class DemoController extends BookingObject{

    @ApiOperation("接口名")
    @GetMapping
    public void fire(HttpServletResponse response) throws IOException{

        ServletOutputStream out = response.getOutputStream();

        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        String fileName = LocalDate.now().toString();
        response.setHeader("Content-disposition", "attachment;filename="+fileName+".xlsx");

        demo(out);

    }

    private void demo(OutputStream out)throws FileNotFoundException {

            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);

            Sheet sheet = new Sheet(1, 0, RoomType2.class);
            sheet.setSheetName("下载");

            List<RoomType2> collect = roomTypeMapper.getAll();

            writer.write(collect, sheet);
            writer.finish();
    }
}
