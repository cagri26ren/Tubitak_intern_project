package yte.intern.project.application.usecases.ManageEvents.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.project.application.usecases.ManageEvents.Entity.SurveyQuestion;

public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestion,Long> {

}
