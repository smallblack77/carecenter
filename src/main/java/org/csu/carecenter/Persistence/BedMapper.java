package org.csu.carecenter.Persistence;

import org.csu.carecenter.entity.Bed;
import org.csu.carecenter.entity.BedAndCustomer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BedMapper {

    //获取床位信息
    List<Bed> getBedList();

    //获取床位和客户具体信息
    BedAndCustomer getBedAndCustomer(int bedId);

    //增加床位信息
    void insertBed(Bed bed);

    //删除床位信息
    void deleteBed(int bedId);

    //增加客户床位信息
    void insertBedAndCustomer(BedAndCustomer bedAndCustomer);

    //删除客户床位信息
    void deleteBedAndCustomer(int bedId);

    //修改床位信息
    void updateBed(Bed bed,int oldBedId);

    //修改床位客户信息
    void updateBedAndCustomer(BedAndCustomer bedAndCustomer);

    //获取bedandcust的最大id
    int getMaxId();


}
