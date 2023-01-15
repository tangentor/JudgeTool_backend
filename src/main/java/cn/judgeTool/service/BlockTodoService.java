package cn.judgeTool.service;

import cn.judgeTool.entity.BlockTodo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author a2576
* @description 针对表【t_block_todo】的数据库操作Service
* @createDate 2023-01-10 15:42:23
*/
public interface BlockTodoService extends IService<BlockTodo> {

	List<BlockTodo> listByUid(String uid);

	boolean insertOrUpdate(BlockTodo blockTodo);
}
