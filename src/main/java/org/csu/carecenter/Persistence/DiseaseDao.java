package org.csu.carecenter.Persistence;

import org.csu.carecenter.entity.DiseaseEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author lyx
 * @email lyx@gmail.com
 * @date 2021-12-06 14:12:37
 */
@Mapper
public interface DiseaseDao extends BaseMapper<DiseaseEntity> {
	
}
