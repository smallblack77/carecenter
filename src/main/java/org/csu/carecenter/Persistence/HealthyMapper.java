package org.csu.carecenter.Persistence;

import org.csu.carecenter.entity.Healthy;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthyMapper {

    //通过id和日期获取
    Healthy getHealthy(int id,String week);

    //修改
    void updateHealthy(Healthy healthy);

    //获取某个客户所有健康
    List<Healthy> getAllHealthy(int id);

    //所有
    List<Healthy> getAllList();
}
