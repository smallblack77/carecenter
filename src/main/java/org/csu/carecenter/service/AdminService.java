package org.csu.carecenter.service;

import org.csu.carecenter.Persistence.AdminMapper;
import org.csu.carecenter.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public Admin getAdminByNameAndPassword(String name, String password){
        Admin admin = new Admin();
        admin.setAdminName(name);
        admin.setPassword(password);
        return adminMapper.selectAdmin(admin);
    }

    public void updateAdminn(Admin admin,String oldName){
        adminMapper.updateAdmin(admin,oldName);
    }
}
