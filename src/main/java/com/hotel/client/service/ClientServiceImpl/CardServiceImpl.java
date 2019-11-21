package com.hotel.client.service.ClientServiceImpl;
import com.hotel.client.dao.Card;
import com.hotel.client.dao.Integral;
import com.hotel.client.mapper.CardMapper;
import com.hotel.client.mapper.IntegralMapper;
import com.hotel.client.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardMapper cardMapper;

    @Autowired
    private IntegralMapper integralMapper;
    @Override
    public List<Card> findAll() {
        List<Card> cards=cardMapper.findtimekout();
        for(Card s : cards){
            cardMapper.nomember(s.getMc_client_id());
        }
        cardMapper.timekout();
        return  cardMapper.getAll();

    }

    @Override
    public Card findCardByClient(Long mc_client_id) {
    return  cardMapper.findClientbyid(mc_client_id);
    }

    @Override
    public void cardSave(Card card) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(card.getMc_deadline());
        cal.add(Calendar.DATE, 1);
        card.setMc_deadline(cal.getTime());
        cardMapper.memberclient(card.getMc_client_id());
        cardMapper.save(card);
        cardMapper.setname(card.getMc_integral_id(),card.getMc_cardnum());

    }

    @Override
    public Card cardUpdate(Card card) {
        return cardMapper.save(card);
    }

    @Override
    public void deleateCard(Long id) {

        cardMapper.DeleteCard(id);
        cardMapper.nomember(id);

    }

    @Override
    public List<Card> findBysomething(Card card) {
        Specification<Card> specification=new Specification<Card>() {
            @Override
            public Predicate toPredicate(Root<Card> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();

                if(card.getMc_cardnum()!=null){

                    predicates.add(cb.like(root.get("mc_cardnum"),"%"+card.getMc_cardnum()+"%"));
                }
                if(card.getMc_name()!=null){
                    //这里相当于数据库字段 name like %前台传过来的值%
                    // predicates.add(cb.equal(root.get("c_phone"),Card.getC_phone()));
                    predicates.add(cb.like(root.get("mc_name"),"%"+card.getMc_name()+"%"));

                }
                predicates.add(cb.equal(root.get("delmark"),0));
                Predicate[] pre = new Predicate[predicates.size()];
                //根据id 倒序排列
               // query.orderBy(cb.desc(root.get("mc_id")));
                //这句大概意思就是将上面拼接好的条件返回去
                return query.where(predicates.toArray(pre)).getRestriction();

            }
        };   //这里我们按照返回来的条件进行查询，就能得到我们想要的结果
        List<Card> list= cardMapper.findAll(specification);
        System.out.println("查询返回的结果为"+list);
        return list;


    }

    @Override
    public int findlevel(double integral,String id) {
        int c= cardMapper.setlevel(integral,id);
        Card card =cardMapper.findByMc_cardnum(id);
        cardMapper.setname(c,card.getMc_cardnum());
        return  c;

    }

    @Override
    public Integral finddiscount(int id) {

        return integralMapper.findById(id).get();
    }

    @Override
    public boolean updateintegral(double integlar,String  mc_cardnum) {
       double d=0;
        Card card = cardMapper.findByMc_cardnum(mc_cardnum);
        double i=card.getMc_integral()+integlar;
       d  =cardMapper.updateInteglar(i,mc_cardnum);
       cardMapper.setlevel(i,mc_cardnum);
       cardMapper.setname(card.getMc_integral_id(),card.getMc_cardnum());
       if(d==0){
           return  false;
       }

       else{
           return  true;
       }

    }

    @Override
    public Integral findcount(String cardnum) {
        int integral = 0;
        integral=cardMapper.findcount(cardnum);
        System.out.println("========="+integral);
        if(integral>0)
        return  integralMapper.findById(integral).get();
        return null;
    }
}
