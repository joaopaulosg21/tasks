package projeto.api.tasks.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import projeto.api.tasks.domain.task.Task;
import projeto.api.tasks.domain.task.TaskDataDTO;
import projeto.api.tasks.domain.task.TaskService;

import java.net.URI;

@RestController
@RequestMapping("/task/")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Task> add(@Valid @RequestBody TaskDataDTO data, UriComponentsBuilder builder) {
        Task task = service.add(data);
        URI uri = builder.path("/task/{id}").buildAndExpand(task.getId()).toUri();

        return ResponseEntity.created(uri).body(task);
    }

}
