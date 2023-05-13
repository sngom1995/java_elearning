package sn.elearning.java.java_elearning_admin.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id", nullable = false, updatable = false)
    private Long roleId;
    @Column(name = "role_name", nullable = false, length = 45)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users =new HashSet<>();

    public Role(String roleName){
        this.roleName=roleName;
    }
}
