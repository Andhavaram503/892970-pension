package com.cognizant.pension.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.pension.model.PensionerInput;

@Repository
public interface ProcessPensionRepository extends JpaRepository<PensionerInput, Integer> {

}
