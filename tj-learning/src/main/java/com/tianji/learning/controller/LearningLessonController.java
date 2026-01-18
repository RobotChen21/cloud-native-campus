package com.tianji.learning.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tianji.learning.service.ILearningLessonService;
import com.tianji.learning.domain.po.LearningLesson;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 学生课程表 控制器
 * </p>
 *
 * @author Chen Yongsai
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/learningLesson")
public class LearningLessonController {

    private final ILearningLessonService learningLessonService;


}
