package com.tianji.learning.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianji.api.client.course.CourseClient;
import com.tianji.api.dto.course.CourseSimpleInfoDTO;
import com.tianji.learning.domain.po.LearningLesson;
import com.tianji.learning.mapper.LearningLessonMapper;
import com.tianji.learning.service.ILearningLessonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 学生课程表 服务实现类
 * </p>
 *
 * @author Chen Yongsai
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class LearningLessonServiceImpl extends ServiceImpl<LearningLessonMapper, LearningLesson> implements ILearningLessonService {

    private final CourseClient courseClient;

    @Override
    public void addUserLessons(Long userId, List<Long> courseIds) {
        //1.基于CourseClient查询课程有效期
        List<CourseSimpleInfoDTO> cInfoList = courseClient.getSimpleInfoList(courseIds);
        if(CollUtil.isEmpty(cInfoList)){
            log.error("课程信息不存在，无法添加到课表");
            return;
        }
        //2.循环便利封装LearingLesson这个PO
        List<LearningLesson> learningLessons = new ArrayList<>(cInfoList.size());
        for (CourseSimpleInfoDTO cInfo : cInfoList) {
            LearningLesson learningLesson = new LearningLesson();
            Integer validDuration = cInfo.getValidDuration();
            if(validDuration !=null && validDuration > 0){
                LocalDateTime now = LocalDateTime.now();
                learningLesson.setCreateTime(now);
                learningLesson.setExpireTime(now.plusMonths(validDuration));
            }
            learningLesson.setUserId(userId);
            learningLesson.setCourseId(cInfo.getId());
            learningLessons.add(learningLesson);
        }
        //3.利用mybatisplus进行批量保存到库中
        saveBatch(learningLessons);
    }
}
