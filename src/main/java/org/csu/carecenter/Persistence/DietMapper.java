package org.csu.carecenter.Persistence;

import org.csu.carecenter.entity.Diet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DietMapper {

    //获取所有膳食信息
    List<Diet> getAllDiet();

    Diet getDietById(int id);

    void deleteDiet(int id);

    void updateDiet(Diet diet);

    void insertDiet(Diet diet);
}
