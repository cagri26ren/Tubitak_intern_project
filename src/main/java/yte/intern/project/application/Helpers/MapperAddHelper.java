package yte.intern.project.application.Helpers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yte.intern.project.application.usecases.ManageEvents.DTO.Event.AddEventDto;
import yte.intern.project.application.usecases.ManageEvents.DTO.Question.QuestionDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Event;
import yte.intern.project.application.usecases.ManageEvents.Entity.Question;
import yte.intern.project.application.usecases.ManageEvents.Entity.SurveyQuestion;
import yte.intern.project.application.usecases.ManageEvents.Mapper.AddEventMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class MapperAddHelper {

    private final AddEventMapper addEventMapper;

    public AddEventDto mapToDto(Event event) {
        Set<Question> questionSet = event.getQuestions();

        List<QuestionDto> questions = new ArrayList<>();

        for( Question q : questionSet ){
            QuestionDto questionDto = new QuestionDto(q.getQuestionName());
            questions.add(questionDto);
        }
        System.out.println(questions);

        AddEventDto addEventDto = addEventMapper.mapToDto(event);
        addEventDto.setQuestions(questions);

        addEventDto.setAskAge( event.isAskAge());
        addEventDto.setAskGender( event.isAskGender());

        return addEventDto;
    }

    public Event mapToEntity(AddEventDto addEventDto) {
        List<QuestionDto> questions = addEventDto.getQuestions();
        Set<Question> questionSet = new HashSet<>();

        for( QuestionDto q : questions ){
            Question question = new Question(q.getQuestionName());
            questionSet.add(question);
        }
        System.out.println(questionSet);

        Event event = addEventMapper.mapToEntity(addEventDto);
        event.setQuestions(questionSet);
        event.setAskAge( addEventDto.isAskAge());
        event.setAskGender( addEventDto.isAskGender());

        return event;
    }

    public List<AddEventDto> mapToDto(List<Event> event) {
        List<AddEventDto> addEventDtos = new ArrayList<>();
        for(Event e : event){
            addEventDtos.add( mapToDto(e));
        }
        return addEventDtos;
    }

    public List<Event> mapToEntity(List<AddEventDto> addEventDto) {
        List<Event> events =addEventMapper.mapToEntity(addEventDto);

        return events;
    }
}
