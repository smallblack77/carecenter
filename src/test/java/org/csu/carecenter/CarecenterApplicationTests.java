package org.csu.carecenter;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import org.csu.carecenter.Persistence.OutMapper;
import org.csu.carecenter.entity.*;
import org.csu.carecenter.service.*;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.DatabaseMetaData;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@MapperScan("org.csu.carecenter.Persistence")

class CarecenterApplicationTests {

    @Resource
    MedicationRecordService recordService;

    @Autowired
    UserService userService ;

    @Autowired
    CustomerService customerService;

    @Autowired
    AdminService adminService;

    @Value("${alibaba.cloud.oss.endpoint}")
    private String endpoint;

    @Value("${alibaba.cloud.access-key}")
    private String accessId;
    @Value("${alibaba.cloud.secret-key}")
    private String accessKeySecret;

    @Value("${alibaba.cloud.oss.bucket}")
    private String bucket;
    @Test
    void contextLoads() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("E:\\log\\1.jpg");


        OSS ossClient = new OSSClientBuilder().build(endpoint, accessId, accessKeySecret);
        ossClient.putObject(bucket,"1.jpg",inputStream);

        ossClient.shutdown();

        System.out.println("上传完成...");

    }

    @Test
    void testCustomer(){
        //查找客户
        Customer customer = customerService.getCustomer(2);
        System.out.println(customer.getSex()+" "+customer.getHeight()+" "+customer.getBirthday());


        //新增客户
//        Customer customer = new Customer();
//        customer.setId("2");
//        customer.setName("张思");
//        customer.setSex("男");
//        customer.setAge(56);
//        customer.setBirthday("1965-2-7");
//        customer.setHeight(186.0);
//        customer.setWeight(130.2);
//        customer.setBedNum(1);
//        customer.setAttention("花生过敏");
//        customerService.addCustomer(customer);
    }

    @Test
    void testOut(){

        //查找外出记录
        List<Out> outList = customerService.getOutList(1);
        for (int i = 0; i < outList.size(); i++){
            System.out.println(outList.get(i).getCustomerId() + " " + outList.get(i).getActualReturnTime());
        }


        //新增Out
        Out out = new Out();
        out.setCustomerId(1);
        out.setReason("看儿女");

        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        out.setStartTime(df.format(date));

        //在某一个时间点上加两小时的写法
        Calendar calendar = Calendar.getInstance();
        //此处setTime为Date类型
        calendar.setTime(date);
        //加上两小时
        calendar.add(Calendar.HOUR, 2);//时

        out.setExpectReturnTime(df.format(calendar.getTime()));

        out.setActualReturnTime(df.format(calendar.getTime()));

        out.setAirPhone("123456");

        customerService.addOut(out);
    }

    @Test
    void tesAdmin(){
        Admin adminByNameAndPassword = adminService.getAdminByNameAndPassword("admin", "admin");
        System.out.println(adminByNameAndPassword);
    }

    @Test
    void testMedi(){

        MedicationRecordEntity entity = new MedicationRecordEntity();
        entity.setCustId(2);
        entity.setNurId(2);
        entity.setMedicine("medicine");
        entity.setDosage("dosage");
        entity.setCondit("condition");
        entity.setTakeTime("takeTime");
        boolean b = recordService.save(entity);
        System.out.println(b);
    }



}
