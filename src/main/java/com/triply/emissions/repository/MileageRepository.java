package com.triply.emissions.repository;

import com.triply.emissions.model.Mileage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MileageRepository extends JpaRepository<Mileage,Long> {
    @Query(value = """
        SELECT mileage FROM mileage WHERE employee_ID =:id ORDER BY year,week DESC LIMIT 1
    """,nativeQuery = true)
    double getLastMileageForEmployee(Long id);

    @Query(value = """
        SELECT SUM(mileage) FROM mileage WHERE year = :year AND week = :weekOfYear AND vehicle_Id IN (
            SELECT vehicleID FROM companyVehicleMatch where companyId = (
                SELECT companyId FROM currentCompany))
                                           AND employee_Id IN(
                SELECT employeeID FROM companyEmployeeMatch where companyId = (
                    SELECT companyId FROM currentCompany))
    """,nativeQuery = true)
    Double getLastMileageForCompany(int year, int weekOfYear);

    @Query (value = """
        SELECT vehicle_id FROM mileage WHERE employee_ID =:id ORDER BY year,week DESC LIMIT 1
    """,nativeQuery = true)
    Long getVehicleId(Long id);

    @Query(value = """
    SELECT employeeId FROM companyEmployeeMatch WHERE companyId = (SELECT companyId FROM currentCompany)
    """,nativeQuery = true)
    List<Long> getEmployeeIdsForCurCompany();
}
