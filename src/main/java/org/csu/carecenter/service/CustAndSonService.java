package org.csu.carecenter.service;

import org.csu.carecenter.Persistence.CustAndSonMapper;
import org.csu.carecenter.entity.CustAndSon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustAndSonService {

    @Autowired
    private CustAndSonMapper custAndSonMapper;

    public void insertRelation(CustAndSon custAndSon){
        custAndSonMapper.insertRelation(custAndSon);
    }
}
