package com.clinic.DentalClinicApplication.repositories;

import com.clinic.DentalClinicApplication.models.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
