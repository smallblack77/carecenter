package org.csu.carecenter.service;

import org.csu.carecenter.Persistence.DietMapper;
import org.csu.carecenter.entity.Diet;
import org.csu.carecenter.entity.OrderDiet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietService {

    @Autowired
    private DietMapper dietMapper;

    //膳食信息
    public List<Diet> getAllDiet()
    {
        List<Diet> diets = dietMapper.getAllDiet();
        return diets;
    }

    public Diet getDietById(int id){
        return dietMapper.getDietById(id);
    }

    public void addDiet(Diet diet){
        dietMapper.insertDiet(diet);
    }

    public void deleteDiet(int id){
        dietMapper.deleteDiet(id);
    }

    public void editDiet(Diet diet){
        dietMapper.updateDiet(diet);
    }

    //客户点餐信息
    public List<OrderDiet> getOrderDietById(int customerId){
        return dietMapper.getOrderDietById(customerId);
    }
}
