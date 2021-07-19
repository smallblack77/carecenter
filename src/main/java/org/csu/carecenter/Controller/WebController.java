package org.csu.carecenter.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class WebController {

    @GetMapping("/center")
    public String center()
    {
        return "First/center";
    }
    @GetMapping("/center2")
    public String center2()
    {
        return "First/center2";
    }
    @GetMapping("/center3")
    public String center3()
    {
        return "First/center3";
    }
    @GetMapping("/center4")
    public String center4()
    {
        return "First/center4";
    }
    @GetMapping("/center5")
    public String center5()
    {
        return "First/center5";
    }
    @GetMapping("/index")
    public String index()
    {
        return "First/index";
    }
    @GetMapping("/learn")
    public String learn()
    {
        return "First/learn";
    }
    @GetMapping("/learn1")
    public String learn1()
    {
        return "First/learn1";
    }
    @GetMapping("/organization")
    public String organization()
    {
        return "First/organization";
    }
    @GetMapping("/theorystudy")
    public String theorystudy()
    {
        return "First/theorystudy";
    }
    @GetMapping("/topic")
    public String topic()
    {
        return "First/topic";
    }
    @GetMapping("/id21")
    public String id21()
    {
        return "First/id21";
    }
    @GetMapping("/social")
    public String social()
    {
        return "First/social";
    }

}
