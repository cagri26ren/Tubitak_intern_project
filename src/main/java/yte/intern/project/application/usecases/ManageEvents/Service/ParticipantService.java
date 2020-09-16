package yte.intern.project.application.usecases.ManageEvents.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.intern.project.application.usecases.ManageEvents.Entity.Chat;
import yte.intern.project.application.usecases.ManageEvents.Entity.Event;
import yte.intern.project.application.usecases.ManageEvents.Entity.Participant;
import yte.intern.project.application.usecases.ManageEvents.Repository.ChatRepository;
import yte.intern.project.application.usecases.ManageEvents.Repository.EventRepository;
import yte.intern.project.application.usecases.ManageEvents.Repository.ParticipantRepository;
import yte.intern.project.application.usecases.common.DTO.MessageResponse;
import yte.intern.project.application.usecases.common.ENUMS.MessageType;

import javax.imageio.ImageIO;
import javax.persistence.EntityNotFoundException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    private final ChatRepository chatRepository;
    private final EventService eventService;
    private final EventRepository eventRepository;


    public byte[] getParticipantQrvalue( String name , String tcKimlikNo ){
        String qrCode;
        Event event= eventService.getEvent(name);

        List<Participant> filteredParticipants = event.getParticipants()
                .stream()
                .filter(p->p.getTcKimlikNo().equals(tcKimlikNo))
                .collect(Collectors.toList());
        if( filteredParticipants.size() == 0 ) {
            throw new EntityNotFoundException();
        }
        Participant participant = filteredParticipants.get(0);
        return participant.getQrCode();
    }

    public BufferedImage getQrCodeBufferImage( String name , String tcKimlikNo ) throws Exception{
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(getParticipantQrvalue(name,tcKimlikNo));
        return ImageIO.read(byteArrayInputStream);
    }

    public MessageResponse checkParticipant( String name ,String tcKimlikNo ){
        Event event = eventService.getEvent(name);
        Set<Participant> participants = event.getParticipants();
        List<Participant> filteredParticipants = participants
                .stream()
                .filter(p->p.getTcKimlikNo().equals(tcKimlikNo))
                .collect(Collectors.toList());
        if( filteredParticipants.size() != 0 ){
            return new MessageResponse("You are in this event", MessageType.SUCCESS );
        }
        return new MessageResponse("No such participant in this event", MessageType.ERROR );

    }

    public List<Chat> getAllChats(String name , String tcKimlikNo ){
        Event event = eventService.getEvent(name);
        Set<Participant> participants = event.getParticipants();
        List<Participant> filteredParticipants = participants
                .stream()
                .filter(p->p.getTcKimlikNo().equals(tcKimlikNo))
                .collect(Collectors.toList());
        if( filteredParticipants.size() != 0 ){
            Participant participant = filteredParticipants.get(0);
            List<Chat> chats = new ArrayList<Chat>();
            chats.addAll(participant.getChats());
            return chats;
        }
        return new ArrayList<Chat>();
    }

    public MessageResponse postChat( String name , String tcKimlikNo, String question, String answer){
        Event event = eventService.getEvent(name);
        Set<Participant> participants = event.getParticipants();
        List<Participant> filteredParticipants = participants
                .stream()
                .filter(p->p.getTcKimlikNo().equals(tcKimlikNo))
                .collect(Collectors.toList());
        if( filteredParticipants.size() != 0 ){
            Participant participant = filteredParticipants.get(0);
            Chat chat = new Chat(question,answer);
            participant.getChats().add( chat );
            chatRepository.save(chat);
            participantRepository.save(participant);
            return new MessageResponse("Question posted", MessageType.SUCCESS );
        }
        return new MessageResponse("No such participant in this event", MessageType.ERROR );

    }

    public MessageResponse updateChat( String name , String tcKimlikNo, String question, String answer){
        Event event = eventService.getEvent(name);
        Set<Participant> participants = event.getParticipants();
        List<Participant> filteredParticipants = participants
                .stream()
                .filter(p->p.getTcKimlikNo().equals(tcKimlikNo))
                .collect(Collectors.toList());
        if( filteredParticipants.size() != 0 ){
            Participant participant = filteredParticipants.get(0);
            List<Chat> chats = chatRepository.findChatByQuestion(question,"");//Fixed
            if( chats.size() != 0  ){
                Chat chat = chats.get(0);
                chat.setAnswer(answer);
                chatRepository.save(chat);
                return new MessageResponse("Answer posted", MessageType.SUCCESS );
            }
            return new MessageResponse("Question is not in the system", MessageType.ERROR );
        }
        return new MessageResponse("No such participant in this event", MessageType.ERROR );
    }

    public Participant getRandomParticipant( String name ){
        Event event = eventService.getEvent(name);
        Set<Participant> participants = event.getParticipants();
        int random = (int)(Math.floor(( Math.random() * participants.size())));
        int i = 0;
        for( Participant p : participants ){
            if( i == random ) {
                return p;
            }
            i++;
        }
        return null;
    }

    public MessageResponse checkOnlyParticipant( String tc ){
        List<Event> events = eventRepository.findAll();

        for( Event e : events ){
            for( Participant p: e.getParticipants()){
                if( p.getTcKimlikNo().equals(tc) ){
                    return new MessageResponse("Operation successful", MessageType.SUCCESS );
                }
            }
        }
        return new MessageResponse("You have not joined any event yet", MessageType.ERROR );
    }

    public Participant getParticipant( String name , String tc ){
        Event event = eventRepository.findEventByName(name);
        Set<Participant> participants = event.getParticipants();
        List<Participant> filteredParticipants = participants
                .stream()
                .filter(p->p.getTcKimlikNo().equals(tc))
                .collect(Collectors.toList());
        if( filteredParticipants.size() != 0 ){
            return filteredParticipants.get(0);
        }
        throw new EntityNotFoundException("No such participant");
    }

}
