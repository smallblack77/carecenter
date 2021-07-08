package org.csu.carecenter.Persistence;

import org.csu.carecenter.entity.Bed;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BedMapper {

    //获取床位信息
    List<Bed> getBedList();


}
