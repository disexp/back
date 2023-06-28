package com.upc.TuCine.TuCine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Person")
public class Person {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;
    @Column(name="first_name", length = 80, nullable = false)
    private String firstName;
    @Column(name="last_name", length = 80, nullable = false)
    private String lastName;
    @Column(name="birthdate", nullable = false)
    private LocalDate birthdate;
    @Column(name="phone", length = 9, nullable = false)
    private String phone;
    @Column(name="photo", length = 5000, nullable = true)
    private String photo;
    @Column(name="email", length = 80, nullable = false)
    private String email;
    @Column(name="password", length = 15, nullable = false)
    private String password;
    @Column(name="number_dni", length = 8, nullable = false)
    private String numberDni;

    @ManyToOne
    @JoinColumn(name = "Gender_id",nullable = false, foreignKey = @ForeignKey(name = "FK_GENDER_ID"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "TypeUser_id",nullable = false, foreignKey = @ForeignKey(name = "FK_TYPEUSER_ID"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private TypeUser typeUser;

}
