package com.interview.backend.domain;


import com.interview.backend.domain.enums.ETaskStatus;

import javax.persistence.*;

@Entity
public class TaskParse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Channel channel;

    @Enumerated(value = EnumType.STRING)
    private ETaskStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ETaskStatus getStatus() {
        return status;
    }

    public void setStatus(ETaskStatus status) {
        this.status = status;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
