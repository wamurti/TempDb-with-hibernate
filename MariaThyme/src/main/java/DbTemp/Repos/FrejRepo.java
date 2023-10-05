package DbTemp.Repos;

import DbTemp.Models.frejsensor;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.parser.Entity;
import java.util.Comparator;
import java.util.List;

public interface FrejRepo extends JpaRepository<frejsensor,Long> {

}