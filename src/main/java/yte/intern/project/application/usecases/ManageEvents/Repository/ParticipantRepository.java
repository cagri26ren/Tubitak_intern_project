package yte.intern.project.application.usecases.ManageEvents.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yte.intern.project.application.usecases.ManageEvents.Entity.Event;
import yte.intern.project.application.usecases.ManageEvents.Entity.Participant;

public interface ParticipantRepository extends JpaRepository<Participant,Long> {

    @Query("select p from Participant p where p.tcKimlikNo = :tcKimlikNo")
    Participant findParticipantByTcKimlikNo(@Param("tcKimlikNo") String tcKimlikNo );
}
