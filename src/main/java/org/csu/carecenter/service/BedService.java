package org.csu.carecenter.service;

import org.csu.carecenter.Persistence.BedMapper;
import org.csu.carecenter.entity.Bed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BedService {

    @Autowired
    private BedMapper bedMapper;

    public List<Bed> getBedList(){

    }
}
