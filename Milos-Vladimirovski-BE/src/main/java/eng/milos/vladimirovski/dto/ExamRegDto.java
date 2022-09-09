package eng.milos.vladimirovski.dto;

import eng.milos.vladimirovski.enity.ExamEntity;
import eng.milos.vladimirovski.enity.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamRegDto implements Dto{



    private Long id;
    @NotNull
    private StudentEntity student;

    @NotNull
    private ExamEntity exam;

    public ExamRegDto(StudentEntity student, ExamEntity exam) {
        this.student = student;
        this.exam = exam;
    }
}
