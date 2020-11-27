package yte.intern.project.application.usecases.ManageEvents.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yte.intern.project.application.usecases.ManageEvents.DTO.Event.GetWholeEventDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Participant.AddParticipantDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.TcDto;
import yte.intern.project.application.usecases.ManageEvents.Mapper.AddParticipantMapper;
import yte.intern.project.application.usecases.ManageEvents.Mapper.GetWholeEventMapper;
import yte.intern.project.application.usecases.ManageEvents.Service.EventService;
import yte.intern.project.application.usecases.ManageEvents.Service.ParticipantService;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
@CrossOrigin("*")
public class ReportController {
    private final EventService eventService;
    private final ParticipantService participantService;
    private final GetWholeEventMapper getWholeEventMapper;
    private final AddParticipantMapper addParticipantMapper;


    @GetMapping
    public List<GetWholeEventDto> getEvents(){
        return getWholeEventMapper.mapToDto( eventService.getAllReportEvents() );
    }

    @GetMapping("/{name}")
    public GetWholeEventDto getEvent(@PathVariable("name") @NotBlank String name){
        return getWholeEventMapper.mapToDto(eventService.getEvent(name));
    }

    @PostMapping("/view/{name}")
    public AddParticipantDto getParticipant(@PathVariable("name") @NotBlank String name , @RequestBody TcDto tcDto){
        return addParticipantMapper.mapToDto(participantService.getParticipant(name,tcDto.getTcKimlikNo()));
    }
}
