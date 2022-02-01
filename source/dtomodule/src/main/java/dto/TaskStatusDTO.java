package dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

@JsonSerialize
public class TaskStatusDTO implements Serializable {
    public Long id;
    public String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
