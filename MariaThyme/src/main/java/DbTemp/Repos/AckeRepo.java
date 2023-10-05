package DbTemp.Repos;

import DbTemp.Models.Ackesensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AckeRepo extends JpaRepository<Ackesensor, Long> {
}
