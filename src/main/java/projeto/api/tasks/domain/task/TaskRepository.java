package projeto.api.tasks.domain.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query(nativeQuery = true,value = "SELECT nextval('presentation_order_seq')")
    Long getNextPresentationOrder();
}
