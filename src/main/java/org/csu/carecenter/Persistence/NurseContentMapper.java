package org.csu.carecenter.Persistence;

import org.csu.carecenter.entity.NurseContent;
import org.csu.carecenter.entity.NurseRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NurseContentMapper {
    //获取所有信息
    List<NurseContent> getAllNurseContentList();

    //新增护工信息
    void addNurseContent(NurseContent nurseContent);

    //删除护工信息
    void deleteNurContent(String nurId);

    //通过id获取信息
    NurseContent getNurById(String nurId);

    //修改护工信息
    void updateNurContent(NurseContent nurseContent,String oldId);

    //查询护理记录
    List<NurseRecord> getAllNurseRecordList();

    //获取护理记录通过id
    NurseRecord getNurseRecord(int id);
}
