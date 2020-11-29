package yte.intern.project.application.usecases.ManageEvents.Mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import yte.intern.project.application.usecases.ManageEvents.DTO.Event.AddEventDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.PostSurveyDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.QuestionDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Event;
import yte.intern.project.application.usecases.ManageEvents.Entity.Question;
import yte.intern.project.application.usecases.ManageEvents.Entity.SurveyEventQuestion;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-28T19:51:49+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14.0.1 (Oracle Corporation)"
)
@Component
public class AddEventMapperImpl implements AddEventMapper {

    @Override
    public AddEventDto mapToDto(Event event) {
        if ( event == null ) {
            return null;
        }

        AddEventDto addEventDto = new AddEventDto();

        addEventDto.setName( event.getName() );
        addEventDto.setMax( event.getMax() );
        addEventDto.setStartDate( event.getStartDate() );
        addEventDto.setStartTime( event.getStartTime() );
        addEventDto.setEndDate( event.getEndDate() );
        addEventDto.setEndTime( event.getEndTime() );
        addEventDto.setLat( event.getLat() );
        addEventDto.setLng( event.getLng() );
        addEventDto.setAskAge( event.isAskAge() );
        addEventDto.setAskGender( event.isAskGender() );
        addEventDto.setQuestions( questionSetToQuestionDtoList( event.getQuestions() ) );
        addEventDto.setSurveyQuestions( surveyEventQuestionSetToPostSurveyDtoList( event.getSurveyQuestions() ) );

        return addEventDto;
    }

    @Override
    public Event mapToEntity(AddEventDto addEventDto) {
        if ( addEventDto == null ) {
            return null;
        }

        Event event = new Event();

        event.setName( addEventDto.getName() );
        event.setMax( addEventDto.getMax() );
        event.setStartDate( addEventDto.getStartDate() );
        event.setEndDate( addEventDto.getEndDate() );
        event.setStartTime( addEventDto.getStartTime() );
        event.setEndTime( addEventDto.getEndTime() );
        event.setLat( addEventDto.getLat() );
        event.setLng( addEventDto.getLng() );
        event.setAskAge( addEventDto.isAskAge() );
        event.setAskGender( addEventDto.isAskGender() );
        event.setQuestions( questionDtoListToQuestionSet( addEventDto.getQuestions() ) );
        event.setSurveyQuestions( postSurveyDtoListToSurveyEventQuestionSet( addEventDto.getSurveyQuestions() ) );

        return event;
    }

    @Override
    public List<AddEventDto> mapToDto(List<Event> event) {
        if ( event == null ) {
            return null;
        }

        List<AddEventDto> list = new ArrayList<AddEventDto>( event.size() );
        for ( Event event1 : event ) {
            list.add( mapToDto( event1 ) );
        }

        return list;
    }

    @Override
    public List<Event> mapToEntity(List<AddEventDto> addEventDto) {
        if ( addEventDto == null ) {
            return null;
        }

        List<Event> list = new ArrayList<Event>( addEventDto.size() );
        for ( AddEventDto addEventDto1 : addEventDto ) {
            list.add( mapToEntity( addEventDto1 ) );
        }

        return list;
    }

    protected QuestionDto questionToQuestionDto(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionDto questionDto = new QuestionDto();

        questionDto.setQuestionName( question.getQuestionName() );

        return questionDto;
    }

    protected List<QuestionDto> questionSetToQuestionDtoList(Set<Question> set) {
        if ( set == null ) {
            return null;
        }

        List<QuestionDto> list = new ArrayList<QuestionDto>( set.size() );
        for ( Question question : set ) {
            list.add( questionToQuestionDto( question ) );
        }

        return list;
    }

    protected PostSurveyDto surveyEventQuestionToPostSurveyDto(SurveyEventQuestion surveyEventQuestion) {
        if ( surveyEventQuestion == null ) {
            return null;
        }

        PostSurveyDto postSurveyDto = new PostSurveyDto();

        postSurveyDto.setSurveyQuestionName( surveyEventQuestion.getSurveyQuestionName() );

        return postSurveyDto;
    }

    protected List<PostSurveyDto> surveyEventQuestionSetToPostSurveyDtoList(Set<SurveyEventQuestion> set) {
        if ( set == null ) {
            return null;
        }

        List<PostSurveyDto> list = new ArrayList<PostSurveyDto>( set.size() );
        for ( SurveyEventQuestion surveyEventQuestion : set ) {
            list.add( surveyEventQuestionToPostSurveyDto( surveyEventQuestion ) );
        }

        return list;
    }

    protected Question questionDtoToQuestion(QuestionDto questionDto) {
        if ( questionDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setQuestionName( questionDto.getQuestionName() );

        return question;
    }

    protected Set<Question> questionDtoListToQuestionSet(List<QuestionDto> list) {
        if ( list == null ) {
            return null;
        }

        Set<Question> set = new HashSet<Question>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( QuestionDto questionDto : list ) {
            set.add( questionDtoToQuestion( questionDto ) );
        }

        return set;
    }

    protected SurveyEventQuestion postSurveyDtoToSurveyEventQuestion(PostSurveyDto postSurveyDto) {
        if ( postSurveyDto == null ) {
            return null;
        }

        SurveyEventQuestion surveyEventQuestion = new SurveyEventQuestion();

        surveyEventQuestion.setSurveyQuestionName( postSurveyDto.getSurveyQuestionName() );

        return surveyEventQuestion;
    }

    protected Set<SurveyEventQuestion> postSurveyDtoListToSurveyEventQuestionSet(List<PostSurveyDto> list) {
        if ( list == null ) {
            return null;
        }

        Set<SurveyEventQuestion> set = new HashSet<SurveyEventQuestion>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( PostSurveyDto postSurveyDto : list ) {
            set.add( postSurveyDtoToSurveyEventQuestion( postSurveyDto ) );
        }

        return set;
    }
}
