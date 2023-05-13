package sn.elearning.java.java_elearning_admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.elearning.java.java_elearning_admin.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
