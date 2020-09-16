package yte.intern.project.application.usecases.ManageEvents.Repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import yte.intern.project.application.usecases.ManageEvents.Entity.Event;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {

    @Query("select e from Event e where e.startDate >= :startDate")
    List<Event> findEventByStartDate(@Param("startDate") LocalDate startDate , Sort sort );

    @Query("select e from Event e where e.startDate <= :startDate")
    List<Event> findEventThatStarted(@Param("startDate") LocalDate startDate , Sort sort );

    @Query("select e from Event e where e.name = :name")
    Event findEventByName(@Param("name") String name );

    @Transactional
    void deleteByName(String name );
}
