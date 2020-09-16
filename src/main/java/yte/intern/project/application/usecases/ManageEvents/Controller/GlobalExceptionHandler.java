package yte.intern.project.application.usecases.ManageEvents.Controller;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import yte.intern.project.application.usecases.common.DTO.MessageResponse;
import yte.intern.project.application.usecases.common.ENUMS.MessageType;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler( value = EntityNotFoundException.class)
    public MessageResponse handleEntityException( EntityNotFoundException exception){
        return new MessageResponse(exception.getMessage(),MessageType.ERROR);
    }

    @ExceptionHandler( value = IllegalArgumentException.class)
    public MessageResponse handleArgumentException( IllegalArgumentException exception){
        return new MessageResponse(exception.getMessage(), MessageType.ERROR);
    }
    @ExceptionHandler( value = MethodArgumentNotValidException.class)
    public MessageResponse handleValidException( MethodArgumentNotValidException exception ){
        return new MessageResponse(exception.getMessage() + "" + exception.getBindingResult() + "" + exception.getParameter(),MessageType.ERROR);
    }

}
