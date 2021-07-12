package org.csu.carecenter.service;

import org.csu.carecenter.Persistence.HealthyMapper;
import org.csu.carecenter.entity.Healthy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthyService {

    @Autowired
    private HealthyMapper healthyMapper;

    //通过id和日期获取
    public Healthy getHealthy(int id, String week){
        return healthyMapper.getHealthy(id,week);
    }

    //修改
    public void updateHealthy(Healthy healthy){
        healthyMapper.updateHealthy(healthy);
    }


}
