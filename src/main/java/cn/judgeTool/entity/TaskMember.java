package cn.judgeTool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_task_member
 */
@TableName(value ="t_task_member")
@Data
public class TaskMember implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 对应的任务ID
     */
    private Integer tid;

    /**
     * 任务下的小组人员，以，隔开，用户名和id以：隔开
     */
    private String users;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}