package org.csu.carecenter.controller;

import cn.hutool.core.lang.hash.Hash;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Controller
public class OssController {


    @Value("${alibaba.cloud.oss.endpoint}")
    private String endpoint;

    @Value("${alibaba.cloud.access-key}")
    private String accessId;
    @Value("${alibaba.cloud.secret-key}")
    private String accessKeySecret;

    @Value("${alibaba.cloud.oss.bucket}")
    private String bucket;


    @GetMapping("/test.html")
    public String test() {
        return "test2";
    }


    @ResponseBody
    @RequestMapping("/oss/policy")
    public Map<String,Object> policy(@RequestParam("file") MultipartFile multipartFile) {
        String host = "https://" + bucket + "." + endpoint; // host的格式为 bucketname.endpoint
        // callbackUrl为上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
        // String callbackUrl = "http://88.88.88.88:8888";
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dir = format + "/"; // 用户上传文件时指定的前缀。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessId, accessKeySecret);
        Map<String, Object> map = new HashMap<>();
//        Map<String, String> respMap = null;
        try {
//            long expireTime = 30;
//            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
//            Date expiration = new Date(expireEndTime);
//            // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
//            PolicyConditions policyConds = new PolicyConditions();
//            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
//            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
//
//            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
//            byte[] binaryData = postPolicy.getBytes("utf-8");
//            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
//            String postSignature = ossClient.calculatePostSignature(postPolicy);
//
//            respMap = new LinkedHashMap<String, String>();
//            respMap.put("accessid", accessId);
//            respMap.put("policy", encodedPolicy);
//            respMap.put("signature", postSignature);
//            respMap.put("dir", dir);
//            respMap.put("host", host);
//            respMap.put("expire", String.valueOf(expireEndTime / 1000));
//            // respMap.put("expire", formatISO8601Date(expiration));

            InputStream inputStream = multipartFile.getInputStream();
            String originalFilename = multipartFile.getOriginalFilename();
            String fileUrl = dir + originalFilename;

            //上传
            ossClient.putObject(bucket, fileUrl, inputStream);
            log.info(host + "/" + fileUrl);
            map.put("code",1);
            map.put("data",host + "/" + fileUrl);
            return map;
        } catch (Exception e) {
            // Assert.fail(e.getMessage());
            System.out.println(e.getMessage());
            return map;
        } finally {
            ossClient.shutdown();
        }
//        return R.ok().put("data",respMap);
    }
}
