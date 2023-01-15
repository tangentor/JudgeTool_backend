package cn.judgeTool.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.judgeTool.entity.TaskMember;
import cn.judgeTool.service.TaskMemberService;
import cn.judgeTool.mapper.TaskMemberMapper;
import org.springframework.stereotype.Service;

/**
* @author a2576
* @description 针对表【t_task_member】的数据库操作Service实现
* @createDate 2022-12-17 21:52:16
*/
@Service
public class TaskMemberServiceImpl extends ServiceImpl<TaskMemberMapper, TaskMember>
    implements TaskMemberService{

}




