package org.csu.carecenter.Persistence;

import org.csu.carecenter.entity.CustAndSon;
import org.springframework.stereotype.Repository;

@Repository
public interface CustAndSonMapper {

    void insertRelation(CustAndSon custAndSon);

    //获取数据
    CustAndSon getCustAndSon(CustAndSon custAndSon);
}
