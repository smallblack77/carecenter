package org.csu.carecenter.service;


import org.csu.carecenter.Persistence.BedMapper;
import org.csu.carecenter.entity.Bed;
import org.csu.carecenter.entity.BedAndCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BedService {

    @Autowired
    private BedMapper bedMapper;

    //获取所有床位信息
    public List<Bed> getBedList(){
        return bedMapper.getBedList();
    }

    public BedAndCustomer getBedAndCustomer(int bedId){
        return bedMapper.getBedAndCustomer(bedId);
    }

    public void insertBed(Bed bed){
        bedMapper.insertBed(bed);
    }

    public void deleteBed(int bedId){
        bedMapper.deleteBed(bedId);
    }

    public void insertBedAndCustomer(BedAndCustomer bedAndCustomer){
        bedMapper.insertBedAndCustomer(bedAndCustomer);
    }

    public void deleteBedAndCustomer(int bedId){
        bedMapper.deleteBedAndCustomer(bedId);
    }

    public void updateBed(Bed bed,int oldBedId){
        bedMapper.updateBed(bed,oldBedId);
    }

    public int getMaxId(){
        return bedMapper.getMaxId();
    }

    public void updateBedAndCustomer(BedAndCustomer bedAndCustomer){
        bedMapper.updateBedAndCustomer(bedAndCustomer);
    }
}
