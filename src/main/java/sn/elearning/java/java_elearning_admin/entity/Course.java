package sn.elearning.java.java_elearning_admin.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "course_id", nullable = false, updatable = false)
    private Long courseId;

    @Basic
    @Column(name = "course_name", nullable= false, length = 45)
    private String courseName;

    @Basic
    @Column(name = "course_duration", nullable = false, length = 45)
    private String courseDuration;

    @Basic
    @Column(name = "course_description", nullable = false, length = 64)
    private String courseDescription;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "enrolled_in",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id",
            nullable = false,
            referencedColumnName = "instructor_id")
    private Instructor instructor;

    public void assignStudentToCourse(Student student) {
        students.add(student);
        student.getCourses().add(this);
    }

    public void deleteStudentFromCourse(Student student) {
        students.remove(student);
        student.getCourses().remove(this);
    }
}
