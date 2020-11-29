package yte.intern.project.application.usecases.ManageEvents.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.intern.project.application.usecases.common.Entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name="idgen",sequenceName = "EVENT_SEQ")
public class Chat extends BaseEntity {

    @Column( name = "QUESTION")
    private String question;

    @Column( name = "ANSWER")
    private String answer;

}
