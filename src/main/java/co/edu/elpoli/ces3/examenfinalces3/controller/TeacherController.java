package co.edu.elpoli.ces3.examenfinalces3.controller;

import co.edu.elpoli.ces3.examenfinalces3.model.Teacher;
import co.edu.elpoli.ces3.examenfinalces3.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.*;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    // Crear un nuevo profesor
    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        return ResponseEntity.ok(teacherRepository.save(teacher));
    }

    // Obtener todos los profesores
    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    // Obtener un profesor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        return teacherRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar un profesor por ID
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher newData) {
        return teacherRepository.findById(id).map(teacher -> {
            teacher.setName(newData.getName());
            teacher.setLastName(newData.getLastName());
            teacher.setSubject(newData.getSubject());
            teacher.setSalary(newData.getSalary());
            teacher.setEmail(newData.getEmail());
            return ResponseEntity.ok(teacherRepository.save(teacher));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un profesor por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        Optional<Teacher> teacherOpt = teacherRepository.findById(id);
        if (teacherOpt.isPresent()) {
            teacherRepository.delete(teacherOpt.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Salario anual de todos los profesores
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

    // Salario anual por ID
    @GetMapping("/{id}/salario-anual")
    public ResponseEntity<Map<String, Object>> getSalarioAnual(@PathVariable Long id) {
        return teacherRepository.findById(id).map(teacher -> {
            Map<String, Object> response = new HashMap<>();
            response.put("nombre", teacher.getName() + " " + teacher.getLastName());
            response.put("salarioAnual", teacher.calcularSalarioAnual());
            return ResponseEntity.ok(response);
        }).orElse(ResponseEntity.notFound().build());
    }
}
