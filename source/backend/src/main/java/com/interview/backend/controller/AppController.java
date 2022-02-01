package com.interview.backend.controller;


import com.interview.backend.services.facade.tasks.ITasksFacade;
import dto.ChannelDTO;
import dto.ParseTaskDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/tasks")
public class AppController {

    private final ITasksFacade tasksFacade;

    public AppController(ITasksFacade tasksFacade) {
        this.tasksFacade = tasksFacade;
    }

    @RequestMapping(value = "/{youtubeChannelId}", method = RequestMethod.POST)
    public long insertTask(@PathVariable("youtubeChannelId") String youtubeChannelId) {
        return this.tasksFacade.insertTask(youtubeChannelId);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<ParseTaskDTO> getAllTasks() {
        return this.tasksFacade.getAllTasks();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ChannelDTO getTaskById(@PathVariable("id") Long id) {
        return this.tasksFacade.getChannelByTaskId(id);
    }
}
