package org.csu.carecenter.service;

import org.csu.carecenter.Persistence.NurseContentMapper;
import org.csu.carecenter.entity.NurseContent;
import org.csu.carecenter.entity.NurseRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NurseContentService {
    @Autowired
    NurseContentMapper nurseContentMapper ;

    //获取护工数量
    public int getCount()
    {
        return nurseContentMapper.getCount();
    }
    //获取所有护工信息
    public List<NurseContent> getAllNurseContentList(){
       return nurseContentMapper.getAllNurseContentList();

    }

    //增加护工信息
    public void addNurseContent(NurseContent nurseContent){
        nurseContentMapper.addNurseContent(nurseContent);
    }

    //删除护工信息
    public void deleteNurContent(String nurId){
        nurseContentMapper.deleteNurContent(nurId);
    }

    //通过id获取护工信息
    public NurseContent getNurById(String nurId){
        return nurseContentMapper.getNurById(nurId);
    }

    //修改护工信息
    public void updateNurContent(NurseContent nurseContent,String oldId){
        nurseContentMapper.updateNurContent(nurseContent,oldId);
    }

    //查询所有护理记录
    public List<NurseRecord> getAllNurseRecordList(){
        return nurseContentMapper.getAllNurseRecordList();
    }

    //获取护理记录byid
    public NurseRecord getNurseRecord(int id){
        return nurseContentMapper.getNurseRecord(id);
    }

    //添加护理记录
    public void addNurseRecord(NurseRecord nurseRecord){
        nurseContentMapper.addNurseRecord(nurseRecord);
    }

    //获取最大id
    public int getNurseRecordMaxId(){
        return nurseContentMapper.getNurseRecordMaxId();
    }

    //修改护理记录
    public void updateNurRecord(NurseRecord nurseRecord){
        nurseContentMapper.updateNurRecord(nurseRecord);
    }

    //删除
    public void deleteNurRecord(int id){
        nurseContentMapper.deleteNurRecord(id);
    }
}
