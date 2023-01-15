package cn.judgeTool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

/**
 * 
 * @TableName t_record
 */
@TableName(value ="t_record")
@Data
@ToString
public class Record implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String caseName;

    /**
     * 
     */
    private String caseFacts;

    /**
     * 
     */
    private String eventType;

    /**
     * 
     */
    private Integer typeId;

    /**
     * 
     */
    private String triggers;

    /**
     * 
     */
    private String arguments;

    /**
     * 
     */
    private String caseJudgement;

    /**
     * 
     */
    private Date createTime;

    /**
     * 首次创建记录的用户
     */
    private String uid;

    /**
     * 对应的任务id
     */
    private Integer tid;

    /**
     * 最后一次修改
     */
    private Date updateTime;

    /**
     * 暂定
     */
    private Integer status;

    private Integer todoId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}