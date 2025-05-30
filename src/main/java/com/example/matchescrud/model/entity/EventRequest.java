package com.example.matchescrud.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//Table name 'match' is reserved and cannot be used.
@Table(name = "eventRequest")
public class EventRequest {
    @Id
    private UUID uuid;
    private String requesterName;
    private LocalDate date;
    private LocalTime time;
    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    @JsonIgnore
    private Team homeTeam;
    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    @JsonIgnore
    private Team awayTeam;
    private int spectators;
    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

}
