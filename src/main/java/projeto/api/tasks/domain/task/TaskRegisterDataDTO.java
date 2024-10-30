package projeto.api.tasks.domain.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TaskRegisterDataDTO(
        @NotBlank
        String name,
        @NotNull
        BigDecimal cost,
        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate deadline) {
}
