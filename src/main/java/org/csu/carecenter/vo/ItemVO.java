package org.csu.carecenter.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.PrintWriter;
import java.math.BigDecimal;

@Data
public class ItemVO {
    //item表中的字段
    private String itemId;
    private String productId;
    private BigDecimal listPrice;
    private BigDecimal unitCost;
    private int supplierId;
    private String status;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;

    //item所需product的属性
    private String categoryId;
    private String productName;
    private String productDescription;

    //item所需inventory的属性
    private int quantity;
}
