package com.triply.emissions.repository;

import com.triply.emissions.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Modifying
    @Query(value = """
        INSERT INTO companyEmployeeMatch (employeeId, companyId) VALUES((SELECT LAST_INSERT_ID()),(SELECT companyId FROM currentCompany))
    """,nativeQuery = true)
    void setCompanyEmployeeMatch(Long id);

}
