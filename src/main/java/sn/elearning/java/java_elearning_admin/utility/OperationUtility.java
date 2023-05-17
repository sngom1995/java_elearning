package sn.elearning.java.java_elearning_admin.utility;

import jakarta.persistence.EntityNotFoundException;
import sn.elearning.java.java_elearning_admin.entity.*;
import sn.elearning.java.java_elearning_admin.repository.*;

import java.util.List;

public class OperationUtility {

    public static void usersOperations(UserRepository userRepository) {
        createUsers(userRepository);
        updateUsers(userRepository);
        deleteUsers(userRepository);
        fetchUsers(userRepository);
    }

    public static void rolesOperations(RoleRepository roleRepository) {
        createRoles(roleRepository);
        updateRoles(roleRepository);
        deleteRoles(roleRepository);
        fetchRoles(roleRepository);
    }
    public static void instructorOperations(InstructorRepository instructorRepository, UserRepository userRepository, RoleRepository roleRepository) {
        createInstructor(instructorRepository, userRepository, roleRepository);
        updateInstructor(instructorRepository);
        deleteInstructor(instructorRepository);
        fetchInstructor(instructorRepository);
    }

    public static void assignRoleToUser(UserRepository userRepository, RoleRepository roleRepository) {
       Role role = roleRepository.findByRoleName("ROLE_ADMIN");
         List<User> users = userRepository.findAll();
         users.forEach(user -> {
             user.assignRoleToUser(role);
             userRepository.save(user);
         });

    }

    public static void studentOperations(StudentRepository studentRepository, UserRepository userRepository, RoleRepository roleRepository) {
        createStudent(studentRepository, userRepository, roleRepository);
        updateStudent(studentRepository);
        deleteStudent(studentRepository);
        fetchStudent(studentRepository);
    }
    
    public static void courseOperations(CourseRepository courseRepository, InstructorRepository instructorRepository, StudentRepository studentRepository) {
        createCourse(courseRepository, instructorRepository);
        updateCourse(courseRepository);
        deleteCourse(courseRepository);
        fetchCourse(courseRepository);
    }

    private static void fetchCourse(CourseRepository courseRepository) {
        courseRepository.findAll().forEach(System.out::println);
    }

    private static void deleteCourse(CourseRepository courseRepository) {
        Course course = courseRepository.findById(1L).orElseThrow(()->
                new EntityNotFoundException("Course not found"));
        courseRepository.delete(course);
    }

    private static void updateCourse(CourseRepository courseRepository) {
        Course course = courseRepository.findById(1L).orElseThrow(()->
                new EntityNotFoundException("Course not found"));
        course.setCourseName("Java 17");
        courseRepository.save(course);
    }

    private static void createCourse(CourseRepository courseRepository, InstructorRepository instructorRepository) {
        Instructor instructor = instructorRepository.findById(1L).orElseThrow(()->
                new EntityNotFoundException("Instructor not found"));
        Course course = Course.builder()
                .courseName("Java")
                .courseDescription("Java course")
                .courseDuration("3 months")
                .instructor(instructor)
                .build();
        courseRepository.save(course);
        Course course1 = Course.builder()
                .courseName("Python")
                .courseDescription("Python course")
                .courseDuration("3 months")
                .instructor(instructor)
                .build();
        courseRepository.save(course1);

    }

    private static void fetchStudent(StudentRepository studentRepository) {
        studentRepository.findAll().forEach(System.out::println);
    }

    private static void deleteStudent(StudentRepository studentRepository) {
        studentRepository.deleteById(1L);
    }

    private static void updateStudent(StudentRepository studentRepository) {
        Student student = studentRepository.findById(1L).orElseThrow(()->
                new EntityNotFoundException("Student not found"));
        student.setFirstName("Babacar");
        studentRepository.save(student);
    }

    public static void createStudent(StudentRepository studentRepository, UserRepository userRepository, RoleRepository roleRepository) {
        Role role = roleRepository.findByRoleName("ROLE_STUDENT");
        if (role == null) {
            throw new EntityNotFoundException("Role not found");
        }
        User user1 = User.builder()
                .email("babs@student.com")
                .password("student")
                .build();
        userRepository.save(user1);
        user1.assignRoleToUser(role);
        Student student = Student.builder()
                .firstName("Babacar")
                .lastName("Diallo")
                .user(user1)
                .build();
        studentRepository.save(student);
    }

    private static void fetchInstructor(InstructorRepository instructorRepository) {
        instructorRepository.findAll().forEach(System.out::println);
    }

    private static void updateInstructor(InstructorRepository instructorRepository) {
        Instructor instructor = instructorRepository.findById(1L).orElseThrow(()->
                new EntityNotFoundException("Instructor not found"));
        instructor.setFirstName("Mamadou");
        instructorRepository.save(instructor);
    }

    private static void deleteInstructor(InstructorRepository instructorRepository) {
        Instructor instructor = instructorRepository.findById(1L).orElseThrow(()->
                new EntityNotFoundException("Instructor not found"));
        instructorRepository.delete(instructor);
    }

    private static void createInstructor(InstructorRepository instructorRepository, UserRepository userRepository, RoleRepository roleRepository) {
        Role role = roleRepository.findByRoleName("ROLE_TEACHER");
        User user1 = User.builder()
                .email("instructor@instructor.com")
                .password("instructor")
                .build();
        userRepository.save(user1);
        user1.assignRoleToUser(role);

        Instructor instructor1 = Instructor.builder()
                .firstName("Samba")
                .lastName("Diallo")
                .summary("Samba Diallo est un formateur en Java")
                .user(user1)
                .build();
        instructorRepository.save(instructor1);
        User user2 = User.builder()
                .email("instructor2@instructor.com")
                .password("instructor2")
                .build();
        userRepository.save(user2);
        user2.assignRoleToUser(role);
        Instructor instructor2 = Instructor.builder()
                .firstName("Moussa")
                .lastName("Ngom")
                .summary("Moussa Ngom est un formateur en Java")
                .user(user2)
                .build();
        instructorRepository.save(instructor2);
    }

    private static void fetchRoles(RoleRepository roleRepository) {
        roleRepository.findAll().forEach(System.out::println);
    }

    private static void deleteRoles(RoleRepository roleRepository) {
        Role role = roleRepository.findById(1L).orElseThrow(()->
                new EntityNotFoundException("Role not found"));
        roleRepository.delete(role);
    }

    private static void updateRoles(RoleRepository roleRepository) {
        Role role = roleRepository.findById(1L).orElseThrow(()->
                new EntityNotFoundException("Role not found"));
        role.setRoleName("ROLE_ADMIN");
        roleRepository.save(role);
    }

    private static void createRoles(RoleRepository roleRepository) {
        Role role1 = Role.builder()
                .roleName("ROLE_ADMIN")
                .build();
        roleRepository.save(role1);
        Role role2 = Role.builder()
                .roleName("ROLE_TEACHER")
                .build();
        roleRepository.save(role2);
        Role role3 = Role.builder()
                .roleName("ROLE_STUDENT")
                .build();
        roleRepository.save(role3);
    }

    private static void fetchUsers(UserRepository userRepository) {
        userRepository.findAll().forEach(System.out::println);
    }

    private static void deleteUsers(UserRepository userRepository) {
        User user = userRepository.findById(1L).orElseThrow(()->
                new EntityNotFoundException("User not found"));
        userRepository.delete(user);
    }

    private static void createUsers(UserRepository userRepository) {
        User user1 = User.builder()
                .email("samba@example.com")
                .password("password@123")
                .build();
        userRepository.save(user1);
        User user2 = User.builder()
                .email("bathie@example.com")
                .password("password@123")
                .build();
        userRepository.save(user2);
        User user3 = User.builder()
                .email("mass@example.com")
                .password("password@123")
                .build();
        userRepository.save(user3);

    }

    public static void updateUsers(UserRepository userRepository) {
        User user = userRepository.findById(1L).orElseThrow(()->
                new EntityNotFoundException("User not found"));
        user.setEmail("master@example.com");
        userRepository.save(user);
    }
}
