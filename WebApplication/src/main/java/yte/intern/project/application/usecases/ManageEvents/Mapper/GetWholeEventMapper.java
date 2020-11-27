package yte.intern.project.application.usecases.ManageEvents.Mapper;

import org.mapstruct.Mapper;
import yte.intern.project.application.usecases.ManageEvents.DTO.Event.GetWholeEventDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Event;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GetWholeEventMapper {

    GetWholeEventDto mapToDto(Event event);

    Event mapToEntity( GetWholeEventDto getWholeEventDto);

    List<GetWholeEventDto> mapToDto(List<Event> event);

    List<Event> mapToEntity( List<GetWholeEventDto> getWholeEventDtos);

}
