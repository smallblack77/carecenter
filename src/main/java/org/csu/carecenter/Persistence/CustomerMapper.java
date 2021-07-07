package org.csu.carecenter.Persistence;

import org.csu.carecenter.entity.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerMapper {

    Customer getCustomer(String name);

    void addCustomer(Customer customer);
}
