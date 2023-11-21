package com.triply.emissions.repository;

import com.triply.emissions.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    @Modifying
    @Query(value = """
        INSERT INTO companyVehicleMatch (vehicleId, companyId) VALUES((SELECT LAST_INSERT_ID()),(SELECT companyId FROM currentCompany))
    """,nativeQuery = true)
    void setCompanyVehicleMatch(Long id);

}
