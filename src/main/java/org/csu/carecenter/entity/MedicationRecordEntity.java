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
 * @date 2021-12-06 22:37:23
 */
@Data
@TableName("medication_record")
public class MedicationRecordEntity implements Serializable {
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
	 * 
	 */
	private Integer nurId;
	/**
	 * 药品名称
	 */
	private String medicine;
	/**
	 * 用量
	 */
	private String dosage;
	/**
	 * 用药状态
	 */
	private String condit;
	/**
	 * 服用时间
	 */
	private String  takeTime;


}
