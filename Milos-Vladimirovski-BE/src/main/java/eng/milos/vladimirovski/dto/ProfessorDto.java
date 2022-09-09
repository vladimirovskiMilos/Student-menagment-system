package eng.milos.vladimirovski.dto;

import eng.milos.vladimirovski.enity.CityEntity;
import eng.milos.vladimirovski.enity.SubjectEntity;
import eng.milos.vladimirovski.enity.TitleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDto implements Dto{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 3)
    private String firstName;

    @NotEmpty
    @Size(min = 3)
    private String lastName;

    @Email
    private String email;

    @Size(min = 3)
    private String address;

    @Size(min = 9)
    private String phone;

    private CityEntity city;

    @NotNull
    private TitleEntity title;

    @NotNull
    private Date reelectionDate;

    private List<SubjectEntity> subjects;


    public ProfessorDto(String firstName, String lastName, String email, String address, String phone, CityEntity city, TitleEntity title, Date reelectionDate, List<SubjectEntity> subjects) {
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

}
