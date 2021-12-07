package org.csu.carecenter.service;

import com.baomidou.mybatisplus.extension.service.IService;

import org.csu.carecenter.common.PageUtils;
import org.csu.carecenter.entity.CustDiseRelationEntity;
import org.csu.carecenter.entity.VO.CustDiseRelationVo;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lyx
 * @email lyx@gmail.com
 * @date 2021-12-06 14:12:37
 */
public interface CustDiseRelationService extends IService<CustDiseRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 获取全部患者与患病详细信息
     * @return
     */
    List<CustDiseRelationVo> getDetailList();

    CustDiseRelationVo getVoById(Integer id);
}

