package eng.milos.vladimirovski.dto;

import eng.milos.vladimirovski.enity.ExamRegEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PassExamDto implements Dto{

    private Long id;
    private ExamRegEntity examRegistration;
    private int grade;


    public PassExamDto(ExamRegEntity examRegistration, int grade) {
        this.examRegistration = examRegistration;
        this.grade = grade;
    }
}
