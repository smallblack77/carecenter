package org.csu.carecenter.Persistence;

import org.csu.carecenter.entity.Diets;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DietMapper {

    //获取所有膳食信息
    List<Diets> getAllDiet();
}
