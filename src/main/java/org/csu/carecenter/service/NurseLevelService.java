package org.csu.carecenter.service;

import org.csu.carecenter.Persistence.NurseLevelMapper;
import org.csu.carecenter.entity.NurseLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NurseLevelService {

    @Autowired
    private NurseLevelMapper nurseLevelMapper;

    public List<NurseLevel> getNurseLevelList(){
        return nurseLevelMapper.getAllNurseLevel();
    }

    public NurseLevel getNurseLevel(int id){
        return nurseLevelMapper.getNurseLevelById(id);
    }

    public void editNurLevel(NurseLevel nurseLevel){
        nurseLevelMapper.updateNurseLevel(nurseLevel);
    }

    public void addNurLevel(NurseLevel nurseLevel){
        nurseLevelMapper.insertNurseLevel(nurseLevel);
    }

    public void deleteNurLevel(int id){
        nurseLevelMapper.deleteNurseLevel(id);
    }

    public int getAddId(){
        return nurseLevelMapper.selectMaxId() + 1;
    }
}
