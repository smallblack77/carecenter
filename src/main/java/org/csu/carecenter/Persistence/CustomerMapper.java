package org.csu.carecenter.Persistence;

import org.csu.carecenter.entity.BedAndCustomer;
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

//入住信息
    void deleteCheckin(BedAndCustomer bedAndCustomer);

    List<BedAndCustomer> selectCheckinList();

    BedAndCustomer selectCheckin(int id);

    void updateCheckin(BedAndCustomer bedAndCustomer);

    void insertCheckin(BedAndCustomer bedAndCustomer);

//退住信息
    void deleteCheckout(BedAndCustomer bedAndCustomer);

    List<BedAndCustomer> selectCheckoutList();

    BedAndCustomer selectCheckout(int id);

    void updateCheckout(BedAndCustomer bedAndCustomer);

    void insertCheckout(BedAndCustomer bedAndCustomer);
}
