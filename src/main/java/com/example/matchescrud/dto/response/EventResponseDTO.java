package com.example.matchescrud.dto.response;

import com.example.matchescrud.dto.StadiumDTO;
import com.example.matchescrud.model.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class EventResponseDTO {
    private UUID uuid;
    private String requesterName;//Set using UUID.Random() when creating a match
    private StadiumDTO stadium; //Set using HomeTeam stadium
    private LocalDate date;
    private LocalTime time;
    private Team homeTeam;
    private Team awayTeam;
  //Calculated by spectators number

}