package com.mrn.springbootjpa.models.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="clients")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column - we can omit when the columns we want to name is the same with the column in table
    @Column(name="first_name")
    @NotEmpty
    private String firstName;

    @NotEmpty
    @Column(name="last_name")
    private String lastName;

    @NotEmpty
    @Email
    private String email;

    @Column(name="image")
    private String image;

    @NotNull
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

   public String fullName() {
        return firstName + ' ' + lastName;
   }

    /*
    * Is used to specify callback methods for the corresponding lifecycle event.
    * This annotation may be applied to methods of an entity class, a mapped superclass, or a callback listener class.
    * */
//    @PrePersist
//    public void prePersist() {
//        createAt = new Date();
//    }
}
