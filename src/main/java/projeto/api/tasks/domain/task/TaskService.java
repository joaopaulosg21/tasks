package projeto.api.tasks.domain.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        Optional<Task> optionalTask = repository.tasksByPresentation();
        long value = 0;
        if (optionalTask.isPresent()) {
            value = optionalTask.get().getPresentationOrder() + 1;
        }
        Task task = new Task(data, value);
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
        return new ResponseDTO(updatedTask, "Tarefa atualizada com sucesso!!");
    }

    private Task findById(Long id) {
        Optional<Task> optionalTask = repository.findById(id);

        if (optionalTask.isEmpty()) {
            throw new TaskNotFoundException("Tarefa com id solicitado não registrada");
        }

        return optionalTask.get();
    }

    public ResponseDTO delete(Long id) {
        Task task = this.findById(id);
        repository.delete(task);

        return new ResponseDTO(task, "Tarefa deletada com sucesso!!");
    }

    public Page<Task> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Task updateOrder(Long taskId, TaskUpdateOrderDTO orderDTO) {
        Task task = this.findById(taskId);
        task.setPresentationOrder(orderDTO.presentationOrder());
        return repository.save(task);
    }
}
