package org.csu.carecenter.service;

import com.baomidou.mybatisplus.extension.service.IService;

import org.csu.carecenter.common.PageUtils;
import org.csu.carecenter.entity.PhysicalExamEntity;

import java.util.Map;

/**
 * 
 *
 * @author lyx
 * @email lyx@gmail.com
 * @date 2021-12-06 22:37:23
 */
public interface PhysicalExamService extends IService<PhysicalExamEntity> {

    PageUtils queryPage(Map<String, Object> params);

    int saveNew(Integer custId, String assessment, String doctorName, String note, String examTime, String report);
}

