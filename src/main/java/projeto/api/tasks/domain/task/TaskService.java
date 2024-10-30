package projeto.api.tasks.domain.task;

import org.springframework.stereotype.Service;
import projeto.api.tasks.domain.task.validations.TaskValidation;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    private List<TaskValidation> validations;

    public TaskService(TaskRepository repository, List<TaskValidation> validations) {
        this.repository = repository;
        this.validations = validations;
    }

    public Task add(TaskDataDTO data) {
        Task task = new Task(data,repository.getNextPresentationOrder());
        this.validations.forEach(v -> v.valid(data));
        return repository.save(task);
    }
}
