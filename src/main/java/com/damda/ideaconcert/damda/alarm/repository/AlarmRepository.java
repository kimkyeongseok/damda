package com.damda.ideaconcert.damda.alarm.repository;

import com.damda.ideaconcert.damda.alarm.domain.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm,Integer>, AlarmRepositoryCustom {
}
