package eng.milos.vladimirovski.enity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamEntity implements AppEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ExaminationPeriodEntity examinationPeriod;
    @OneToOne
    private SubjectEntity subject;
    @OneToOne
    private ProfessorEntity professor;
    private Date date;


    public ExamEntity(ExaminationPeriodEntity  examinationPeriod,SubjectEntity subject, ProfessorEntity professor, Date date) {
        this.subject = subject;
        this.professor = professor;
        this.date = date;
        this.examinationPeriod = examinationPeriod;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamEntity that = (ExamEntity) o;
        return id == that.id && Objects.equals(subject, that.subject) && Objects.equals(professor, that.professor) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, professor, date);
    }

    @Override
    public String toString() {
        return "ExamEntity{" +
                "id=" + id +
                ", subject=" + subject +
                ", professor=" + professor +
                ", date=" + date +
                '}';
    }
}
