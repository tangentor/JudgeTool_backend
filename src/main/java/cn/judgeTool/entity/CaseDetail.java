package cn.judgeTool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_case_detail
 */
@TableName(value ="t_case_detail")
@Data
public class CaseDetail implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String paperId;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String baseInfo;

    /**
     * 
     */
    private String analysisProcess;

    /**
     * 
     */
    private String result;

    /**
     * 
     */
    private String paperName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}