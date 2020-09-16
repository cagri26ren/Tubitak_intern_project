package yte.intern.project.application.usecases.ManageEvents.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yte.intern.project.application.usecases.ManageEvents.Entity.Admin;

public interface UserRepository extends JpaRepository<Admin,Long>{

    @Query("select a from Admin a where a.username = :username")
    Admin findAByUsername(@Param("username") String username);
}
