package com.surya.BlackBoxDashboard_Backend.Repository;

import com.surya.BlackBoxDashboard_Backend.Model.data_bb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface data_bb_repository extends JpaRepository<data_bb,Integer> {
    List<data_bb> findByDate(LocalDate date);
}