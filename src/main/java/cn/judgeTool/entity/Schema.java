package cn.judgeTool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_schema
 */
@TableName(value ="t_schema")
@Data
public class Schema implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    @TableField("`trigger`")
    private String trigger;

    /**
     * 
     */
    private String argument;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}