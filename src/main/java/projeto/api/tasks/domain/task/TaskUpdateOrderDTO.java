package projeto.api.tasks.domain.task;

import jakarta.validation.constraints.NotNull;

public record TaskUpdateOrderDTO(
    @NotNull Long presentationOrder) {

}
