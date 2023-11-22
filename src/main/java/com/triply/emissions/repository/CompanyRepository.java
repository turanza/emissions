package com.triply.emissions.repository;

import com.triply.emissions.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Modifying
    @Query(value = """
    INSERT INTO currentCompany (companyId) value (:id)
    """,nativeQuery = true)
    void setCurrentCompany(Long id);

    @Query(value = """
    SELECT id FROM company ORDER BY id DESC LIMIT 1
    """,nativeQuery = true)
    Long getLastOne();
}
