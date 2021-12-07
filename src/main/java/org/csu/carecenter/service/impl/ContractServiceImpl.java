package org.csu.carecenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.csu.carecenter.Persistence.ContractDao;
import org.csu.carecenter.common.PageUtils;
import org.csu.carecenter.common.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.csu.carecenter.entity.ContractEntity;
import org.csu.carecenter.service.ContractService;


@Service("contractService")
public class ContractServiceImpl extends ServiceImpl<ContractDao, ContractEntity> implements ContractService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ContractEntity> page = this.page(
                new Query<ContractEntity>().getPage(params),
                new QueryWrapper<ContractEntity>()
        );

        return new PageUtils(page);
    }

}