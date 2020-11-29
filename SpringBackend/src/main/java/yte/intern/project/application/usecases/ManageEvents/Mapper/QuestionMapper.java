package yte.intern.project.application.usecases.ManageEvents.Mapper;


import org.mapstruct.Mapper;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.QuestionDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Question;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    QuestionDto mapToDto(Question question);

    Question mapToEntity( QuestionDto questionDto);

    List<QuestionDto> mapToDto(List<Question> questions);

    List<Question> mapToEntity( List<QuestionDto> questionDtos);
}