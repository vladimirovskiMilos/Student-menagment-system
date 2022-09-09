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
@Table(name = "professor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorEntity implements AppEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "zip_code")
    private CityEntity city;

    @OneToOne
    private TitleEntity title;

    @Column(name = "reelection_date")
    private Date reelectionDate;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "professors_subjects", joinColumns = @JoinColumn(name = "professor_id", referencedColumnName = "id"), inverseJoinColumns =
    @JoinColumn(name = "subject_id", referencedColumnName = "id"))
    private List<SubjectEntity> subjects;


    public ProfessorEntity(String firstName, String lastName, String email, String address, String phone, CityEntity city, TitleEntity title, Date reelectionDate, List<SubjectEntity> subjects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.city = city;
        this.title = title;
        this.reelectionDate = reelectionDate;
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "ProfessorEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", city=" + city +
                ", title=" + title +
                ", reelectionDate=" + reelectionDate +
                ", subjects=" + subjects +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfessorEntity that = (ProfessorEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, phone);
    }
}