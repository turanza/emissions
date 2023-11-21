CREATE TABLE companyEmployeeMatch(
                                     id bigint AUTO_INCREMENT PRIMARY KEY,
                                     companyId bigint not null,
                                     employeeId bigint not null
);
CREATE TABLE companyUserMatch(
                                 id bigint AUTO_INCREMENT PRIMARY KEY,
                                 userId bigint not null,
                                 companyId bigint not null
);
CREATE TABLE companyVehicleMatch(
                                    id bigint AUTO_INCREMENT PRIMARY KEY,
                                    companyId bigint not null,
                                    vehicleId bigint not null
);
CREATE TABLE currentCompany(
    companyId bigint not null
);
insert into emission.users (created, status, updated, login, name, password, role, surname)
values ((SELECT NOW()),'ACTIVE',(SELECT NOW()),'test','test',
        '$2a$12$qRCQbp4rjYbfxCicVMc9EeynUr91Mu0PXR7uq3bGEaQlKnkZApXay','ADMIN','test');