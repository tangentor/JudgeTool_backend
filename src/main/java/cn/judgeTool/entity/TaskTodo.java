package cn.judgeTool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_task_todo
 */
@TableName(value ="t_task_todo")
@Data
public class TaskTodo implements Serializable,Cloneable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String groupKey;

    /**
     * 
     */
    private String uid;

    /**
     * 对应的taskid
     */
    private Integer tid;

    @Override
    public TaskTodo clone() throws CloneNotSupportedException {
        TaskTodo clone = (TaskTodo)super.clone();
        clone.uid = this.uid;
        return clone;
    }

    /**
     * 对应的标注文本id
     */
    private String paperId;

    /**
     * 0未处理 1已自我标记 2已交换标记
     */
    private Integer status;

    /**
     * 已标记后对应的记录
     */
    private Integer recordId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}