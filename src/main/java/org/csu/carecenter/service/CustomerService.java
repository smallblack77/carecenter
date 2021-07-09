package org.csu.carecenter.service;

import org.csu.carecenter.Persistence.CustomerMapper;
import org.csu.carecenter.Persistence.OutMapper;
import org.csu.carecenter.entity.Customer;
import org.csu.carecenter.entity.Out;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private OutMapper outMapper;

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

    //入住信息

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
