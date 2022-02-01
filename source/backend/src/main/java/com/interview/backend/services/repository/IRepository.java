package com.interview.backend.services.repository;

import com.interview.backend.domain.TaskParse;

import java.util.Optional;
import java.util.Set;

public interface IRepository {
    Optional<TaskParse> findTaskById(Long id);

    Set<TaskParse> findAllTasks();

    TaskParse saveTask(TaskParse taskParse);

    TaskParse saveTaskStatus(TaskParse taskParse);
}
