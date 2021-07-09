package org.csu.carecenter.service;

import org.csu.carecenter.Persistence.NurseContentMapper;
import org.csu.carecenter.entity.NurseContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NurseContentService {
    @Autowired
    NurseContentMapper nurseContentMapper ;

    //获取所有护工信息
    public List<NurseContent> getAllNurseContentList(){
       return nurseContentMapper.getAllNurseContentList();

    }

    //增加护工信息
    public void addNurseContent(NurseContent nurseContent){
        nurseContentMapper.addNurseContent(nurseContent);
    }

    //删除护工信息
    public void deleteNurContent(String nurid){
        nurseContentMapper.deleteNurContent(nurid);
    }
}
