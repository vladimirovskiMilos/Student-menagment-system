package eng.milos.vladimirovski.dto;

import eng.milos.vladimirovski.enity.ExamEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExaminationPeriodDto implements Dto{

    private Long id;
    @NotNull
    private Date start;
    @NotNull
    private Date end;
    @NotNull
    private String name;
    //private List<ExamEntity> exams; List<ExamEntity> exams,
    private boolean isActive;

    public ExaminationPeriodDto(Date start, Date end, String name,  boolean isActive) {
        this.start = start;
        this.end = end;
        this.name = name;
        //this.exams = exams;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "ExaminationPeriodDto{" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
