package yte.intern.project.application.usecases.ManageEvents.Mapper;

import org.mapstruct.Mapper;
import yte.intern.project.application.usecases.ManageEvents.DTO.Event.GetSurveyEventDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Event;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GetSurveyEventMapper {

    GetSurveyEventDto mapToDto(Event event);

    Event mapToEntity( GetSurveyEventDto getSurveyEventDto);

    List<GetSurveyEventDto> mapToDto(List<Event> event);

    List<Event> mapToEntity( List<GetSurveyEventDto> getSurveyEventDtos);
}