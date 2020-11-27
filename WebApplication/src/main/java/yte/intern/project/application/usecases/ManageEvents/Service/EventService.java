package yte.intern.project.application.usecases.ManageEvents.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.internal.LoadingCache;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import yte.intern.project.application.Helpers.ZXingHelper;
import yte.intern.project.application.MailService.EmailServiceImpl;
import yte.intern.project.application.usecases.ManageEvents.Entity.*;
import yte.intern.project.application.usecases.ManageEvents.Repository.*;

import org.springframework.data.domain.Pageable;
import yte.intern.project.application.usecases.common.DTO.MessageResponse;
import yte.intern.project.application.usecases.common.ENUMS.MessageType;

import javax.persistence.EntityNotFoundException;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final ParticipantRepository participantRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final SurveyQuestionRepository surveyQuestionRepository;
    private final SurveyEventQuestionRepository surveyEventQuestionRepository;
    private final EmailServiceImpl emailService;


    public List<Event> getAllEventsAdmin( ){
        return eventRepository.findEventByStartDate(LocalDate.of(1970,10,10)
                , Sort.by("startDate").ascending());
    }

    public List<Event> getAllEventsUser( ){
        return eventRepository.findEventByStartDate(LocalDate.now(), Sort.by("startDate").ascending());
    }

    public Event getEvent( String name ){
        Event event = eventRepository.findEventByName(name);
        if( event == null ){
            throw new EntityNotFoundException("Event with the name " + name + " does not exist");
        }
        return event;
    }

    public MessageResponse addEvent( Event event ){
        if( eventRepository.findEventByName(event.getName()) != null){
            return new MessageResponse("This event name " + event.getName() + " already taken", MessageType.ERROR);
        }
        else if( LocalDateTime.of(event.getStartDate(),event.getStartTime()).isBefore(LocalDateTime.now())){

            return new MessageResponse("Event start date must be after now", MessageType.ERROR);
        }
        event.setCurrent(0L);
        if( event.getQuestions() == null){
            event.setQuestions(new HashSet<>());
        }
        questionRepository.saveAll(event.getQuestions());
        surveyEventQuestionRepository.saveAll(event.getSurveyQuestions());
        eventRepository.save(event);
        return new MessageResponse("Successfully added", MessageType.SUCCESS);
    }

    @Transactional
    public MessageResponse updateSingleEvent( String oldName, Event event ){
        Event dbEvent = eventRepository.findEventByName(oldName);

        if( dbEvent != null){
            if(event.getStartDate().isAfter(LocalDate.now()) ) {
                if(dbEvent.getCurrent() < event.getMax() ) {
                    dbEvent.setName(event.getName());
                    dbEvent.setMax(event.getMax());
                    dbEvent.setStartDate(event.getStartDate());
                    dbEvent.setStartTime(event.getStartTime());
                    dbEvent.setEndDate(event.getEndDate());
                    dbEvent.setEndTime(event.getEndTime());
                    dbEvent.setLat(event.getLat());
                    dbEvent.setLng(event.getLng());
                    dbEvent.getQuestions().clear();
                    for(Question q : event.getQuestions()){
                        dbEvent.getQuestions().add(q);
                    }
                    eventRepository.save(dbEvent);
                }
                else{
                    return new MessageResponse("Event max capacity must be higher than current number of people",MessageType.ERROR);
                }
            }
            else {
                return new MessageResponse("You cannot change the event after event starts",MessageType.ERROR);
            }
        }
        else {
            return new MessageResponse("Event with the given name" + oldName + "does not exist",MessageType.ERROR);
        }
        return new MessageResponse("Event successfully updated",MessageType.SUCCESS);
    }

    @Transactional
    public MessageResponse deleteSingleEvent( String name ){
        Event event = eventRepository.findEventByName(name);
        if( event != null ){
            if( LocalDateTime.of(event.getStartDate(),event.getStartTime()).isBefore(LocalDateTime.now())){
                return new MessageResponse("You cannot delete an event after it started", MessageType.ERROR);
            }
            eventRepository.deleteByName(name);
            return new MessageResponse("Event deleted",MessageType.SUCCESS);
        }
        return new MessageResponse("Cannot find event with name " + name, MessageType.ERROR );
    }

    @Transactional
    public MessageResponse joinSingleEvent(String name , Participant participant){
        Event event = eventRepository.findEventByName(name);
        if( event == null){
            return new MessageResponse("Cannot find event with name" + name, MessageType.ERROR );
        }
        if( event.getCurrent() >= event.getMax()){
            return new MessageResponse("This event is full" + name, MessageType.ERROR );
        }
        Set<Participant> participants = event.getParticipants();
        List<Participant> filteredParticipants = participants
                .stream()
                .filter(p->p.getTcKimlikNo().equals(participant.getTcKimlikNo()))
                .collect(Collectors.toList());
        if( filteredParticipants.size() != 0 ){
            return new MessageResponse("Participant" + participant.getName() + " is already in this event", MessageType.ERROR );
        }
        String qrValue = participant.getTcKimlikNo() + " - " + participant.getName() + " - " + participant.getSurname() + " - "
                + participant.getJoinDate().toString() + " / " + participant.getJoinTime().toString();
        byte[] qrCode = ZXingHelper.getQRCodeImage( qrValue, 200, 200);
        participant.setQrCode(qrCode);

        try {
            emailService.sendMessageWithAttachmentImage(participant.getEmail(), "testa", qrCode);
        }
        catch (Exception e){
            throw new IllegalArgumentException("EMAIL_ERROR");
        }

        if( participant.getSurveyQuestions() == null ){
            participant.setSurveyQuestions(new HashSet<>());
        }
        surveyQuestionRepository.saveAll(participant.getSurveyQuestions());
        answerRepository.saveAll(participant.getAnswers());
        participantRepository.save(participant);

        participants.add(participant);
        event.setCurrent( event.getCurrent() + 1 );

        eventRepository.save(event);

        return new MessageResponse("Participant joined the event",MessageType.SUCCESS);
    }

    public List<Event> getAllReportEvents(){
        return eventRepository.findEventByStartDate(LocalDate.of(1970,10,10)
                , Sort.by("startDate").ascending());
    }



    public List<Event> getSurveyEvents( String tcKimlikNo ){
        List<Event> events = eventRepository.findAll();
        List<Event> resultEvents = events.stream().filter(event -> event.getEndDate().isAfter(LocalDate.now())).collect(Collectors.toList());
        List<Event> tempEvents = new ArrayList<>(resultEvents);
        for( Event event : tempEvents ){
            Set<Participant> participants = event.getParticipants();
            List<Participant> filteredParticipants = participants
                    .stream()
                    .filter(p->p.getTcKimlikNo().equals(tcKimlikNo))
                    .collect(Collectors.toList());
            if( filteredParticipants.size() != 0 ){
                Participant participant = filteredParticipants.get(0);
                Set<SurveyQuestion> surveyQuestions = participant.getSurveyQuestions();
                for( SurveyQuestion surveyQuestion : surveyQuestions ){
                    if( !surveyQuestion.getAnswer().equals("") || surveyQuestion.getQuestionName().equals("")){
                        resultEvents.remove(event);
                        break;
                    }
                }
            }

        }
        return resultEvents;
    }

    public List<Event> getDoneSurveyEvents(){
        List<Event> events = eventRepository.findEventThatStarted(LocalDate.of(2022,10,10), Sort.by("startDate").ascending());

        List<Event> resultEvents = new ArrayList<>();
        for( Event e : events ) {
            if (e.getParticipants().size() != 0) {
                resultEvents.add(e);
            }
        }
        List<Event> copyResultEvents = new ArrayList<>(resultEvents);

        //Removing ()
        for(Event e: resultEvents){

            Set<Participant> participantSet = e.getParticipants();
            Set<Participant> copyParticipantSet = new HashSet<>(participantSet);

            for( Participant p : copyParticipantSet) {

                Set<SurveyQuestion> surveyQuestionSet = p.getSurveyQuestions();

                for (SurveyQuestion s : surveyQuestionSet) {
                    if (s.getAnswer().equals("") || s.getQuestionName().equals("")) {
                        participantSet.remove(p);
                        break;
                    }
                }
            }
        }
        return resultEvents.stream().filter(event -> event.getParticipants().size() > 0 ).collect(Collectors.toList());
    }

    public Event getSurveyEvent( String name , String tcKimlikNo ){
        Event event = eventRepository.findEventByName(name);

        Set<Participant> participants = event.getParticipants();
        List<Participant> filteredParticipants = participants
                .stream()
                .filter(p->p.getTcKimlikNo().equals(tcKimlikNo))
                .collect(Collectors.toList());
        if( filteredParticipants.size() == 0 ){
            throw new EntityNotFoundException("No such participant in this event");
        }
        return event;
    }

    @Transactional
    public MessageResponse updateSurvey( String name, String tcKimlikNo, List<SurveyQuestion> surveyQuestions ){
        System.out.println("error check1");

        Event event = eventRepository.findEventByName(name);
        Set<Participant> participants = event.getParticipants();
        List<Participant> filteredParticipants = participants
                .stream()
                .filter(p->p.getTcKimlikNo().equals(tcKimlikNo))
                .collect(Collectors.toList());
        System.out.println("error check2");
        if( filteredParticipants.size() != 0 ){
            Participant participant = filteredParticipants.get(0);
            participant.getSurveyQuestions().clear();
            participant.getSurveyQuestions().addAll(surveyQuestions);
            surveyQuestionRepository.saveAll( participant.getSurveyQuestions() );
            return new MessageResponse("Survey saved", MessageType.SUCCESS );
        }
        return new MessageResponse("No such participant in this event", MessageType.ERROR );
    }

    public List<SurveyQuestion> getSurvey( String name , String tcKimlikNo){
        Event event = eventRepository.findEventByName(name);
        Set<Participant> participants = event.getParticipants();
        List<Participant> filteredParticipants = participants
                .stream()
                .filter(p->p.getTcKimlikNo().equals(tcKimlikNo))
                .collect(Collectors.toList());
        if( filteredParticipants.size() != 0 ){
            return new ArrayList<>(filteredParticipants.get(0).getSurveyQuestions());
        }
        throw new EntityNotFoundException("No such participant");
    }



}
