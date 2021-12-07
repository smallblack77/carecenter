package org.csu.carecenter.entity.VO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * 
 * @author lyx
 * @email lyx@gmail.com
 * @date 2021-12-06 14:12:37
 */
@Data
@TableName("cust_dise_relation")
public class CustDiseRelationVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Integer custId;
	/**
	 * 病情程度
	 */
	private String level;
	/**
	 * 患病时长
	 */
	private String duration;
	/**
	 * 备注
	 */
	private String remarks;

	private String custName;

	private String diseaseId;

	private String diseaseName;

}
