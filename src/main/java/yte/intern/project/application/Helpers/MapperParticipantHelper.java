package yte.intern.project.application.Helpers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yte.intern.project.application.usecases.ManageEvents.DTO.Participant.AddParticipantDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.AnswerDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Answer;
import yte.intern.project.application.usecases.ManageEvents.Entity.Participant;
import yte.intern.project.application.usecases.ManageEvents.Mapper.AddParticipantMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MapperParticipantHelper {

    private final AddParticipantMapper addParticipantMapper;

    public AddParticipantDto mapToDto(Participant participant) {
        Set<Answer> answerSet = participant.getAnswers();

        List<AnswerDto> answerDtos = new ArrayList<>();

        for( Answer a : answerSet ){
            AnswerDto answerDto = new AnswerDto(a.getQuestionName(),a.getAnswer());
            answerDtos.add(answerDto);
        }
        AddParticipantDto addParticipantDto = addParticipantMapper.mapToDto(participant);
        addParticipantDto.setAnswers(answerDtos);

        return addParticipantDto;
    }

    public Participant mapToEntity(AddParticipantDto addParticipantDto) {
        List<AnswerDto> answerDtos = addParticipantDto.getAnswers();
        Set<Answer> answerSet = new HashSet<>();

        for( AnswerDto a : answerDtos ){
            Answer answer = new Answer(a.getQuestionName(),a.getAnswer());
            answerSet.add(answer);
        }

        Participant participant = addParticipantMapper.mapToEntity(addParticipantDto);
        participant.setAnswers(answerSet);

        return participant;
    }

    public List<AddParticipantDto> mapToDto(List<Participant> participants) {
        List<AddParticipantDto> addParticipantDtos = new ArrayList<>();
        for(Participant p : participants){
            addParticipantDtos.add( mapToDto(p));
        }
        return addParticipantDtos;
    }

    public List<Participant> mapToEntity(List<AddParticipantDto> addParticipantDtos) {
        List<Participant> participants = new ArrayList<>();
        for(AddParticipantDto a : addParticipantDtos){
            participants.add( mapToEntity(a));
        }
        return participants;
    }
}