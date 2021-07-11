package org.csu.carecenter.Persistence;

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
}
