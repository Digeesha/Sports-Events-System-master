package com.example.matchescrud.Mapper;

import com.example.matchescrud.dto.request.EventRequestDTO;
import com.example.matchescrud.dto.request.MatchRequestDTO;
import com.example.matchescrud.model.entity.EventRequest;
import com.example.matchescrud.model.entity.Match;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EventRequestMapper {

    EventRequest eventRequestDTOtoevent(EventRequestDTO eventRequestDTO);
    EventRequestDTO eventToEventRequestDTO(EventRequest eventRequest);
    List<EventRequestDTO> eventListToeventRequestDTOList(List<EventRequest> eventRequest);
}
