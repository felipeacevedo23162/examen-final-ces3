package co.edu.elpoli.ces3.examenfinalces3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import co.edu.elpoli.ces3.examenfinalces3.model.Teacher;
import co.edu.elpoli.ces3.examenfinalces3.repository.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExamenFinalCes3Application {

    public static void main(String[] args) {
        SpringApplication.run(ExamenFinalCes3Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(TeacherRepository repo) {
        return args -> {
            repo.save(new Teacher("Felipe", "Acevedo", "Matemáticas", 3000000.0, "felipe@ejemplo.com"));
            repo.save(new Teacher("Laura", "García", "Inglés", 2800000.0, "laura@ejemplo.com"));

            repo.findAll().forEach(t -> {
                System.out.println(t);
                System.out.println("Salario anual: " + t.calcularSalarioAnual());
            });
        };
    }
}