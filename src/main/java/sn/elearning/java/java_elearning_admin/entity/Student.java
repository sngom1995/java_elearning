package sn.elearning.java.java_elearning_admin.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id", nullable = false, updatable = false)
    private Long studentId;
    @Basic
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Basic
    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Basic
    @Column(name = "level", nullable = false, length = 64)
    private String level;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @ManyToMany(mappedBy = "students",fetch = FetchType.LAZY)
    private Set<Course> courses = new HashSet<>();
}
