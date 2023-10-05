package DbTemp.Repos;

import DbTemp.Models.Jonassensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JonasRepo extends JpaRepository<Jonassensor,Long> {
}
