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
 * @date 2021-12-02 13:59:09
 */
@Data
@TableName("contract")
public class ContractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Integer custid;
	/**
	 * 
	 */
	private String manageLevel;
	/**
	 * 
	 */
	private String nurseLevel;
	/**
	 * 
	 */
	private String url;
	/**
	 * 
	 */
	private Date createdTime;

}
