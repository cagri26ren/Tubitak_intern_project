package yte.intern.project.application.Helpers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.QuestionDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Event.ViewEventDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Event;
import yte.intern.project.application.usecases.ManageEvents.Entity.Question;
import yte.intern.project.application.usecases.ManageEvents.Mapper.ViewEventMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MapperViewHelper {

    private final ViewEventMapper viewEventMapper;

    public ViewEventDto mapToDto(Event event) {
        Set<Question> questionSet = event.getQuestions();

        List<QuestionDto> questions = new ArrayList<>();

        for( Question q : questionSet ){
            QuestionDto questionDto = new QuestionDto(q.getQuestionName());
            questions.add(questionDto);
        }
        System.out.println(questions);

        ViewEventDto viewEventDto = viewEventMapper.mapToDto(event);
        viewEventDto.setQuestions(questions);

        viewEventDto.setAskAge( event.isAskAge());
        viewEventDto.setAskGender( event.isAskGender());

        return viewEventDto;
    }

    public Event mapToEntity(ViewEventDto viewEventDto) {
        List<QuestionDto> questions = viewEventDto.getQuestions();

        Set<Question> questionSet = new HashSet<>();

        for( QuestionDto q : questions ){
            Question question = new Question(q.getQuestionName());
            questionSet.add(question);
        }
        System.out.println(questionSet);

        Event event = viewEventMapper.mapToEntity(viewEventDto);
        event.setQuestions(questionSet);

        event.setAskAge( viewEventDto.isAskAge());
        event.setAskGender( viewEventDto.isAskGender());

        return event;
    }

    public List<ViewEventDto> mapToDto(List<Event> event) {
        List<ViewEventDto> viewEventDtos = new ArrayList<>();
        for(Event e : event){
            viewEventDtos.add( mapToDto(e));
        }
        return viewEventDtos;
    }

    public List<Event> mapToEntity(List<ViewEventDto> viewEventDtos) {
        List<Event> events = new ArrayList<>();
        for(ViewEventDto a : viewEventDtos){
            events.add( mapToEntity(a));
        }
        return events;
    }
}
