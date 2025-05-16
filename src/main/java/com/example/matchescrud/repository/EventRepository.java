package com.example.matchescrud.repository;

import com.example.matchescrud.model.entity.EventRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<EventRequest, UUID> {
}
