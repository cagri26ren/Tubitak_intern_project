package yte.intern.project.application.usecases.ManageEvents.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import yte.intern.project.application.usecases.ManageEvents.DTO.Event.GetChatEventDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Chat;
import yte.intern.project.application.usecases.ManageEvents.Entity.Event;
import yte.intern.project.application.usecases.ManageEvents.Entity.Participant;
import yte.intern.project.application.usecases.ManageEvents.Repository.EventRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final EventRepository eventRepository;

    public List<Event> getAllChatEvents(){
        List<Event> events = eventRepository.findEventThatStarted(LocalDate.of(2022,10,10), Sort.by("startDate").ascending());

        List<Event> resultEvents = new ArrayList<>();

        for( Event e : events ) {
            if (e.getParticipants().size() != 0) {
                resultEvents.add(e);
            }
        }

        //Removing ("")
        for(Event e: resultEvents){

            Set<Participant> participantSet = e.getParticipants();
            Set<Participant> copyParticipantSet = new HashSet<>(participantSet);

            for( Participant p : participantSet) {

                Set<Chat> chatSet = p.getChats();
                Set<Chat> copyChatSet = new HashSet<>(chatSet);

                for (Chat c : copyChatSet) {
                    if (!c.getAnswer().equals("")) {
                        chatSet.remove(c);
                    }
                }
            }
        }



        for( int i = 0; i < resultEvents.size(); i++ ){
            Set<Participant> participantSet = resultEvents.get(i).getParticipants();
            resultEvents.get(i).setParticipants(participantSet.stream().filter( participant -> participant.getChats().size() > 0 ).collect(Collectors.toSet()));
        }
        return resultEvents;


/*
        //Removing empty participants
        for(Event e: resultEvents){

            Set<Participant> participantSet = e.getParticipants();
            Set<Participant> copyParticipantSet = new HashSet<>(participantSet);

            for( Participant p : copyParticipantSet) {
                Set<Chat> chatSet = p.getChats();
                System.out.println(chatSet.size());
                if( chatSet.size() == 0 ){
                    participantSet.remove(p);
                }
            }
        }return resultEvents;/*


        //Removing empty events
        List<Event> copyResultEvents = new ArrayList<>(resultEvents);

        for(Event e: copyResultEvents){

            Set<Participant> participantSet = e.getParticipants();

            if( participantSet.size()==0){
                resultEvents.remove(e);
            }
        }




        return resultEvents;*/
    }
}
