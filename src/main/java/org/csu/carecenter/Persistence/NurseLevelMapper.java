package org.csu.carecenter.Persistence;

import org.csu.carecenter.entity.CustomerAndNurse;
import org.csu.carecenter.entity.NurseContent;
import org.csu.carecenter.entity.NurseLevel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NurseLevelMapper {

    List<NurseLevel> getAllNurseLevel();

    NurseLevel getNurseLevelById(int levelId);

    void updateNurseLevel(NurseLevel nurseLevel);

    void insertNurseLevel(NurseLevel nurseLevel);

    void deleteNurseLevel(int id);

    int selectMaxId();

    //通过custId获取护工和客户的关系
    CustomerAndNurse getCustAndNur(int custId);

    //通过NurId获取护工信息
    NurseContent getNurseContentById(int nurId);
}
