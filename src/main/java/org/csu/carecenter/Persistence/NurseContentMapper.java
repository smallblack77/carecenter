package org.csu.carecenter.Persistence;

import org.csu.carecenter.entity.NurseContent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NurseContentMapper {
    //获取所有信息
    List<NurseContent> getAllNurseContentList();

    //新增护工信息
    void addNurseContent(NurseContent nurseContent);

    //删除护工信息
    void deleteNurContent(String nurid);
}
