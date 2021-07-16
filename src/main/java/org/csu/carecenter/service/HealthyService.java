package org.csu.carecenter.service;

import org.csu.carecenter.Persistence.HealthyMapper;
import org.csu.carecenter.entity.Healthy;
import org.csu.carecenter.entity.VO.HealthyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    //获取某个客户所有的
    public List<Healthy> getAllHealthy(int id){
        return  healthyMapper.getAllHealthy(id);
    }

    //所有
    public List<Healthy> getAllList(){
        return healthyMapper.getAllList();
    }

    //获取某个客户当天的基本信息和健康信息
    public List<HealthyVO> getHealthyVO(int custId, String week){
        return  healthyMapper.getHealthyVO(custId, week);
    }
}
