package org.csu.carecenter.Persistence;

import org.csu.carecenter.entity.MedicationRecordEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.csu.carecenter.entity.VO.MediRecdVo;

import java.util.List;

/**
 * 
 * 
 * @author lyx
 * @email lyx@gmail.com
 * @date 2021-12-06 22:37:23
 */
@Mapper
public interface MedicationRecordDao extends BaseMapper<MedicationRecordEntity> {

    List<MediRecdVo> getVoList();

}
