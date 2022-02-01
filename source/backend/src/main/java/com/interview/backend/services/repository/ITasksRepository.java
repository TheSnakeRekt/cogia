package com.interview.backend.services.repository;

import com.interview.backend.domain.TaskParse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface ITasksRepository extends CrudRepository<TaskParse, Long> {
}
