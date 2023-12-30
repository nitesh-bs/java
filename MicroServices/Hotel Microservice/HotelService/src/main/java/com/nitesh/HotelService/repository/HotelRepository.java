package com.nitesh.HotelService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitesh.HotelService.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
