package yte.intern.project.application.usecases.ManageEvents.Controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import yte.intern.project.application.MailService.EmailServiceImpl;
import yte.intern.project.application.usecases.ManageEvents.DTO.*;
import yte.intern.project.application.usecases.ManageEvents.DTO.Event.AddEventDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Event.ViewEventDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Participant.AddParticipantDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Participant.ParticipantDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Event;
import yte.intern.project.application.usecases.ManageEvents.Entity.Participant;
import yte.intern.project.application.usecases.ManageEvents.Entity.SurveyQuestion;
import yte.intern.project.application.usecases.ManageEvents.Mapper.*;
import yte.intern.project.application.usecases.ManageEvents.Mapper.AddEventMapper;
import yte.intern.project.application.usecases.ManageEvents.Service.EventService;
import yte.intern.project.application.usecases.ManageEvents.Service.ParticipantService;
import yte.intern.project.application.usecases.common.DTO.MessageResponse;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
@CrossOrigin("*")
public class EventController {
    private final EventService eventService;
    private final ParticipantService participantService;
    private final EmailServiceImpl emailService;
    private final ServletContext servletContext;
    private final AddParticipantMapper addParticipantMapper;
    private final ViewEventMapper viewEventMapper;
    private final AddEventMapper addEventMapper;
    private final ViewChatMapper viewChatMapper;
    private final ParticipantMapper participantMapper;

    @GetMapping
    public List<ViewEventDto> getAllEvents(){
        List<Event> events = eventService.getAllEventsUser();
        return viewEventMapper.mapToDto(events);
    }
    @GetMapping("/admin")
    public List<ViewEventDto> getAllEventsAdmin(){
        List<Event> events = eventService.getAllEventsAdmin();
        return viewEventMapper.mapToDto(events);
    }

    @GetMapping("/view/{name}")
    public ViewEventDto getSingleEvent( @PathVariable("name") @NotBlank String name ){
        Event event = eventService.getEvent(name);
        return viewEventMapper.mapToDto(event);
    }

    @PostMapping
    public MessageResponse addEvent(@Valid @RequestBody AddEventDto addEventDto ){
        System.out.println(addEventDto.getSurveyQuestions().get(0).getSurveyQuestionName());
        Event event = addEventMapper.mapToEntity(addEventDto);
        return eventService.addEvent(event);
    }

    @PutMapping("/{oldName}")
    public MessageResponse updateEvent(@PathVariable("oldName") @NotBlank String oldName,@Valid @RequestBody AddEventDto addEventDto ){
        Event event = addEventMapper.mapToEntity(addEventDto);
        return eventService.updateSingleEvent(oldName,event);
    }

    @DeleteMapping("/{name}")
    public MessageResponse updateEvent(@PathVariable("name") @NotBlank String name){
        return eventService.deleteSingleEvent(name);
    }

    @PutMapping("/join/{name}")
    public MessageResponse joinEvent(@PathVariable("name") @NotBlank String name , @Valid @RequestBody AddParticipantDto addParticipantDto){
        System.out.println(addParticipantDto.getGender());
        Participant participant = addParticipantMapper.mapToEntity(addParticipantDto);
        System.out.println(participant.getGender());
        return eventService.joinSingleEvent(name,participant);
}

    @RequestMapping(value = "/qrcode", method = RequestMethod.POST)
    public ResponseEntity<byte[]> qrcode(@Valid @RequestBody QrCodeDto qrCodeDto) throws Exception {
        BufferedImage bufferedImage = participantService.getQrCodeBufferImage(qrCodeDto.getName(),qrCodeDto.getTcKimlikNo());
        File file = new File("src/main/resources/static/myimage.png");
        ImageIO.write(bufferedImage, "png", file);

        HttpHeaders headers = new HttpHeaders();
        InputStream in = getClass().getResourceAsStream("/static/myimage.png");
        byte[] media = IOUtils.toByteArray(in);
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/checkParticipant")
    public MessageResponse checkParticipant( @RequestBody QrCodeDto checkParticpantDto){
        return participantService.checkParticipant(checkParticpantDto.getName(),checkParticpantDto.getTcKimlikNo());
    }

    @GetMapping("/giveaway/{name}")
    public ParticipantDto getRandomParticipant(@PathVariable("name") @NotBlank String name ){
        return participantMapper.mapToDto(participantService.getRandomParticipant(name));
    }


}
