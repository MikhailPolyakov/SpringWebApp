package com.example.Java_Diplom.models;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="Users")
public class Users {

@ManyToMany
@JoinTable(
        name="User_Role",
        joinColumns = @JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name="role_id")
)
private List<Role> roleList;


@Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

   @Column(name="username")
    private String username;

   @Column(name="password")
   private String password;


   @Column(name="fullname")
   private String fullname;

   @Column(name="email")
    private String email;

@Column(name="needtoupdate")
private  Boolean needToUpdate;

@Column(name="activaticode")
private String activaticode;

  //  @Transient
   // public static String exceptions ;

    public String getActivatiCode() {
        return activaticode;
    }

    public void setActivatiCode(String activaticode) {
        this.activaticode = activaticode;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public boolean isNeedToUpdate() {
        return needToUpdate;
    }

    public void setNeedToUpdate(boolean needToUpdate) {
        this.needToUpdate = needToUpdate;
    }


    //   @Transient
  //  public static boolean Update=false;

  //  public  void needToUpdate(){
 //       Update=true;
  //  }
   // public  void Updated(){
   //     Update=false;
  //  }


    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Users{" +
                "roleList=" + roleList +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id == users.id && Objects.equals(roleList, users.roleList) && Objects.equals(username, users.username) && Objects.equals(password, users.password) && Objects.equals(fullname, users.fullname) && Objects.equals(email, users.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleList, id, username, password, fullname, email);
    }
}
