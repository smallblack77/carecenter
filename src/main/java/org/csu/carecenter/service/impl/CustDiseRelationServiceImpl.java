package org.csu.carecenter.service.impl;

import org.csu.carecenter.Persistence.CustDiseRelationDao;
import org.csu.carecenter.common.PageUtils;
import org.csu.carecenter.common.Query;
import org.csu.carecenter.entity.VO.CustDiseRelationVo;
import org.csu.carecenter.service.DiseaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import org.csu.carecenter.entity.CustDiseRelationEntity;
import org.csu.carecenter.service.CustDiseRelationService;




@Service("custDiseRelationService")
public class CustDiseRelationServiceImpl extends ServiceImpl<CustDiseRelationDao, CustDiseRelationEntity> implements CustDiseRelationService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CustDiseRelationEntity> page = this.page(
                new Query<CustDiseRelationEntity>().getPage(params),
                new QueryWrapper<CustDiseRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CustDiseRelationVo> getDetailList() {

        List<CustDiseRelationVo> list = baseMapper.getDetailList();

        return list;
    }

    @Override
    public CustDiseRelationVo getVoById(Integer id) {
        for (CustDiseRelationVo vo : this.getDetailList()) {
            if (vo.getId().equals(id)) return vo;
        }
        return null;
    }

}