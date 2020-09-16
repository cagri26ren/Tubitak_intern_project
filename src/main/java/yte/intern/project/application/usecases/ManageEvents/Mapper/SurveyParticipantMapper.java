package yte.intern.project.application.usecases.ManageEvents.Mapper;

import org.mapstruct.Mapper;
import yte.intern.project.application.usecases.ManageEvents.DTO.Participant.SurveyParticipantDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Event;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SurveyParticipantMapper {

    SurveyParticipantDto mapToDto(Event event);

    Event mapToEntity( SurveyParticipantDto surveyParticipantDto);

    List<SurveyParticipantDto> mapToDto(List<Event> event);

    List<Event> mapToEntity( List<SurveyParticipantDto> surveyParticipantDtos);
}