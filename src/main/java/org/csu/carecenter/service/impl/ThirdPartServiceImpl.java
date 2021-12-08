package org.csu.carecenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.csu.carecenter.service.ThirdPartService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static org.csu.carecenter.constant.RedisConstant.REDIS_ORDER;
import static org.csu.carecenter.constant.RedisConstant.REDIS_RFID_LIST;


@Service
public class ThirdPartServiceImpl implements ThirdPartService {

    @Resource(name = "stringRedisTemplate")
    RedisTemplate redisTemplate;

    @Override
    public String getRfidFromRedis() {
        //根据时间戳从小到大返回
        Set<String> byScore = redisTemplate.boundZSetOps(REDIS_RFID_LIST).rangeByScore(0D, System.currentTimeMillis());

        String s = byScore.iterator().next();
        Map<String, String> map = JSON.parseObject(s, new TypeReference<Map<String, String>>() {
        });
        //删除成功读取的id
        redisTemplate.boundZSetOps(REDIS_RFID_LIST).remove(s);

        //获取最早录入的id号
        return map.get("RFID");
    }

    //问题：无法确定消息是否发送成功，没有ack
    //思路：等待一段时间后，访问redis list 是否存放数据  如果为空，再次发送请求
    @Override
    public Boolean sendOrder() throws InterruptedException {
        String s = String.valueOf(System.currentTimeMillis());
        redisTemplate.boundListOps(REDIS_ORDER).leftPush(s);
        while (true){
            Long count = redisTemplate.boundZSetOps(REDIS_RFID_LIST).count(0D, System.currentTimeMillis());
            if (!count.equals(0L)){
                //有数据
                break;
            }else {
                Thread.sleep(1000*1);
                redisTemplate.boundListOps(REDIS_ORDER).leftPush(s);
            }
        }
        //Zset中有数据
        return true;
    }
}
