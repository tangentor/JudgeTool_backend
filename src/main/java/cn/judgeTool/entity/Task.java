package cn.judgeTool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_task
 */
@TableName(value ="t_task")
@Data
public class Task implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 标注人的id以及名字，用:分割
     */
    private String markman;

    /**
     * 验证人的id以及名字，用:分割
     */
    private String validman;

    /**
     * 0表示正在标注，1表示标注完成待验证，2表示验证完成
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}