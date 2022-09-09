package eng.milos.vladimirovski.enity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "subject")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SubjectEntity implements AppEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private String description;

    private int noOfESP;

    private int yearOfStudy;

    private String semester;


    public SubjectEntity(String name, String description, int noOfESP, int yearOfStudy, String semester) {
        this.name = name;
        this.description = description;
        this.noOfESP = noOfESP;
        this.yearOfStudy = yearOfStudy;
        this.semester = semester;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectEntity subject = (SubjectEntity) o;
        return id.equals(subject.id) && name.equals(subject.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "SubjectEntity{" +
                "subjectId=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", noOfESP=" + noOfESP +
                ", yearOfStudy=" + yearOfStudy +
                ", semester='" + semester + '\'' +
                '}';
    }
}
