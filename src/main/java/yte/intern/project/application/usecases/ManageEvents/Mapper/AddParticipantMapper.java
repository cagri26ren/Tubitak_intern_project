package yte.intern.project.application.usecases.ManageEvents.Mapper;

import org.mapstruct.Mapper;
import yte.intern.project.application.usecases.ManageEvents.DTO.Participant.AddParticipantDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Participant;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddParticipantMapper {
    AddParticipantDto mapToDto(Participant participant);

    Participant mapToEntity( AddParticipantDto addParticipantDto);

    List<AddParticipantDto> mapToDto(List<Participant> participants);

    List<Participant> mapToEntity( List<AddParticipantDto> addParticipantDtos);
}