package yte.intern.project.application.usecases.common.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.intern.project.application.usecases.common.ENUMS.MessageType;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class MessageResponse {
    private String message;
    private MessageType messageType;
}
