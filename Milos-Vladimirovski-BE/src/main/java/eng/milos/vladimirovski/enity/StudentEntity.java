package eng.milos.vladimirovski.enity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity  implements AppEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;



    @Column(name = "email")
    private String email;


    @Column(name = "index_number")
    private String indexNumber;

    @Column(name = "index_year")
    private long indexYear;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Size(min = 3, message = "Address mast have more then 3 characters")
    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "zip_code")
    private CityEntity city;


    @Column(name = "current_year_of_study")
    private Long currentYearOfStudy;

    public StudentEntity(String email, String indexNumber, long indexYear, String firstName, String lastName, String address, CityEntity city, Long currentYearOfStudy) {
        this.email = email;
        this.indexNumber = indexNumber;
        this.indexYear = indexYear;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.currentYearOfStudy = currentYearOfStudy;
    }


    @Override
    public String toString() {
        return "StudentEntity{" +
                "email='" + email + '\'' +
                ", indexNumber='" + indexNumber + '\'' +
                ", indexYear=" + indexYear +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city=" + city +
                ", CurrentYearOfStudy=" + currentYearOfStudy +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return indexYear == that.indexYear && Objects.equals(indexNumber, that.indexNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indexNumber, indexYear);
    }
}
