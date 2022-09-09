package eng.milos.vladimirovski.enity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExaminationPeriodEntity implements AppEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date start;
    private Date end;
    private String name;

    /*@ManyToMany(cascade = CascadeType.ALL)
    private List<ExamEntity> exams;*/

    @Column(name = "is_active")
    private boolean isActive;

//List<ExamEntity> exams,
    public ExaminationPeriodEntity(Date start, Date end, String name,  boolean isActive) {
        this.start = start;
        this.end = end;
        this.name = name;
     //   this.exams = exams;
        this.isActive = isActive;
    }

/*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExaminationPeriodEntity that = (ExaminationPeriodEntity) o;
        return isActive == that.isActive && Objects.equals(id, that.id) && Objects.equals(start, that.start) && Objects.equals(end, that.end) && Objects.equals(name, that.name) && Objects.equals(exams, that.exams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, start, end, name, exams, isActive);
    }

    @Override
    public String toString() {
        return "ExaminationPeriodEntity{" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                ", name='" + name + '\'' +
                ", exams=" + exams +
                ", isActive=" + isActive +
                '}';
    }*/
}
