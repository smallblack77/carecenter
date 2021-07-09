package org.csu.carecenter.service;

import org.csu.carecenter.Persistence.DietMapper;
import org.csu.carecenter.entity.Diets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietService {

    @Autowired
    private DietMapper dietMapper;

    public List<Diets> getAllDiet()
    {
        List<Diets> diets = dietMapper.getAllDiet();
        return diets;
    }
}
