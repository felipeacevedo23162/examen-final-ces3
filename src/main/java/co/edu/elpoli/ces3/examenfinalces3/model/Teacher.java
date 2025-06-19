package co.edu.elpoli.ces3.examenfinalces3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String lastName;

    @NotNull
    @Column(nullable = false)
    private String subject;

    @NotNull
    @Column(nullable = false)
    private Double salary;

    @NotNull
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    // Constructor por defecto
    public Teacher() {}

    // Constructor con parámetros (sin id)
    public Teacher(String name, String lastName, String subject, Double salary, String email) {
        this.name = name;
        this.lastName = lastName;
        this.subject = subject;
        this.salary = salary;
        this.email = email;
    }

    // Getters y setters
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Double calcularSalarioAnual() {
        return this.salary * 12;
    }

    @Override
    public String toString() {
        return String.format("Profesor: %s %s, Materia: %s, Salario: %.2f, Email: %s",
                name, lastName, subject, salary, email);
    }
}
