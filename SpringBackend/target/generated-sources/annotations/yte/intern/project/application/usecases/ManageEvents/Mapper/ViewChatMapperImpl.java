package yte.intern.project.application.usecases.ManageEvents.Mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import yte.intern.project.application.usecases.ManageEvents.DTO.Chat.ViewChatDto;
import yte.intern.project.application.usecases.ManageEvents.Entity.Chat;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-29T18:08:13+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14.0.1 (Oracle Corporation)"
)
@Component
public class ViewChatMapperImpl implements ViewChatMapper {

    @Override
    public ViewChatDto mapToDto(Chat chat) {
        if ( chat == null ) {
            return null;
        }

        ViewChatDto viewChatDto = new ViewChatDto();

        viewChatDto.setQuestion( chat.getQuestion() );
        viewChatDto.setAnswer( chat.getAnswer() );

        return viewChatDto;
    }

    @Override
    public Chat mapToEntity(ViewChatDto viewChatDto) {
        if ( viewChatDto == null ) {
            return null;
        }

        Chat chat = new Chat();

        chat.setQuestion( viewChatDto.getQuestion() );
        chat.setAnswer( viewChatDto.getAnswer() );

        return chat;
    }

    @Override
    public List<ViewChatDto> mapToDto(List<Chat> chats) {
        if ( chats == null ) {
            return null;
        }

        List<ViewChatDto> list = new ArrayList<ViewChatDto>( chats.size() );
        for ( Chat chat : chats ) {
            list.add( mapToDto( chat ) );
        }

        return list;
    }

    @Override
    public List<Chat> mapToEntity(List<ViewChatDto> viewChatDtos) {
        if ( viewChatDtos == null ) {
            return null;
        }

        List<Chat> list = new ArrayList<Chat>( viewChatDtos.size() );
        for ( ViewChatDto viewChatDto : viewChatDtos ) {
            list.add( mapToEntity( viewChatDto ) );
        }

        return list;
    }
}
