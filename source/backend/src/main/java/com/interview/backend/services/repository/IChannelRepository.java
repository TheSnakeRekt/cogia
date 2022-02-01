package com.interview.backend.services.repository;

import com.interview.backend.domain.Channel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface IChannelRepository extends CrudRepository<Channel, Long> {
}
