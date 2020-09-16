package yte.intern.project.application.usecases.ManageEvents.Entity;

import lombok.*;
import yte.intern.project.application.usecases.common.Entity.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@SequenceGenerator(name="idgen",sequenceName = "EVENT_SEQ")
public class Participant extends BaseEntity {

    @Column( name = "NAME")
    private String name;

    @Column( name = "SURNAME")
    private String surname;

    @Column( name = "E_MAIL")
    private String email;

    @Column( name = "TC_KIMLIK_NO")
    private String tcKimlikNo;

    @Column( name = "JOIN_DATE")
    private LocalDate joinDate;

    @Column(name ="JOIN_TIME")
    private LocalTime joinTime;

    @Column(name="QR_CODE")
    private byte[] qrCode;

    @Column(name="AGE")
    private Long age;

    @Column(name="GENDER")
    private String gender;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "PARTICIPANT_ID")
    private Set<Answer> answers;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "PARTICIPANT_ID")
    private Set<SurveyQuestion> surveyQuestions;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "PARTICIPANT_ID")
    private Set<Chat> chats;



}