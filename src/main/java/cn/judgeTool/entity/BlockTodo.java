package cn.judgeTool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_block_todo
 */
@TableName(value ="t_block_todo")
@Data
public class BlockTodo implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String todoId;

    @TableField(exist = false)
    private TaskTodo taskTodo;

    /**
     * 
     */
    private String reason;

    /**
     * 
     */
    private String reportor;

    /**
     * 
     */
    private String checkor;

    /**
     * 
     */
    private Date time;

    private String remark;

    private int status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}