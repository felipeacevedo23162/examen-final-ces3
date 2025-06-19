package co.edu.elpoli.ces3.examenfinalces3.controller;

import co.edu.elpoli.ces3.examenfinalces3.model.Teacher;
import co.edu.elpoli.ces3.examenfinalces3.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @PostMapping
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @GetMapping("/salarios-anuales")
    public List<Map<String, Object>> getSalariosAnuales() {
        List<Map<String, Object>> response = new ArrayList<>();
        for (Teacher teacher : teacherRepository.findAll()) {
            Map<String, Object> item = new HashMap<>();
            item.put("nombre", teacher.getName() + " " + teacher.getLastName());
            item.put("salarioAnual", teacher.calcularSalarioAnual());
            response.add(item);
        }
        return response;
    }

    @GetMapping("/{id}/salario-anual")
    public Map<String, Object> getSalarioAnual(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Teacher teacher = teacherRepository.findById(id).orElseThrow();
            response.put("nombre", teacher.getName() + " " + teacher.getLastName());
            response.put("salarioAnual", teacher.calcularSalarioAnual());
        } catch (NoSuchElementException e) {
            response.put("error", "Profesor no encontrado");
        }
        return response;
    }
}
