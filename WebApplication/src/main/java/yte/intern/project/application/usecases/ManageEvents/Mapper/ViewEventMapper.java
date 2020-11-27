package yte.intern.project.application.usecases.ManageEvents.Mapper;

import org.mapstruct.Mapper;
import yte.intern.project.application.usecases.ManageEvents.DTO.Event.ViewEventDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Event;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ViewEventMapper {

    ViewEventDto mapToDto(Event event);

    Event mapToEntity( ViewEventDto viewEventDto);

    List<ViewEventDto> mapToDto(List<Event> event);

    List<Event> mapToEntity( List<ViewEventDto> viewEventDto);

}