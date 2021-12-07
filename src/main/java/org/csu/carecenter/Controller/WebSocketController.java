package org.csu.carecenter.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Slf4j
@Controller
@SessionAttributes("web")
public class WebSocketController {

    @RequestMapping("/view")
    public String view(@RequestParam("username") String name, Model model) {
        System.out.println(name);
        log.info("跳转到websocket的页面上");
        String url = "redirect:/websocket/"+name;
        System.out.println(url);
        return url;

    }

    @RequestMapping("/websocket/{username}")
    public String webSocket(@PathVariable String username, Model model) {
        try {
            log.info("跳转到websocket/username的页面上");
            model.addAttribute("username", username);
            return "websocket";
        } catch (Exception e) {
            log.info("跳转到websocket的页面上发生异常，异常信息是：" + e.getMessage());
            return "error";
        }
    }
}