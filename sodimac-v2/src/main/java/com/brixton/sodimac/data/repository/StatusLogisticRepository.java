package com.brixton.sodimac.data.repository;

import com.brixton.sodimac.data.entity.logistic.StatusLogistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StatusLogisticRepository extends JpaRepository<StatusLogistic, Integer> {

    @Query(value = "SELECT E FROM StatusLogistic E WHERE E.description = :description and E.groupStatus = :groupStatus")
    Optional<StatusLogistic> findByGroupStatus(@Param("description")String description, @Param("groupStatus")String groupStatus);


}
