package com.example.matchescrud.Mapper;

import com.example.matchescrud.dto.response.EventResponseDTO;
import com.example.matchescrud.model.entity.EventRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EventResponseDTOMapper {
    EventResponseDTO eventToEventResponseDTO(EventRequest eventRequest);
    EventRequest eventResponseDTOToEvent(EventResponseDTO eventResponseDTO);
    List<EventResponseDTO> eventListToEventResponseDTOList(List<EventRequest> eventRequest);
}
