package sn.elearning.java.java_elearning_admin.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false, updatable = false)
    private Long userId;

    @Basic
    @Column(name = "first_name", nullable = false, length = 45, unique = true)
    @Email(message = "Email should be valid")
    private String email;

    @Basic
    @Column(name = "password", nullable = false, length = 64)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Password should be at least 8 characters long and contain at least one uppercase letter, one lowercase letter and one number")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns =  @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne(mappedBy = "user")
    private Student student;

    @OneToOne(mappedBy = "user")
    private Instructor instructor;

    public void assignRoleToUser(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    public void deleteRoleFromUser(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }
}
