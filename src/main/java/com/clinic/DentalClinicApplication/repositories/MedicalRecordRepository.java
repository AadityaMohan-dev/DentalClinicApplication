package com.clinic.DentalClinicApplication.repositories;

import com.clinic.DentalClinicApplication.models.entities.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
}
