package org.csu.carecenter.Persistence;

import org.csu.carecenter.entity.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {

    Admin selectAdmin(Admin admin);
}
