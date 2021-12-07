package org.csu.carecenter.service.impl;

import org.csu.carecenter.Persistence.PhysicalExamDao;
import org.csu.carecenter.common.PageUtils;
import org.csu.carecenter.common.Query;
import org.csu.carecenter.entity.Customer;
import org.csu.carecenter.service.CustomerService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.csu.carecenter.entity.PhysicalExamEntity;
import org.csu.carecenter.service.PhysicalExamService;

import javax.annotation.Resource;


@Service("physicalExamService")
public class PhysicalExamServiceImpl extends ServiceImpl<PhysicalExamDao, PhysicalExamEntity> implements PhysicalExamService {


    @Resource
    CustomerService customerService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PhysicalExamEntity> page = this.page(
                new Query<PhysicalExamEntity>().getPage(params),
                new QueryWrapper<PhysicalExamEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public int saveNew(Integer custId, String assessment, String doctorName, String note, String examTime, String report) {

        Customer customer = customerService.getById(custId.toString());
        if (customer!=null){
            //保存体检记录
            PhysicalExamEntity entity = new PhysicalExamEntity();
            entity.setCustId(custId);
            entity.setCustName(customer.getName());
            entity.setAssessment(assessment);
            entity.setDoctorName(doctorName);
            entity.setNote(note);
            entity.setExamTime(examTime);
            entity.setReport(report);
            boolean save = this.save(entity);
            if (save){
                //保存成功
                return 1;
            }else {
                return 0;
            }
        }else {
            //用户不存在
            return 0;
        }
    }

}