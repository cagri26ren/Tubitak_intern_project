package yte.intern.project.application.usecases.ManageEvents.Mapper;


import org.mapstruct.Mapper;
import yte.intern.project.application.usecases.ManageEvents.DTO.Participant.ParticipantDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Participant;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {
    ParticipantDto mapToDto(Participant participant);

    Participant mapToEntity( ParticipantDto ParticipantDto);

    List<ParticipantDto> mapToDto(List<Participant> participants);

    List<Participant> mapToEntity( List<ParticipantDto> ParticipantDtos);
}
