package projeto.api.tasks.domain.task;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "tasks")
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal cost;

    private LocalDate deadline;

    private Long presentationOrder;

    public Task(){}

    public Task(TaskDataDTO data, Long presentationOrder) {
        this.name = data.name();
        this.cost = data.cost();
        this.deadline = data.deadline();
        this.presentationOrder = presentationOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Long getPresentationOrder() {
        return presentationOrder;
    }

    public void setPresentationOrder(Long presentationOrder) {
        this.presentationOrder = presentationOrder;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
