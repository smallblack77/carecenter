package org.csu.carecenter.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainWebController {

    @GetMapping("/mainWeb")
    public String viewMain()
    {
        return "index";
    }

    @GetMapping("/chatRoom")
    public String chatRoom()
    {
        return "websocket";
    }
}
