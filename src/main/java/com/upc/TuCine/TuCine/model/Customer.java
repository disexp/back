package com.upc.TuCine.TuCine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "Person_id", nullable = false, foreignKey = @ForeignKey(name = "FK_CUSTOMER_PERSON"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Person person;
}
