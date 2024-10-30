package projeto.api.tasks.domain.task;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task add(TaskRegisterDataDTO data) {
        Task task = new Task(data,repository.getNextPresentationOrder());

        return repository.save(task);
    }
}
