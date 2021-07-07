package org.csu.carecenter.Controller;

import com.alipay.api.domain.AccDetailModel;
import com.alipay.api.domain.UserSubmitModel;
import org.csu.carecenter.entity.User;
import org.csu.carecenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/account")
@SessionAttributes("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userLoginForm")
    public String signon(Model model){
        return "account/userLogin";
    }


    @RequestMapping("/login")
    public String login(String userId,String password,Model model){
        if(userId != null && password != null){
            User user = userService.getUserByUserIdAndPassword(userId,password);
            if (user == null){
                String errorValue = "Invalid username or password.  Signon failed.";
                model.addAttribute("errorValue",errorValue);
                return "account/userLogin";
            }else {
                user.setPassword(null);
                model.addAttribute("user", user);
                return "account/index";
            }

      }else {
            return null;
        }
    }
}

