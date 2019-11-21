package com.hotel.client.service;

import com.hotel.client.dao.Card;
import com.hotel.client.dao.Integral;

import java.util.List;

public interface CardService {

    /**
     * 查询所有会员卡信息
     * @return

     */
    List<Card> findAll();

    /**
     * 根据客户id查询会员卡
     */
    Card findCardByClient(Long mc_client_id);

    /**
     * 会员卡添加
     */
    void cardSave(Card card);

    /**
     * 会员卡修改
     */
    Card cardUpdate(Card card);


    /**
     * 动态查询
     */
    List<Card> findBysomething(Card card);

    /**
     * 删除会员卡
     */
    void deleateCard(Long id);

    /**
     * 根据消费积分设置级别
     */
    int findlevel(double integral,String mc_cardnum);

    /**
     * 根据级别查折扣
     */
    Integral finddiscount(int id);
    /**
     * 更新积分和折扣级别
     */
    boolean updateintegral(double integlar,String cardnum);

    /**
     * 根据会员卡号查折扣
     *
     */
    Integral findcount(String cardnum);

}
