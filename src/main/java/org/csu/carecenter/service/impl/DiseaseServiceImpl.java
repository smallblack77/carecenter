package org.csu.carecenter.service.impl;

import org.csu.carecenter.Persistence.DiseaseDao;
import org.csu.carecenter.common.PageUtils;
import org.csu.carecenter.common.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.csu.carecenter.entity.DiseaseEntity;
import org.csu.carecenter.service.DiseaseService;


@Service("diseaseService")
public class DiseaseServiceImpl extends ServiceImpl<DiseaseDao, DiseaseEntity> implements DiseaseService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DiseaseEntity> page = this.page(
                new Query<DiseaseEntity>().getPage(params),
                new QueryWrapper<DiseaseEntity>()
        );

        return new PageUtils(page);
    }

}