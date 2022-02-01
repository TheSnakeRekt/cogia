package dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

@JsonSerialize
public class ParseTaskDTO implements Serializable {


    public Long taskId;
    public String status;
    public String channelName;
    public String channelId;

    @JsonIgnore
    public ChannelDTO channel;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    @JsonIgnore
    public ChannelDTO getChannel() {
        return channel;
    }

    @JsonIgnore
    public void setChannel(ChannelDTO channel) {
        this.channel = channel;
        if(this.channel != null){
            this.channelName =  this.channel.getName();
            this.channelId = this.channel.getId();
        }
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
