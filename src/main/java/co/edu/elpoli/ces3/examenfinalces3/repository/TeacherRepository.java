package co.edu.elpoli.ces3.examenfinalces3.repository;

import co.edu.elpoli.ces3.examenfinalces3.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    // Puedes agregar @Query personalizados aquí si deseas
}
