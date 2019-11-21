package com.hotel.client.service.ClientServiceImpl;

import com.hotel.client.dao.Card;
import com.hotel.client.dao.Client;
import com.hotel.client.mapper.CardMapper;
import com.hotel.client.mapper.ClientMapper;
import com.hotel.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private CardMapper cardMapper;

    @Override
    public Client findClientById(Long id) {
        return  clientMapper.findById(id).get();
    }

    @Override
    public List<Client> findAll() {
        List<Card> cards=cardMapper.findtimekout();
        for(Card s : cards){
            cardMapper.nomember(s.getMc_client_id());
        }
        cardMapper.timekout();
        return clientMapper.getAll();
    }

    @Override
    public Client ClientSave(Client client) {
        return clientMapper.save(client);
    }

    @Override
    public void deleteByIds(Long id) {
        clientMapper.deleteByIds(id);

    }

    @Override
    public Client ClientUpdate(Client client) {

        return clientMapper.save(client);
    }

    @Override
    public List<Client> findbysome(Client client) {
        /**root ：我们要查询的类型
         * query：添加查询条件
         * cb: 构建条件
         * specification为一个匿名内部类
         */
       Specification<Client> specification=new Specification<Client>() {
            @Override
            public Predicate toPredicate(Root<Client> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                //我理解为创建一个条件的集合
                List<Predicate> predicates = new ArrayList<Predicate>();
                //判断传过来的CityId是否为null,如果不为null就加到条件中
                if(client.getC_name()!=null){
                    /** cb.equal（）相当于判断后面两个参数是否一致
                     *root相当于我们的实体类的一个路径，使用get可以获取到我们的字段，因为我的cityid为Long类型
                     * 所以是as(Long.class)
                     *如果为Int,就是as(Integer.class) 第二个参数为前台传过来的参数，这句话就相当于
                     * 数据库字段的值cityid = 前台传过来的值client.getCityId()
                     */
                    predicates.add(cb.like(root.get("c_name"),"%"+client.getC_name()+"%"));
                }
                if(client.getC_phone()!=null){
                    //这里相当于数据库字段 name like %前台传过来的值%
                   // predicates.add(cb.equal(root.get("c_phone"),client.getC_phone()));
                    predicates.add(cb.like(root.get("c_phone"),"%"+client.getC_phone()+"%"));

                }
                if(client.getC_valid()==0){
                    //这里相当于数据库字段 parent（也是Long类型） = 前台传过来的值client.getParent()
                    predicates.add(cb.equal(root.get("c_valid").as(Integer.class),client.getC_valid()));
                }
                if(client.getC_valid()==1){
                    //这里相当于数据库字段 parent（也是Long类型） = 前台传过来的值client.getParent()
                    predicates.add(cb.equal(root.get("c_valid").as(Integer.class),client.getC_valid()));
                }
                predicates.add(cb.equal(root.get("delmark"),0));

                //创建一个条件的集合，长度为上面满足条件的个数
                Predicate[] pre = new Predicate[predicates.size()];
                //根据id 倒序排列
                query.orderBy(cb.desc(root.get("c_id")));
                //这句大概意思就是将上面拼接好的条件返回去
                return query.where(predicates.toArray(pre)).getRestriction();

            }
        };   //这里我们按照返回来的条件进行查询，就能得到我们想要的结果
        List<Client> list= clientMapper.findAll(specification);
        System.out.println("查询返回的结果为"+list);
        return list;

    }
}
