package com.upc.TuCine.TuCine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Business")
public class Business {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    @Column(name = "social_reason", length = 200, nullable = false)
    private String socialReason;
    @Column(name = "ruc", length = 11, nullable = false)
    private String ruc;
    @Column(name = "phone", length = 9, nullable = false)
    private String phone;
    @Column(name = "email", length = 100, nullable = false)
    private String email;
    @Column(name = "image_logo", length = 5000, nullable = false)
    private String imageLogo;
    @Column(name = "image_banner", length = 5000, nullable = false)
    private String imageBanner;
    @Column(name = "description", length = 2000, nullable = false)
    private String description;
    @Column(name = "date_attention", length = 800, nullable = false)
    private String dateAttention;
    @Column(name = "address", length = 200, nullable = false)
    private String address;
    @Column(name = "reference_address", length = 200, nullable = false)
    private String referenceAddress;

    @ManyToOne
    @JoinColumn(name = "Owner_id",nullable = false, foreignKey = @ForeignKey(name = "FK_OWNER_ID"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "BusinessType_id",nullable = false, foreignKey = @ForeignKey(name = "FK_DISTRICT_ID"))

    private BusinessType businessType;
}
