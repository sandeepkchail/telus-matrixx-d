package com.telus.serviceactivation.activation.repository;

import com.telus.serviceactivation.activation.entity.RelatedParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RelatedPartyRepository extends JpaRepository<RelatedParty, Long> {

    //Optional<RelatedParty> findById(Long id);


    // Custom query to find RelatedParty by non-primary key column 'id'
    @Query("SELECT rp FROM RelatedParty rp WHERE rp.id = :id")
    Optional<RelatedParty> findById(@Param("id") Long id);

    // Custom query using JPQL
  /*  @Query("SELECT rp FROM RelatedParty rp WHERE rp.id = :id")
    Optional<RelatedParty> findById(@Param("id") Long id);*/

}