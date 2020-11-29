package yte.intern.project.application.usecases.ManageEvents.Mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import yte.intern.project.application.usecases.ManageEvents.DTO.Participant.ParticipantDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Participant;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-29T18:08:13+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14.0.1 (Oracle Corporation)"
)
@Component
public class ParticipantMapperImpl implements ParticipantMapper {

    @Override
    public ParticipantDto mapToDto(Participant participant) {
        if ( participant == null ) {
            return null;
        }

        ParticipantDto participantDto = new ParticipantDto();

        participantDto.setName( participant.getName() );
        participantDto.setSurname( participant.getSurname() );
        participantDto.setEmail( participant.getEmail() );
        participantDto.setTcKimlikNo( participant.getTcKimlikNo() );

        return participantDto;
    }

    @Override
    public Participant mapToEntity(ParticipantDto ParticipantDto) {
        if ( ParticipantDto == null ) {
            return null;
        }

        Participant participant = new Participant();

        participant.setName( ParticipantDto.getName() );
        participant.setSurname( ParticipantDto.getSurname() );
        participant.setEmail( ParticipantDto.getEmail() );
        participant.setTcKimlikNo( ParticipantDto.getTcKimlikNo() );

        return participant;
    }

    @Override
    public List<ParticipantDto> mapToDto(List<Participant> participants) {
        if ( participants == null ) {
            return null;
        }

        List<ParticipantDto> list = new ArrayList<ParticipantDto>( participants.size() );
        for ( Participant participant : participants ) {
            list.add( mapToDto( participant ) );
        }

        return list;
    }

    @Override
    public List<Participant> mapToEntity(List<ParticipantDto> ParticipantDtos) {
        if ( ParticipantDtos == null ) {
            return null;
        }

        List<Participant> list = new ArrayList<Participant>( ParticipantDtos.size() );
        for ( ParticipantDto participantDto : ParticipantDtos ) {
            list.add( mapToEntity( participantDto ) );
        }

        return list;
    }
}
