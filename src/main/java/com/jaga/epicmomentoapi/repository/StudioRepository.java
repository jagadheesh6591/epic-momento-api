package com.jaga.epicmomentoapi.repository;

import com.jaga.epicmomentoapi.bean.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {
}
