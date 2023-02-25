package cn.judgeTool.service.impl;

import cn.judgeTool.entity.BlockTodo;
import cn.judgeTool.entity.Task;
import cn.judgeTool.entity.TaskTodo;
import cn.judgeTool.mapper.BlockTodoMapper;
import cn.judgeTool.service.BlockTodoService;
import cn.judgeTool.service.TaskService;
import cn.judgeTool.service.TaskTodoService;
import cn.judgeTool.util.UsernameUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.xml.transform.Source;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author a2576
* @description 针对表【t_block_todo】的数据库操作Service实现
* @createDate 2023-01-10 15:42:23
*/
@Service
public class BlockTodoServiceImpl extends ServiceImpl<BlockTodoMapper, BlockTodo>
    implements BlockTodoService{

	@Resource
	private TaskService taskService;

	@Resource
	private TaskTodoService taskTodoService;

	@Override
	public List<BlockTodo> listByUid(String uid) {
		LambdaQueryWrapper<BlockTodo> wrapper = new LambdaQueryWrapper<>();
		wrapper.like(BlockTodo::getReportor,uid)
				.or()
				.like(BlockTodo::getCheckor,uid)
				.orderByDesc(BlockTodo::getTime);
		List<BlockTodo> list = this.list(wrapper);
		list.forEach(v->v.setTaskTodo(taskTodoService.getById(v.getTodoId())));
		return list;
	}

	@Override
	@Transactional
	public boolean insertOrUpdate(BlockTodo blockTodo) {
		String uid = UsernameUtil.getLoginUser();
		if(blockTodo.getReportor()==null){
			//初次新增
			//设置为待处理状态
			blockTodo.setStatus(1);
			//根据tasktodo
			Task task = taskService.getByTodoId(blockTodo.getTodoId());
			//设置人员
			//判断是否正确
			if(!task.getMarkman().contains(uid)){
				throw new RuntimeException("用户与任务不匹配");
			}
			blockTodo.setReportor(task.getMarkman());
			blockTodo.setCheckor(task.getValidman());
		} else {
			//完成
			if(blockTodo.getRemark()==null){
				//pass
				blockTodo.setRemark("通过");
				blockTodo.setStatus(2);
				//设置tasktodo为-1，表示被锁
				taskTodoService.update(new LambdaUpdateWrapper<TaskTodo>().set(TaskTodo::getStatus, -1).eq(TaskTodo::getId, blockTodo.getTodoId()));
			} else {
                blockTodo.setStatus(-1);
			}
		}
		blockTodo.setTime(new Date());
		return this.saveOrUpdate(blockTodo);
	}

	@Override
	public Page<BlockTodo> getPage(int pageNum) {
		Page<BlockTodo> page = new Page<>(pageNum, 15);
		String uid = UsernameUtil.getLoginUser();
		LambdaQueryWrapper<BlockTodo> wrapper = new LambdaQueryWrapper<>();
		wrapper.like(BlockTodo::getReportor,uid)
				.or()
				.like(BlockTodo::getCheckor,uid)
				.orderByDesc(BlockTodo::getTime);
		//返回所有结果
		Page<BlockTodo> blockTodoPage = this.getBaseMapper().selectPage(page, wrapper);
		//填充todo信息
		List<BlockTodo> records = blockTodoPage.getRecords();
		records.forEach(v-> v.setTaskTodo(taskTodoService.getById(v.getTodoId())));
		blockTodoPage.setRecords(records);
		return blockTodoPage;
	}
}




