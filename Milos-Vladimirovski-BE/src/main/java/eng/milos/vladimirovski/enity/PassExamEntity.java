package eng.milos.vladimirovski.enity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pass_exam")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PassExamEntity implements AppEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private ExamRegEntity examRegistration;

    private int grade;

    public PassExamEntity(ExamRegEntity examRegistration, int grade) {
        this.examRegistration = examRegistration;
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassExamEntity that = (PassExamEntity) o;
        return grade == that.grade && Objects.equals(id, that.id) && Objects.equals(examRegistration, that.examRegistration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, examRegistration, grade);
    }
}
