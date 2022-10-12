package com.example.Java_Diplom.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="Privilege")
public class Privilege {



    @ManyToMany(mappedBy = "privileges")
    private List<Role> roleList;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

@Column(name="privilege_name")
    private String privilege_name;

    public Privilege(int id, String privilege_name) {
        this.id = id;
        this.privilege_name = privilege_name;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Privilege(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrivilege_name() {
        return privilege_name;
    }

    public void setPrivilege_name(String privilege_name) {
        this.privilege_name = privilege_name;
    }

    public Privilege() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Privilege privilege = (Privilege) o;
        return id == privilege.id && Objects.equals(privilege_name, privilege.privilege_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, privilege_name);
    }
}
