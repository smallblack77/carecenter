package org.csu.carecenter.Persistence;

import org.csu.carecenter.entity.CustDiseRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.csu.carecenter.entity.VO.CustDiseRelationVo;

import java.util.List;

/**
 * 
 * 
 * @author lyx
 * @email lyx@gmail.com
 * @date 2021-12-06 14:12:37
 */
@Mapper
public interface CustDiseRelationDao extends BaseMapper<CustDiseRelationEntity> {

    List<CustDiseRelationVo> getDetailList();

}
