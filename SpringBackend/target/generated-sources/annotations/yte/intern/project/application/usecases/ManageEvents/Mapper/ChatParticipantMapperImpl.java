package yte.intern.project.application.usecases.ManageEvents.Mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import yte.intern.project.application.usecases.ManageEvents.DTO.Participant.ChatParticipantDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Event;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-29T18:08:13+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14.0.1 (Oracle Corporation)"
)
@Component
public class ChatParticipantMapperImpl implements ChatParticipantMapper {

    @Override
    public ChatParticipantDto mapToDto(Event event) {
        if ( event == null ) {
            return null;
        }

        ChatParticipantDto chatParticipantDto = new ChatParticipantDto();

        chatParticipantDto.setName( event.getName() );

        return chatParticipantDto;
    }

    @Override
    public Event mapToEntity(ChatParticipantDto chatParticipantDto) {
        if ( chatParticipantDto == null ) {
            return null;
        }

        Event event = new Event();

        event.setName( chatParticipantDto.getName() );

        return event;
    }

    @Override
    public List<ChatParticipantDto> mapToDto(List<Event> event) {
        if ( event == null ) {
            return null;
        }

        List<ChatParticipantDto> list = new ArrayList<ChatParticipantDto>( event.size() );
        for ( Event event1 : event ) {
            list.add( mapToDto( event1 ) );
        }

        return list;
    }

    @Override
    public List<Event> mapToEntity(List<ChatParticipantDto> chatParticipantDtos) {
        if ( chatParticipantDtos == null ) {
            return null;
        }

        List<Event> list = new ArrayList<Event>( chatParticipantDtos.size() );
        for ( ChatParticipantDto chatParticipantDto : chatParticipantDtos ) {
            list.add( mapToEntity( chatParticipantDto ) );
        }

        return list;
    }
}
