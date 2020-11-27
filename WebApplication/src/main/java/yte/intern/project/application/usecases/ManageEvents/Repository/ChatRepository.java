package yte.intern.project.application.usecases.ManageEvents.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yte.intern.project.application.usecases.ManageEvents.Entity.Chat;
import yte.intern.project.application.usecases.ManageEvents.Entity.Event;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat,Long> {

    @Query("select c from Chat c where c.answer = :answer")
    List<Chat> findAllUnansweredChats(@Param("answer") String answer );

    @Query("select c from Chat c where c.question = :question AND c.answer = :answer")
    List<Chat> findChatByQuestion( @Param("question") String question, @Param("answer") String answer );
}
