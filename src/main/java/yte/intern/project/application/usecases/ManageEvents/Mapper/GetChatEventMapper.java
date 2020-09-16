package yte.intern.project.application.usecases.ManageEvents.Mapper;


import org.mapstruct.Mapper;
import yte.intern.project.application.usecases.ManageEvents.DTO.Event.GetChatEventDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Event.GetSurveyEventDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Event;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GetChatEventMapper {

    GetChatEventDto mapToDto(Event event);

    Event mapToEntity( GetChatEventDto getChatEventDto);

    List<GetChatEventDto> mapToDto(List<Event> event);

    List<Event> mapToEntity( List<GetChatEventDto> getChatEventDtos);
}
