package yte.intern.project.application.usecases.ManageEvents.Mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import yte.intern.project.application.usecases.ManageEvents.DTO.Chat.ViewChatDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Event.GetChatEventDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Participant.ChatParticipantDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Chat;
import yte.intern.project.application.usecases.ManageEvents.Entity.Event;
import yte.intern.project.application.usecases.ManageEvents.Entity.Participant;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-28T19:51:49+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14.0.1 (Oracle Corporation)"
)
@Component
public class GetChatEventMapperImpl implements GetChatEventMapper {

    @Override
    public GetChatEventDto mapToDto(Event event) {
        if ( event == null ) {
            return null;
        }

        GetChatEventDto getChatEventDto = new GetChatEventDto();

        getChatEventDto.setName( event.getName() );
        getChatEventDto.setStartDate( event.getStartDate() );
        getChatEventDto.setStartTime( event.getStartTime() );
        getChatEventDto.setEndDate( event.getEndDate() );
        getChatEventDto.setEndTime( event.getEndTime() );
        getChatEventDto.setParticipants( participantSetToChatParticipantDtoList( event.getParticipants() ) );

        return getChatEventDto;
    }

    @Override
    public Event mapToEntity(GetChatEventDto getChatEventDto) {
        if ( getChatEventDto == null ) {
            return null;
        }

        Event event = new Event();

        event.setName( getChatEventDto.getName() );
        event.setStartDate( getChatEventDto.getStartDate() );
        event.setEndDate( getChatEventDto.getEndDate() );
        event.setStartTime( getChatEventDto.getStartTime() );
        event.setEndTime( getChatEventDto.getEndTime() );
        event.setParticipants( chatParticipantDtoListToParticipantSet( getChatEventDto.getParticipants() ) );

        return event;
    }

    @Override
    public List<GetChatEventDto> mapToDto(List<Event> event) {
        if ( event == null ) {
            return null;
        }

        List<GetChatEventDto> list = new ArrayList<GetChatEventDto>( event.size() );
        for ( Event event1 : event ) {
            list.add( mapToDto( event1 ) );
        }

        return list;
    }

    @Override
    public List<Event> mapToEntity(List<GetChatEventDto> getChatEventDtos) {
        if ( getChatEventDtos == null ) {
            return null;
        }

        List<Event> list = new ArrayList<Event>( getChatEventDtos.size() );
        for ( GetChatEventDto getChatEventDto : getChatEventDtos ) {
            list.add( mapToEntity( getChatEventDto ) );
        }

        return list;
    }

    protected ViewChatDto chatToViewChatDto(Chat chat) {
        if ( chat == null ) {
            return null;
        }

        ViewChatDto viewChatDto = new ViewChatDto();

        viewChatDto.setQuestion( chat.getQuestion() );
        viewChatDto.setAnswer( chat.getAnswer() );

        return viewChatDto;
    }

    protected List<ViewChatDto> chatSetToViewChatDtoList(Set<Chat> set) {
        if ( set == null ) {
            return null;
        }

        List<ViewChatDto> list = new ArrayList<ViewChatDto>( set.size() );
        for ( Chat chat : set ) {
            list.add( chatToViewChatDto( chat ) );
        }

        return list;
    }

    protected ChatParticipantDto participantToChatParticipantDto(Participant participant) {
        if ( participant == null ) {
            return null;
        }

        ChatParticipantDto chatParticipantDto = new ChatParticipantDto();

        chatParticipantDto.setName( participant.getName() );
        chatParticipantDto.setSurname( participant.getSurname() );
        chatParticipantDto.setEmail( participant.getEmail() );
        chatParticipantDto.setTcKimlikNo( participant.getTcKimlikNo() );
        chatParticipantDto.setJoinDate( participant.getJoinDate() );
        chatParticipantDto.setJoinTime( participant.getJoinTime() );
        chatParticipantDto.setAge( participant.getAge() );
        chatParticipantDto.setGender( participant.getGender() );
        chatParticipantDto.setChats( chatSetToViewChatDtoList( participant.getChats() ) );

        return chatParticipantDto;
    }

    protected List<ChatParticipantDto> participantSetToChatParticipantDtoList(Set<Participant> set) {
        if ( set == null ) {
            return null;
        }

        List<ChatParticipantDto> list = new ArrayList<ChatParticipantDto>( set.size() );
        for ( Participant participant : set ) {
            list.add( participantToChatParticipantDto( participant ) );
        }

        return list;
    }

    protected Chat viewChatDtoToChat(ViewChatDto viewChatDto) {
        if ( viewChatDto == null ) {
            return null;
        }

        Chat chat = new Chat();

        chat.setQuestion( viewChatDto.getQuestion() );
        chat.setAnswer( viewChatDto.getAnswer() );

        return chat;
    }

    protected Set<Chat> viewChatDtoListToChatSet(List<ViewChatDto> list) {
        if ( list == null ) {
            return null;
        }

        Set<Chat> set = new HashSet<Chat>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( ViewChatDto viewChatDto : list ) {
            set.add( viewChatDtoToChat( viewChatDto ) );
        }

        return set;
    }

    protected Participant chatParticipantDtoToParticipant(ChatParticipantDto chatParticipantDto) {
        if ( chatParticipantDto == null ) {
            return null;
        }

        Participant participant = new Participant();

        participant.setName( chatParticipantDto.getName() );
        participant.setSurname( chatParticipantDto.getSurname() );
        participant.setEmail( chatParticipantDto.getEmail() );
        participant.setTcKimlikNo( chatParticipantDto.getTcKimlikNo() );
        participant.setJoinDate( chatParticipantDto.getJoinDate() );
        participant.setJoinTime( chatParticipantDto.getJoinTime() );
        participant.setAge( chatParticipantDto.getAge() );
        participant.setGender( chatParticipantDto.getGender() );
        participant.setChats( viewChatDtoListToChatSet( chatParticipantDto.getChats() ) );

        return participant;
    }

    protected Set<Participant> chatParticipantDtoListToParticipantSet(List<ChatParticipantDto> list) {
        if ( list == null ) {
            return null;
        }

        Set<Participant> set = new HashSet<Participant>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( ChatParticipantDto chatParticipantDto : list ) {
            set.add( chatParticipantDtoToParticipant( chatParticipantDto ) );
        }

        return set;
    }
}
