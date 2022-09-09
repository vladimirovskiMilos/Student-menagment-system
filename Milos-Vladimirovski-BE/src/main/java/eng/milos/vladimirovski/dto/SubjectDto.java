package eng.milos.vladimirovski.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto implements Dto {


    private Long id;

    @NotEmpty(message = "You mast enter a subject name")
    @Size(min = 3)
    private String name;
    @Size(max = 200)
    private String description;
    @NotNull(message = "ESP points are required")
    private int noOfESP;

    @NotNull(message = "Year of study is required")
    @Min(value = 1)
    @Max(value = 4)
    private int yearOfStudy;


    private String semester;


    public SubjectDto(String name, String description, int noOfESP, int yearOfStudy, String semester) {
        this.name = name;
        this.description = description;
        this.noOfESP = noOfESP;
        this.yearOfStudy = yearOfStudy;
        this.semester = semester;
    }

}
