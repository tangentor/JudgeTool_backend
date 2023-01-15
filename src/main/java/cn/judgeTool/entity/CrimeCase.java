package cn.judgeTool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_crime_case
 */
@TableName(value ="t_crime_case")
@Data
public class CrimeCase implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 罪名
     */
    private String crime;

    /**
     * 案件详情ID
     */
    private String detailId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}