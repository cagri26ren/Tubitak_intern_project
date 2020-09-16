package yte.intern.project.application.usecases.ManageEvents.Mapper;

import org.mapstruct.Mapper;
import yte.intern.project.application.usecases.ManageEvents.DTO.Chat.ViewChatDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Chat;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ViewChatMapper{
    ViewChatDto mapToDto(Chat chat);

    Chat mapToEntity( ViewChatDto viewChatDto);

    List<ViewChatDto> mapToDto(List<Chat> chats);

    List<Chat> mapToEntity( List<ViewChatDto> viewChatDtos);
}
