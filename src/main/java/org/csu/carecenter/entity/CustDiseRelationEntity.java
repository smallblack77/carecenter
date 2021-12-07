package org.csu.carecenter.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author lyx
 * @email lyx@gmail.com
 * @date 2021-12-06 14:12:37
 */
@Data
@TableName("cust_dise_relation")
public class CustDiseRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Integer diseaseId;
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

}
