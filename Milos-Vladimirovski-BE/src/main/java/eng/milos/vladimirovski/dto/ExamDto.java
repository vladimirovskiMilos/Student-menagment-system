package eng.milos.vladimirovski.dto;

import eng.milos.vladimirovski.enity.ExaminationPeriodEntity;
import eng.milos.vladimirovski.enity.ProfessorEntity;
import eng.milos.vladimirovski.enity.SubjectEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamDto implements Dto{



    private Long id;

    private ExaminationPeriodEntity examinationPeriod;
    @NotNull(message = "You must pick subject for the exam")
    @Valid
    private SubjectEntity subject;
    @NotNull
    private ProfessorEntity professor;
    @NotNull
    private Date date;

    public ExamDto(ExaminationPeriodEntity examinationPeriod, SubjectEntity subject, ProfessorEntity professor, Date date) {
        this.subject = subject;
        this.professor = professor;
        this.date = date;
        this.examinationPeriod = examinationPeriod;
    }

}
