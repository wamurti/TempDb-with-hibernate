package DbTemp.Repos;

import DbTemp.Models.Bjornsensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BjornRepo extends JpaRepository<Bjornsensor,Long> {
}
