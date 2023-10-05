package DbTemp.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="Jonassensor")
@NoArgsConstructor
@AllArgsConstructor

public class Jonassensor {
    @Id
    @GeneratedValue
    private Long id;
    private float temperatur;
    @CreationTimestamp
    private LocalDateTime tid;

    public Jonassensor(float temperatur){this.temperatur=temperatur;}

    public Jonassensor(float temperatur, LocalDateTime tid) {
        this.temperatur = temperatur;
        this.tid = tid;
    }
}


