package com.telus.serviceactivation.activation.repository;

import com.telus.serviceactivation.activation.entity.RelatedParty;
import com.telus.serviceactivation.activation.entity.ServiceActivation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ServiceActivationRepository extends JpaRepository<ServiceActivation, Long> {
    Optional<ServiceActivation> findByCategoryAndDescriptionAndServiceTypeAndState(
            String category, String description, String serviceType, String state);

    /*@Query("SELECT sa FROM ServiceActivation sa JOIN sa.serviceCharacteristics sc WHERE sc.name = :name AND sc.valueContent = :value")
    List<ServiceActivation> findByServiceCharacteristicsNameAndValue(@Param("name") String name, @Param("value") String value);*/
    //Optional<ServiceActivation> findByPhoneNumberAndActivityCd(String phoneNumber, String activityCd);

    //Custom query method to find ServiceActivation by related parties and activity code
    //Optional<ServiceActivation> findByRelatedPartiesAndActivityCd(List<RelatedParty> relatedParties, String activityCd);

    /*@Query("SELECT sa FROM ServiceActivation sa LEFT JOIN FETCH sa.relatedParties LEFT JOIN FETCH sa.serviceCharacteristics WHERE sa.id = :id")
    Optional<ServiceActivation> findByIdWithRelations(@Param("id") Long id);
*/

}

