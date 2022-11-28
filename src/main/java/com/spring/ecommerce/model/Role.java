package com.spring.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(name = "role_seq",
            sequenceName = "role_seq",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "role_id")
    public Long id;

    @Column(name = "role_type")
    private RoleType roleType;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    private List<User> users;

    public Role() {
    }

    public Role(RoleType roleType, List<User> users) {
        this.roleType = roleType;
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleType=" + roleType +
                ", users=" + users +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public List<User> getUsers() {
        if (users == null) {
            users = new ArrayList<>();
        }
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
