package yte.intern.project.application.usecases.ManageEvents.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.project.application.usecases.ManageEvents.Entity.SurveyEventQuestion;

public interface SurveyEventQuestionRepository extends JpaRepository<SurveyEventQuestion,Long> {
}
