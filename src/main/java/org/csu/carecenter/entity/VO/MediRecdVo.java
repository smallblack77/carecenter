package org.csu.carecenter.entity.VO;

import lombok.Data;



@Data
public class MediRecdVo {

    private Integer id;

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
    private String takeTime;

    private String custName;
    private String nurName;
}
