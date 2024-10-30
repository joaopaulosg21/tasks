package projeto.api.tasks.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import projeto.api.tasks.domain.task.ResponseDTO;
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

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@Valid @RequestBody TaskDataDTO data, @PathVariable Long id) {
        return ResponseEntity.ok(service.update(data,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
