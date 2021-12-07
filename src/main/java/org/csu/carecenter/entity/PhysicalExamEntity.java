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
@TableName("physical_exam")
public class PhysicalExamEntity implements Serializable {
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
	private String custName;
	/**
	 * 体检报告
	 */
	private String report;
	/**
	 * 健康评估
	 */
	private String assessment;
	/**
	 * 负责医生
	 */
	private String doctorName;
	/**
	 * 注意事项
	 */
	private String note;
	/**
	 * 体检时间
	 */
	private String examTime;
}
