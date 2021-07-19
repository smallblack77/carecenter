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

    @GetMapping("/social")
    public String social()
    {
        return "First/social";
    }

    @GetMapping("/id21")
    public String id21()
    {
        return "First/id21";
    }
    @GetMapping("/id22")
    public String id22()
    {
        return "First/id22";
    }
    @GetMapping("/id23")
    public String id23()
    {
        return "First/id23";
    }
    @GetMapping("/id24")
    public String id24()
    {
        return "First/id24";
    }
    @GetMapping("/id31")
    public String id31()
    {
        return "First/id31";
    }
    @GetMapping("/id32")
    public String id32()
    {
        return "First/id32";
    }
    @GetMapping("/id41")
    public String id41()
    {
        return "First/id41";
    }
    @GetMapping("/id42")
    public String id42()
    {
        return "First/id42";
    }
    @GetMapping("/id51")
    public String id51()
    {
        return "First/id51";
    }
    @GetMapping("/id52")
    public String id52()
    {
        return "First/id52";
    }
    @GetMapping("/id53")
    public String id53()
    {
        return "First/id53";
    }
    @GetMapping("/id54")
    public String id54()
    {
        return "First/id54";
    }
    @GetMapping("/id61")
    public String id61()
    {
        return "First/id61";
    }

}
