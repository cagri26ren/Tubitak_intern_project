package yte.intern.project.application.usecases.ManageEvents.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yte.intern.project.application.usecases.ManageEvents.DTO.Chat.AddChatDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Chat.ViewChatDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Event.GetChatEventDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Event.GetSurveyEventDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Event.ViewEventDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.AddSurveyQuestionDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.ViewSurveyQuestionDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.TcDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.SurveyQuestion;
import yte.intern.project.application.usecases.ManageEvents.Mapper.*;
import yte.intern.project.application.usecases.ManageEvents.Service.ChatService;
import yte.intern.project.application.usecases.ManageEvents.Service.EventService;
import yte.intern.project.application.usecases.ManageEvents.Service.ParticipantService;
import yte.intern.project.application.usecases.common.DTO.MessageResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class ChatController {
    private final ViewChatMapper viewChatMapper;
    private final ViewEventMapper viewEventMapper;
    private final GetSurveyEventMapper getSurveyEventMapper;
    private final GetChatEventMapper getChatEventMapper;
    private final ParticipantService participantService;
    private final ChatService chatService;
    private final EventService eventService;
    private final AddSurveyQuestionMapper addSurveyQuestionMapper;
    private final ViewSurveyQuestionMapper viewSurveyQuestionMapper;

    @PostMapping("events/ask/{name}")
    public List<ViewChatDto> getParticipantChats(@PathVariable("name") @NotBlank String name, @Valid @RequestBody TcDto tcDto){
        return viewChatMapper.mapToDto(participantService.getAllChats(name,tcDto.getTcKimlikNo()));
    }

    @PostMapping("events/ask/post/{name}")
    public MessageResponse postChat(@PathVariable("name") @NotBlank String name, @Valid @RequestBody AddChatDto addChatDto){
        return participantService.postChat(name,addChatDto.getTcKimlikNo(),addChatDto.getQuestion(),addChatDto.getAnswer());
    }

    @PutMapping("events/ask/post/{name}")
    public MessageResponse updateChat(@PathVariable("name") @NotBlank String name, @Valid @RequestBody AddChatDto addChatDto){
        return participantService.updateChat(name,addChatDto.getTcKimlikNo(),addChatDto.getQuestion(),addChatDto.getAnswer());
    }

    @GetMapping("/questions")
    public List<GetChatEventDto> getAllChatEvents(){
        return getChatEventMapper.mapToDto(chatService.getAllChatEvents());
    }


    @PostMapping("/surveyQuestions")
    public List<ViewEventDto> getAllSurveyEvents(@Valid @RequestBody TcDto tcDto){
        return viewEventMapper.mapToDto(eventService.getSurveyEvents(tcDto.getTcKimlikNo()));
    }


    @GetMapping("/surveyQuestions")
    public List<GetSurveyEventDto> getDoneSurveys(){
        return getSurveyEventMapper.mapToDto(eventService.getDoneSurveyEvents());
    }


    @PostMapping("/surveyQuestions/{name}")
    public GetSurveyEventDto getSpesificSurvey( @PathVariable("name") @NotBlank String name , @Valid @RequestBody TcDto tcDto ){
        System.out.println(getSurveyEventMapper.mapToDto(eventService.getSurveyEvent(name,tcDto.getTcKimlikNo())).getParticipants().get(0).getSurveyQuestions().get(0).getQuestionName());
        return getSurveyEventMapper.mapToDto(eventService.getSurveyEvent(name,tcDto.getTcKimlikNo()));
    }
    @PostMapping("/allSurveyQuestions/{name}")
    public List<ViewSurveyQuestionDto> getSurvey(@PathVariable("name") @NotBlank String name , @Valid @RequestBody TcDto tcDto ){
        return viewSurveyQuestionMapper.mapToDto(eventService.getSurvey(name,tcDto.getTcKimlikNo()));
    }


    @PutMapping("/surveyQuestions/{name}")
    public MessageResponse updateSurvey(@PathVariable("name") @NotBlank String name , @Valid @RequestBody List<AddSurveyQuestionDto> addSurveyQuestionDto){

        return eventService.updateSurvey(name,addSurveyQuestionDto.get(0).getTcKimlikNo(), addSurveyQuestionMapper.mapToEntity(addSurveyQuestionDto) );
    }

    @PostMapping("/checkParticipant")
    public MessageResponse checkParticipant( @Valid @RequestBody TcDto tcDto){
        return participantService.checkOnlyParticipant(tcDto.getTcKimlikNo());
    }

}
