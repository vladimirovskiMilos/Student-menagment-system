package eng.milos.vladimirovski.enity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamRegEntity implements AppEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private StudentEntity student;

    @ManyToOne
    private ExamEntity exam;

    public ExamRegEntity(StudentEntity student, ExamEntity exam) {
        this.student = student;
        this.exam = exam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamRegEntity that = (ExamRegEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(student, that.student) && Objects.equals(exam, that.exam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student, exam);
    }
}
