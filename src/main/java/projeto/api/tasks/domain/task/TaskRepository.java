package projeto.api.tasks.domain.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query(nativeQuery = true,value = "SELECT nextval('presentation_order_seq')")
    Long getNextPresentationOrder();

    @Query(nativeQuery = true, value = "SELECT * FROM tasks WHERE LOWER(tasks.name) = LOWER(?1)")
    Optional<Task> findByName(String name);

    @Query(nativeQuery = true, value = "SELECT * FROM tasks ORDER BY presentation_order DESC LIMIT 1")
    Optional<Task> tasksByPresentation();
}
