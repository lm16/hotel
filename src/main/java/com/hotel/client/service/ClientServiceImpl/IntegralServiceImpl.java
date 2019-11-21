package com.hotel.client.service.ClientServiceImpl;

import com.hotel.client.dao.Integral;
import com.hotel.client.mapper.IntegralMapper;
import com.hotel.client.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IntegralServiceImpl implements IntegralService {
    @Autowired
    private IntegralMapper integralMapper;
    @Override
    public List<Integral> findAll() {
        return integralMapper.findAll();
    }

    @Override
    public Integral Integralsave(Integral integral) {
        return integralMapper.save(integral);
    }

    @Override
    public Integral Integralupdate(Integral integral) {
        return integralMapper.save(integral);
    }

    @Override
    public void Integrsldelete(Integer id) {

        integralMapper.deleteById(id);
    }


}
