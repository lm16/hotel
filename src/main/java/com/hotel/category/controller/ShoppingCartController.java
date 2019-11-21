package com.hotel.category.controller;

import com.hotel.bean.Result;
import com.hotel.bean.ResultType;
import com.hotel.category.bean.Information;
import com.hotel.category.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 林晓锋
 * @date 2019/10/12
 * modified: 2019/10/19
 * 功能：购物车接口
 */
@Api(tags = "菜品购物车接口")
@RequestMapping("/json/shoppingCart")
@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @ApiOperation(value = "加入购物车",notes = "正在测试")
    @RequestMapping(value = "/addShoppingCart",method = RequestMethod.POST)
    @ResponseBody
    public Result addShoppingCart(HttpServletRequest request,
                                  @ApiParam(name = "fiId",value = "菜品id",required = true)
                          @RequestParam(value = "fiId") Long fiId,
                                  @ApiParam(name = "operation",value = "操作",required = false)
                          @RequestParam(value = "operation" ,required = false) String operation) throws UnknownHostException {
        //获得菜品以及图片路径
        HashMap information = shoppingCartService.selectInformationById(fiId);
        //获取ip
        InetAddress ia = InetAddress.getLocalHost();
        String ip = ia.getHostAddress();
        //获取端口
        int host = request.getLocalPort();
        //图片路径解析为url
        if(information.containsKey("url")) {
            information.put("url","http://"+ip+ ":" + host + information.get("url"));
        }
        //创建session会话
        HttpSession session = request.getSession();
        //取得购物车的菜品
        List<HashMap> cartMap = (List<HashMap>)session.getAttribute("cartMap");
        //没有购物车
        if(cartMap==null) {
           //创建购物车
            cartMap = new ArrayList<HashMap>();
            information.put("num",1);
            cartMap.add(information);

        }else {
            //判断是否为同一商品
            boolean flag = false;
            for (HashMap hashMap : cartMap) {
                //相同商品加入购物车
                if(hashMap.get("fi_id")==information.get("fi_id")) {
                        //是同一商品
                        flag = true;
                        //商品数量加一
                        if (operation!=null&&operation.equals("increase")) {

                            hashMap.put("num",(Integer)hashMap.get("num")+1);
                            System.out.println("相同商品数量加1");
                            break;

                        }else {
                            //商品数量为1则移除该商品
                            if((int)hashMap.get("num")==1) {

                                //特别注意这里立即跳出循环，否则没有任何商品list越界报错
                                cartMap.remove(hashMap);
                                System.out.println("移除该商品");
                                break;

                            }else {
                                //商品数量减一
                                hashMap.put("num",(Integer)hashMap.get("num")-1);
                                System.out.println("相同商品数量减1");
                                break;

                            }
                        }

                }
            }
            //不同商品加入购物车
            if(flag == false) {

                information.put("num",1);
                cartMap.add(information);
                System.out.println("不同商品数量加1");

            }
        }

        //保存到购物车
          System.out.println(cartMap);
        session.setAttribute("cartMap",cartMap);
        return Result.build(ResultType.Success).appendData("data","加入购物车成功");
    }

    @ApiOperation(value = "展示购物车",notes = "正在测试")
    @RequestMapping(value = "/showShoppingCart",method = RequestMethod.GET)
    @ResponseBody
    public Result showShoppingCart(HttpServletRequest request) {

        HttpSession session = request.getSession();
        List<HashMap> cartMap = (List<HashMap>) session.getAttribute("cartMap");

        if(cartMap == null) {
            cartMap = new ArrayList<HashMap>();
        }

        System.out.println("cartMap="+cartMap);

        return Result.build(ResultType.Success).appendData("data",cartMap);

    }

    @ApiOperation(value = "清空购物车",notes = "正在测试")
    @RequestMapping(value = "/deleteShoppingCart",method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteShoppingCart(HttpServletRequest request) {

        HttpSession session = request.getSession();
        List<HashMap> cartMap = new ArrayList<>();
        session.setAttribute("cartMap",cartMap);

        return Result.build(ResultType.Success).appendData("data","清空成功");

    }
}
