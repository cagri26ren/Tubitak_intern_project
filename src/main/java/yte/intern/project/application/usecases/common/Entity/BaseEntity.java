package yte.intern.project.application.usecases.common.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
@EqualsAndHashCode( of = "id")
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
    @Column(name = "ID")
    private Long id;

    @Version
    @Column(name = "VERSION")
    private Long version;

    @CreatedDate
    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    private LocalDateTime lastModifiedDate;
}
