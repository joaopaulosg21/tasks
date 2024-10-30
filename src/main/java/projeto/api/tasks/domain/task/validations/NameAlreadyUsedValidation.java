package projeto.api.tasks.domain.task.validations;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projeto.api.tasks.domain.task.Task;
import projeto.api.tasks.domain.task.TaskDataDTO;
import projeto.api.tasks.domain.task.TaskRepository;

import java.util.Optional;

@Component
public class NameAlreadyUsedValidation implements TaskValidation {

    @Autowired
    private TaskRepository repository;

    @Override
    public void valid(TaskDataDTO data) {
        Optional<Task> optionalTask = repository.findByName(data.name());

        if(optionalTask.isPresent()) {
            throw new ValidationException("Ja existe uma tarefa registrada com esse nome!!");
        }
    }
}
