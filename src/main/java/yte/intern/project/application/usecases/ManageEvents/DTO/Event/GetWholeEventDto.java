package yte.intern.project.application.usecases.ManageEvents.DTO.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.intern.project.application.usecases.ManageEvents.DTO.Participant.AddParticipantDto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetWholeEventDto {

    @NotBlank
    private String name;

    @Min( value = 0, message = "Course capacity can not be 0")
    private Long max;

    @Min( value = 0, message = "Course capacity can not be 0")
    private Long current;

    private LocalDate startDate;

    private LocalTime startTime;

    private LocalDate endDate;

    private LocalTime endTime;

    private Double lat;

    private Double lng;

    private List<AddParticipantDto> participants;
}
