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

    //获取不空床位信息
    public List<Bed> getBedListNotEmpty(){
        return bedMapper.getBedListNotEmpty();
    }
    //获取空床位信息
    public List<Bed> getBedListEmpty(){
        return bedMapper.getBedListEmpty();
    }
    //获取所有床位信息
    public List<Bed> getAllBedList(){
        return bedMapper.getAllBedList();
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

    public void updateBed(Bed bed){
        bedMapper.updateBed(bed);
    }

    public int getMaxId(){
        return bedMapper.getMaxId();
    }

    public void updateBedAndCustomer(BedAndCustomer bedAndCustomer){
        bedMapper.updateBedAndCustomer(bedAndCustomer);
    }

    //通过id获取bed
    public Bed getBedByBedId(int bedId){
         return bedMapper.getBedByBedId(bedId);
    }

    public void updateBedStatus(int flag, int id){
        bedMapper.updateBedStatus(flag, id);
    }
}
