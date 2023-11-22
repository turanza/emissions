package com.triply.emissions.service;

import com.triply.emissions.command.company.CreateCompanyCommand;
import com.triply.emissions.model.Company;
import com.triply.emissions.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;


    @Transactional
    public void createNewCompany(CreateCompanyCommand command) {
        companyRepository.save(new Company(command.getName()));
        companyRepository.setCurrentCompany(companyRepository.getLastOne());
    }
}
