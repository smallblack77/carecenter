package org.csu.carecenter.ControllerForWX;

import org.csu.carecenter.entity.CustAndSon;
import org.csu.carecenter.entity.Customer;
import org.csu.carecenter.service.CustAndSonService;
import org.csu.carecenter.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/custAndSon")
public class CustAndSunController {

    @Autowired
    private CustAndSonService custAndSonService;
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/insert")
    @ResponseBody
    public CustAndSon insertCustAndSon(String name, String phone, String wechat){



        CustAndSon custAndSon = new CustAndSon();
        custAndSon.setName(name);
        custAndSon.setWechat(wechat);
        custAndSon.setPhone(phone);

        CustAndSon custAndSon1 = custAndSonService.getCustAndSon(custAndSon);
        if(custAndSon1 == null){
            custAndSonService.insertRelation(custAndSon);
        }

        return custAndSon;

    }
}
