package org.csu.carecenter.Persistence;

import org.csu.carecenter.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User getUserByUserIdAndPassword(User user);

}
