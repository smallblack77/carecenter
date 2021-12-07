package org.csu.carecenter.service.impl;

import org.csu.carecenter.Persistence.MedicationRecordDao;
import org.csu.carecenter.common.PageUtils;
import org.csu.carecenter.common.Query;
import org.csu.carecenter.entity.Customer;
import org.csu.carecenter.entity.NurseContent;
import org.csu.carecenter.entity.VO.MediRecdVo;
import org.csu.carecenter.service.CustomerService;
import org.csu.carecenter.service.NurseContentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import org.csu.carecenter.entity.MedicationRecordEntity;
import org.csu.carecenter.service.MedicationRecordService;

import javax.annotation.Resource;


@Service("medicationRecordService")
public class MedicationRecordServiceImpl extends ServiceImpl<MedicationRecordDao, MedicationRecordEntity> implements MedicationRecordService {

    @Resource
    CustomerService customerService;

    @Resource
    NurseContentService nurseContentService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MedicationRecordEntity> page = this.page(
                new Query<MedicationRecordEntity>().getPage(params),
                new QueryWrapper<MedicationRecordEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<MediRecdVo> getVoList() {
        return this.baseMapper.getVoList();
    }

    @Override
    public boolean saveSafe(MedicationRecordEntity entity) {

        Customer customer = customerService.getById(entity.getCustId().toString());
        NurseContent nurseContent = nurseContentService.getNurById(entity.getNurId().toString());
        if (customer!=null&&nurseContent!=null){
            System.out.println(entity.getTakeTime());
            int i = this.baseMapper.insert(entity);
            return i > 0;
        }else {
            return false;
        }
    }

}