package org.csu.carecenter.service;

import com.baomidou.mybatisplus.extension.service.IService;

import org.csu.carecenter.common.PageUtils;
import org.csu.carecenter.entity.MedicationRecordEntity;
import org.csu.carecenter.entity.VO.MediRecdVo;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lyx
 * @email lyx@gmail.com
 * @date 2021-12-06 22:37:23
 */
public interface MedicationRecordService extends IService<MedicationRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<MediRecdVo> getVoList();

    boolean saveSafe(MedicationRecordEntity entity);
}

