package org.csu.carecenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.carecenter.common.PageUtils;
import org.csu.carecenter.entity.DiseaseEntity;

import java.util.Map;

/**
 * 
 *
 * @author lyx
 * @email lyx@gmail.com
 * @date 2021-12-06 14:12:37
 */
public interface DiseaseService extends IService<DiseaseEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

