package cn.judgeTool.service.impl;

import cn.judgeTool.entity.TaskTodo;
import cn.judgeTool.service.TaskTodoService;
import cn.judgeTool.service.UserService;
import cn.judgeTool.util.RedisUtil;
import cn.judgeTool.util.UsernameUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.judgeTool.entity.Record;
import cn.judgeTool.service.RecordService;
import cn.judgeTool.mapper.RecordMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author a2576
 * @description 针对表【t_record】的数据库操作Service实现
 * @createDate 2022-12-21 13:45:52
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record>
		implements RecordService {

	@Resource
	private RecordMapper recordMapper;

	@Resource
	private TaskTodoService taskTodoService;

	@Resource
	private RedisUtil redisUtil;

	@Resource
	private UserService userService;


	@Override
	@Transactional
	public boolean saveAndUpdateTodo(Record record) {
		//设置创建时间和更新时间一致
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		String user = UsernameUtil.getLoginUser();
		//提交人
		record.setUid(user);
		TaskTodo taskTodo = taskTodoService.getById(record.getTodoId());
		//查看状态
		if (taskTodo.getStatus() == 1) {
			//判断是标注修改还是验证
			if (!taskTodo.getUid().equals(user)) {
				taskTodo.setStatus(2);
			} else {
				taskTodo.setStatus(1);
			}
			record.setUpdateTime(new Date());
			//更新record
			recordMapper.updateById(record);
		} else {
			//首次提交
			taskTodo.setStatus(1);
			recordMapper.insert(record);
			taskTodo.setRecordId(record.getId());
		}
		return taskTodoService.updateById(taskTodo);
	}

	@Override
	public int weekCount(String uid) {
		return recordMapper.selectWeekCount(uid);
	}

	@Override
	public int dayCount(String uid) {
		return recordMapper.selectDayCount(uid);
	}

	@Override
	public String sota() {
		String key = "judge:sota";
		//查缓存
		if (redisUtil.hasKey(key)) {
			return redisUtil.get(key).toString();
		} else {
			Map<String, Object> map = recordMapper.selectSotaCount();
			if (map == null) {
				return "";
			}
//			System.out.println(map);
			String name = userService.detail(map.get("uid").toString()).getNickname();
			System.out.println(name);
			String sota = name + ":" + map.get("record_count");
			//一分钟
			redisUtil.set(key, sota, 60);
			return sota;
		}
	}
}




