package org.csu.carecenter.Persistence;

import org.csu.carecenter.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerMapper {

    Customer getCustomer(int id);

    List<Customer> getCustomerList();

    void addCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(int id);
}
