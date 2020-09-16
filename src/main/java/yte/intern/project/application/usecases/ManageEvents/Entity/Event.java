package yte.intern.project.application.usecases.ManageEvents.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.intern.project.application.usecases.common.Entity.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name="idgen",sequenceName = "EVENT_SEQ")
public class Event extends BaseEntity {

    @Column( name = "NAME",unique = true)
    private String name;

    @Column( name = "MAX")
    private Long max;

    @Column(name = "CURRENT")
    private Long current;

    @Column( name = "START_DATE")
    private LocalDate startDate;

    @Column( name = "END_DATE")
    private LocalDate endDate;

    @Column( name = "START_TIME")
    private LocalTime startTime;

    @Column(name = "END_TIME")
    private LocalTime endTime;

    @Column( name = "LATITUDE")
    private Double lat;

    @Column( name = "LONGITUDE")
    private Double lng;

    @Column( name = "ASK_AGE")
    private boolean askAge;

    @Column( name = "ASK_GENDER")
    private boolean askGender;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "EVENT_ID")
    private Set<Question> questions;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "EVENT_ID")
    private Set<Participant> participants;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "EVENT_ID")
    private Set<SurveyEventQuestion> surveyQuestions;

}
