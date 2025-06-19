package co.edu.elpoli.ces3.examenfinalces3.repository;

import co.edu.elpoli.ces3.examenfinalces3.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {


    @Query("SELECT t FROM Teacher t WHERE t.salary > ?1")
    List<Teacher> findTeachersWithSalaryGreaterThan(Double salary);

    @Query("SELECT t FROM Teacher t WHERE t.subject = ?1")
    List<Teacher> findBySubject(String subject);
}
