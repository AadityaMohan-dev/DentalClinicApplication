package com.clinic.DentalClinicApplication.repositories;

import com.clinic.DentalClinicApplication.models.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
