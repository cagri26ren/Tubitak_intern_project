package yte.intern.project.application.usecases.ManageEvents.Mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.QuestionDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Question;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-29T18:08:13+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14.0.1 (Oracle Corporation)"
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Override
    public QuestionDto mapToDto(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionDto questionDto = new QuestionDto();

        questionDto.setQuestionName( question.getQuestionName() );

        return questionDto;
    }

    @Override
    public Question mapToEntity(QuestionDto questionDto) {
        if ( questionDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setQuestionName( questionDto.getQuestionName() );

        return question;
    }

    @Override
    public List<QuestionDto> mapToDto(List<Question> questions) {
        if ( questions == null ) {
            return null;
        }

        List<QuestionDto> list = new ArrayList<QuestionDto>( questions.size() );
        for ( Question question : questions ) {
            list.add( mapToDto( question ) );
        }

        return list;
    }

    @Override
    public List<Question> mapToEntity(List<QuestionDto> questionDtos) {
        if ( questionDtos == null ) {
            return null;
        }

        List<Question> list = new ArrayList<Question>( questionDtos.size() );
        for ( QuestionDto questionDto : questionDtos ) {
            list.add( mapToEntity( questionDto ) );
        }

        return list;
    }
}
