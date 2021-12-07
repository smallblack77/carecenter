package org.csu.carecenter.service;

import com.baomidou.mybatisplus.extension.service.IService;

import org.csu.carecenter.common.PageUtils;
import org.csu.carecenter.entity.ContractEntity;

import java.util.Map;

/**
 * 
 *
 * @author lyx
 * @email lyx@gmail.com
 * @date 2021-12-02 13:59:09
 */
public interface ContractService extends IService<ContractEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

