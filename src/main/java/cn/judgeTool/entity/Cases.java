package cn.judgeTool.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 
 * @TableName t_cases
 */
@TableName(value ="t_cases")
@Data
@ToString
public class Cases implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private String crime;

    /**
     * 
     */
    private String caseNo;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private String events;

    /**
     * 
     */
    private String typevector;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}