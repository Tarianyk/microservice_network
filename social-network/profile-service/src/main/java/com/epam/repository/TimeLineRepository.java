package com.epam.repository;

import com.epam.domain.TimeLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeLineRepository extends JpaRepository<TimeLine, Long> {
}
