package org.csu.carecenter.Persistence;

import org.csu.carecenter.entity.CustAndSon;
import org.springframework.stereotype.Repository;

@Repository
public interface CustAndSonMapper {

    void insertRelation(CustAndSon custAndSon);
}
