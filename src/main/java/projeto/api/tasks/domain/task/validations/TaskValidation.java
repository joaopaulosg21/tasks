package projeto.api.tasks.domain.task.validations;

import projeto.api.tasks.domain.task.TaskDataDTO;

public interface TaskValidation {

    void valid(TaskDataDTO data);
}
