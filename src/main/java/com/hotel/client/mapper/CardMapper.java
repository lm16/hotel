package com.hotel.client.mapper;

import com.hotel.client.dao.Card;

import com.hotel.client.dao.Integral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CardMapper extends JpaRepository<Card ,Long> , JpaSpecificationExecutor<Card> {

    /**
     * 客户成为会员
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="update client set c_valid=1 where c_id=?1",nativeQuery = true)
    void memberclient(Long id);

    /**
     * 客户会员到期
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="update member_card set delmark=1  where mc_deadline<now()",nativeQuery = true)
    void timekout();

    @Query(value="select * from member_card  where mc_deadline<now()",nativeQuery = true)
    List<Card> findtimekout();



    /**
     * 客户会员或删除
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="update client set c_valid=0 where c_id=?1",nativeQuery = true)
    void nomember(Long id);
    /**
     * 删除卡
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="update member_card set delmark=1 where mc_client_id=?1",nativeQuery = true)
    void DeleteCard(Long id);



    /**
     * 查询所有
     * @return
     */
    @Query(value="select * from member_card where delmark!=1",nativeQuery = true)
    List<Card> getAll();

    /**
     * 根据客户id查询卡
     */
    @Query(value="select * from member_card where delmark!=1 And mc_client_id=?1",nativeQuery = true)
    Card findClientbyid(Long id);
    /**
     * 根据消费积分设置级别
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update member_card as m set m.mc_integral_id=(select i_id from Integral as i where m.mc_integral<=i.i_max AND m.mc_integral>i.i_min) where m.mc_cardnum=m.mc_cardnum",nativeQuery = true)
    int setlevel(@Param("mc_integral") double mc_integral, @Param("mc_cardnum") String mc_cardnum);

    /**
     * 根据消费积分id设置名称
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update member_card as m set m.mc_integral_name=(select i_name from Integral as i where i.i_id=?1) where m.mc_cardnum=?2" ,nativeQuery = true)
    void setname(int id,String num);

    /**
     * 根据级别查找折扣
     */
    /*@Query(value = "select i_discount,i_name from Integral as i where i.i_id=?1 ",nativeQuery = true)
     Integral finddiscount(int mc_integral_id);*/

    /**
     * 根据卡号查会员找折扣
     * @param mc_cardnum 卡号
     * @return 积分等级
     */
    @Query(value = "select mc_integral_id from member_card  where mc_cardnum=?1 and delmark!=1",nativeQuery = true)
    int findcount(String mc_cardnum);

    /**
     * 根据卡号查卡
     */

    @Query(value = "select * from member_card  where mc_cardnum=?1 and delmark!=1",nativeQuery = true)
    Card findByMc_cardnum(String mc_cardnum);
    /**
     * 消费之后修改积分
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update member_card set mc_integral=?1 where mc_cardnum=?2 ",nativeQuery = true)
    int updateInteglar(double mc_intergral, String id);


}
