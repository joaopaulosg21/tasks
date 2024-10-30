package projeto.api.tasks.domain.task;

import org.springframework.stereotype.Service;
import projeto.api.tasks.domain.task.validations.TaskValidation;

import java.util.List;
import java.util.Optional;

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

    public ResponseDTO update(TaskDataDTO data, Long id) {
        this.validations.forEach(v -> v.valid(data));
        Task task = this.findById(id);

        task.setName(data.name());
        task.setCost(data.cost());
        task.setDeadline(data.deadline());

        Task updatedTask = repository.save(task);
        return new ResponseDTO(updatedTask, "Tarefa atualizada com sucesso");
    }

    private Task findById(Long id) {
        Optional<Task> optionalTask = repository.findById(id);

        if(optionalTask.isEmpty()) {
            throw new TaskNotFoundException("Tarefa com id solicitado não registrada");
        }

        return optionalTask.get();
    }
}
