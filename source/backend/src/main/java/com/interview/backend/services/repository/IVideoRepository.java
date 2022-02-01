package com.interview.backend.services.repository;

import com.interview.backend.domain.Video;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface IVideoRepository extends CrudRepository<Video, Long> {
}
