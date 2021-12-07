package org.csu.carecenter.Persistence;

import org.csu.carecenter.entity.ContractEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author lyx
 * @email lyx@gmail.com
 * @date 2021-12-02 13:59:09
 */
@Mapper
public interface ContractDao extends BaseMapper<ContractEntity> {
	
}
