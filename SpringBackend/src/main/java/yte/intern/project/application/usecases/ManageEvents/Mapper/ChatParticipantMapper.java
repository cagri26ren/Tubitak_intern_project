package yte.intern.project.application.usecases.ManageEvents.Mapper;

import org.mapstruct.Mapper;
import yte.intern.project.application.usecases.ManageEvents.DTO.Participant.ChatParticipantDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Event;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatParticipantMapper {
    ChatParticipantDto mapToDto(Event event);

    Event mapToEntity( ChatParticipantDto chatParticipantDto);

    List<ChatParticipantDto> mapToDto(List<Event> event);

    List<Event> mapToEntity( List<ChatParticipantDto> chatParticipantDtos);
}
