package yte.intern.project.application.usecases.ManageEvents.Mapper;

import org.mapstruct.Mapper;
import yte.intern.project.application.usecases.ManageEvents.DTO.Event.AddEventDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Event;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddEventMapper {
    AddEventDto mapToDto(Event event);

    Event mapToEntity( AddEventDto addEventDto);

    List<AddEventDto> mapToDto(List<Event> event);

    List<Event> mapToEntity( List<AddEventDto> addEventDto);
}

