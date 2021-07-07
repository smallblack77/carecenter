package org.csu.carecenter.service;

import org.csu.carecenter.Persistence.CustomerMapper;
import org.csu.carecenter.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    public Customer getCustomer(String name){
        return customerMapper.getCustomer(name);
    }
}
