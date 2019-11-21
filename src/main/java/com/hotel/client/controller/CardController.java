package com.hotel.client.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hotel.bean.Result;
import com.hotel.bean.ResultType;
import com.hotel.client.dao.Card;
import com.hotel.client.dao.Integral;
import com.hotel.client.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@Api(tags = "会员卡接口")
@Controller
public class CardController {
    @Autowired
    private CardService cardService;

    @RequiresPermissions(value = { "*:find" }, logical = Logical.OR)
    @ApiOperation(value = "查询所有卡信息" ,notes="无传参，返回list")
    @GetMapping("/Card/findAll")
    @ResponseBody
    public Result selectAllCard() throws IOException {
        PageHelper.startPage(1,2);
        List<Card> cards = cardService.findAll();
        PageInfo pageInfo = new PageInfo(cards);
        return  Result.build(ResultType.Success).appendData("data",pageInfo);
    }

    @ApiOperation(value = "根据客户id查卡",notes = "传id")
    @ApiImplicitParam(name = "id", value = "客户id", required = true, dataType = "Long", paramType ="path" )
    @PutMapping("/Card/findcardBy1/{id}")
    @ResponseBody
    public  Result findbyid(@PathVariable Long id){
        Card c = cardService.findCardByClient(id);
        if(c==null){
            return Result.build(ResultType.Failed);
        }
        return  Result.build(ResultType.Success).appendData("data",c);
    }

    @RequiresPermissions(value = { "*:add" }, logical = Logical.OR)
    @ApiOperation(value = "增加卡",notes = "传实体")
    @ApiImplicitParam(name = "card", value = "会员卡实体类", required = true, dataType = "Card")
    @PostMapping("/Card/cardSave")
    @ResponseBody
    public  Result CardSave(@RequestBody(required = false) Card card){
        cardService.cardSave(card);

        return  Result.build(ResultType.Success);
        }

    @RequiresPermissions(value = { "*:modify" }, logical = Logical.OR)
    @ApiOperation(value = "修改卡",notes = "传实体")
    @ApiImplicitParam(name = "card", value = "会员卡实体类", required = true, dataType = "Card")
    @PostMapping("/Card/cardUpdate")
    @ResponseBody
    public  Result CardUpdate(@RequestBody(required = false) Card card){
        Card c = cardService.cardUpdate(card);
        if(c==null){
            return Result.build(ResultType.Failed);
        }
        return  Result.build(ResultType.Success).appendData("data",c);
    }

    @RequiresPermissions(value = { "*:delete" }, logical = Logical.OR)
    @ApiOperation(value = "删除卡",notes = "传id")
    @ApiImplicitParam(name = "id", value = "客户id", required = true, dataType = "int", paramType ="path" )
    @PutMapping("/Card/Carddelate/{id}")
    @ResponseBody
    public  Result DeleteCard(@PathVariable Long id){
        cardService.deleateCard(id);
        return  Result.build(ResultType.Success);
    }

    @ApiOperation(value = "根据条件查询客户信息", notes = "根据传入卡号，会员卡号的查询")
    @ApiImplicitParam(name = "card", value = "卡实体类", required = true, dataType = "Card")
    @PostMapping("/Card/Cardsearch")
    @ResponseBody
    public Result Cardsearch(@RequestBody(required = false) Card card){
        PageHelper.startPage(1,2);
        List<Card> cards = cardService.findBysomething(card);
        PageInfo pageInfo = new PageInfo(cards);
        return  Result.build(ResultType.Success).appendData("data",pageInfo);
    }

    @ApiOperation(value = "根据级别查折扣",notes = "传折扣级别id")
    @ApiImplicitParam(name = "integral_id", value = "折扣级别", required = true, dataType = "int", paramType ="path" )
    @PutMapping("/Card/findDiscount/{integral_id}")
    @ResponseBody
    public  Result findDiscount(@PathVariable Integer integral_id){
             Integral integral = cardService.finddiscount(integral_id);
        if(integral==null){
            return Result.build(ResultType.Failed);
        }
        return  Result.build(ResultType.Success).appendData("data",integral);
    }

    @ApiOperation(value = "修改累加积分",notes = "传积分和会员卡")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "integral", value = "积分", required = true, dataType = "double", paramType ="path" ),
            @ApiImplicitParam(name = "mc_cardnum", value = "会员卡号", required = true, dataType = "String", paramType ="path" )

    }
      )
    @PutMapping("/Card/findintegral/{integral}/{mc_cardnum}")
    @ResponseBody
    public  Result updateintegral(@PathVariable double integral,@PathVariable  String mc_cardnum){
       boolean i=cardService.updateintegral(integral,mc_cardnum);
        if(i==false){
            return Result.build(ResultType.Failed);
        }
        return  Result.build(ResultType.Success).appendData("data",i);
    }

    @ApiOperation(value = "根据卡号查折扣",notes = "传卡号")
    @ApiImplicitParam(name = "cardnum", value = "卡号", required = true, dataType = "String", paramType ="path" )
    @GetMapping("/Card/findcount/{cardnum}")
    @ResponseBody
    public  Result findcount(@PathVariable String cardnum){

        Integral integral=cardService.findcount(cardnum);
        if(integral==null){
            return Result.build(ResultType.Failed,"找不到会员卡号");
        }
        return  Result.build(ResultType.Success).appendData("data",integral);
    }

}
