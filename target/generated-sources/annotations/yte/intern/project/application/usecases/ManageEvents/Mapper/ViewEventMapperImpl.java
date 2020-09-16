package yte.intern.project.application.usecases.ManageEvents.Mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import yte.intern.project.application.usecases.ManageEvents.DTO.Event.ViewEventDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.PostSurveyDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.QuestionDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Event;
import yte.intern.project.application.usecases.ManageEvents.Entity.Question;
import yte.intern.project.application.usecases.ManageEvents.Entity.SurveyEventQuestion;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-07T16:16:23+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14.0.1 (Oracle Corporation)"
)
@Component
public class ViewEventMapperImpl implements ViewEventMapper {

    @Override
    public ViewEventDto mapToDto(Event event) {
        if ( event == null ) {
            return null;
        }

        ViewEventDto viewEventDto = new ViewEventDto();

        viewEventDto.setName( event.getName() );
        viewEventDto.setMax( event.getMax() );
        viewEventDto.setCurrent( event.getCurrent() );
        viewEventDto.setStartDate( event.getStartDate() );
        viewEventDto.setStartTime( event.getStartTime() );
        viewEventDto.setEndDate( event.getEndDate() );
        viewEventDto.setEndTime( event.getEndTime() );
        viewEventDto.setLat( event.getLat() );
        viewEventDto.setLng( event.getLng() );
        viewEventDto.setAskAge( event.isAskAge() );
        viewEventDto.setAskGender( event.isAskGender() );
        viewEventDto.setQuestions( questionSetToQuestionDtoList( event.getQuestions() ) );
        viewEventDto.setSurveyQuestions( surveyEventQuestionSetToPostSurveyDtoList( event.getSurveyQuestions() ) );

        return viewEventDto;
    }

    @Override
    public Event mapToEntity(ViewEventDto viewEventDto) {
        if ( viewEventDto == null ) {
            return null;
        }

        Event event = new Event();

        event.setName( viewEventDto.getName() );
        event.setMax( viewEventDto.getMax() );
        event.setCurrent( viewEventDto.getCurrent() );
        event.setStartDate( viewEventDto.getStartDate() );
        event.setEndDate( viewEventDto.getEndDate() );
        event.setStartTime( viewEventDto.getStartTime() );
        event.setEndTime( viewEventDto.getEndTime() );
        event.setLat( viewEventDto.getLat() );
        event.setLng( viewEventDto.getLng() );
        event.setAskAge( viewEventDto.isAskAge() );
        event.setAskGender( viewEventDto.isAskGender() );
        event.setQuestions( questionDtoListToQuestionSet( viewEventDto.getQuestions() ) );
        event.setSurveyQuestions( postSurveyDtoListToSurveyEventQuestionSet( viewEventDto.getSurveyQuestions() ) );

        return event;
    }

    @Override
    public List<ViewEventDto> mapToDto(List<Event> event) {
        if ( event == null ) {
            return null;
        }

        List<ViewEventDto> list = new ArrayList<ViewEventDto>( event.size() );
        for ( Event event1 : event ) {
            list.add( mapToDto( event1 ) );
        }

        return list;
    }

    @Override
    public List<Event> mapToEntity(List<ViewEventDto> viewEventDto) {
        if ( viewEventDto == null ) {
            return null;
        }

        List<Event> list = new ArrayList<Event>( viewEventDto.size() );
        for ( ViewEventDto viewEventDto1 : viewEventDto ) {
            list.add( mapToEntity( viewEventDto1 ) );
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
