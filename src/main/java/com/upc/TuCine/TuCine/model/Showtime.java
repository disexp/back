package com.upc.TuCine.TuCine.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Showtime")
public class Showtime {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    @Column(name = "time", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime time;

    @Column(name = "price")
    private Float price;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false, foreignKey = @ForeignKey(name = "FK_SHOWTIME_FILM"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Film film;

    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false, foreignKey = @ForeignKey(name = "FK_SHOWTIME_BUSINESS"))
    private Business business;

    @ManyToOne
    @JoinColumn(name = "promotion_id", foreignKey = @ForeignKey(name = "FK_SHOWTIME_PROMOTION"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Promotion promotion;
}
