package org.csu.carecenter.controller;

import org.csu.carecenter.service.ThirdPartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ThirdPartController {

    @Resource
    ThirdPartService thirdPartService;

    @GetMapping("/getRfid")
    public Map<String,String> getRfid() throws InterruptedException {

        HashMap<String, String> data = new HashMap<>();
        //TODO: 发送命令要求第三方平台读取RFID,并存放到Redis
        while (!Thread.interrupted()){
            Boolean aBoolean = thirdPartService.sendOrder();
            if(aBoolean){
                break;
            }
        }
        //返回读取到得rfid
        data.put("rfid",thirdPartService.getRfidFromRedis());
        return data;
    }
}
