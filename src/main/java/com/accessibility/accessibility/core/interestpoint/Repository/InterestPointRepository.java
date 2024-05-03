package com.accessibility.accessibility.core.interestpoint.Repository;

import com.accessibility.accessibility.core.interestpoint.domain.entities.InterestPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestPointRepository extends JpaRepository<InterestPoint, Long> {

    InterestPoint findByName(String name);
}
