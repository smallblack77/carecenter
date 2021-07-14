package org.csu.carecenter.service;

import org.csu.carecenter.Persistence.CustomerMapper;
import org.csu.carecenter.Persistence.OutMapper;
import org.csu.carecenter.Persistence.TimeLineMapper;
import org.csu.carecenter.entity.BedAndCustomer;
import org.csu.carecenter.entity.Customer;
import org.csu.carecenter.entity.Out;
import org.csu.carecenter.entity.TimeLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private OutMapper outMapper;

    @Autowired
    private TimeLineMapper timeLineMapper;

    //时间线
    public  List<TimeLine> getAllTimeLine(){
        return timeLineMapper.getAllTimeLine();
    }

    public List<TimeLine> getTimeLineById(int custId){
        return timeLineMapper.getTimeLineById(custId);
    }

    public List<String> getDayList(int id){
        return timeLineMapper.getDayList(id);
    }

    public void insertTimeLine(TimeLine timeLine){
        timeLineMapper.insertTimeLime(timeLine);
    }

    //客户本身信息
    public Customer getCustomer(int id){
        return customerMapper.getCustomer(id);
    }

    public List<Customer> getCustomerLsit(){
        return customerMapper.getCustomerList();
    }

    public void addCustomer(Customer customer){
        customerMapper.addCustomer(customer);
    }

    public void updateCustomer(Customer customer){
        customerMapper.updateCustomer(customer);
    }

    public void deleteCustomer(int id){
        customerMapper.deleteCustomer(id);
    }

    public int getCustomerId(String name){
        return customerMapper.getCustomerIdByNameAndPhone(name);
    }

    //入住信息
    public List<BedAndCustomer> selectCheckinList(){
        return customerMapper.selectCheckinList();
    }

    public BedAndCustomer selectCheckin(int id){
        return customerMapper.selectCheckin(id);
    }

    public void deleteCheckin(int id){
        customerMapper.deleteCheckin(id);
    }

    public void updateCheckin(BedAndCustomer bedAndCustomer){
        customerMapper.updateCheckin(bedAndCustomer);
    }

    public void addCheckin(BedAndCustomer bedAndCustomer) {
        customerMapper.insertCheckin(bedAndCustomer);
    }



    //退住信息
    public List<BedAndCustomer> selectCheckoutList(){
        return customerMapper.selectCheckoutList();
    }

    public BedAndCustomer selectCheckout(int id){
        return customerMapper.selectCheckout(id);
    }

    public void updateCheckout(BedAndCustomer bedAndCustomer){
        customerMapper.updateCheckout(bedAndCustomer);
    }

    public void deleteCheckout(BedAndCustomer bedAndCustomer){
        customerMapper.deleteCheckout(bedAndCustomer);
    }

    public void addCheckout(BedAndCustomer bedAndCustomer){
        customerMapper.insertCheckout(bedAndCustomer);
    }


    //外出信息
    public List<Out> getOutList(int custid){
        return outMapper.getOutList(custid);
    }

    public Out getOut(int id){
        return outMapper.getOut(id);
    }

    public List<Out> getAllOutList(){
        return outMapper.getAllOutList();
    }

    public void addOut(Out out){
        outMapper.insertOut(out);
    }

    public void deleteOut(int id){
        outMapper.deleteOut(id);
    }

    public void updateOut(Out out){
        outMapper.updateOut(out);
    }
}
