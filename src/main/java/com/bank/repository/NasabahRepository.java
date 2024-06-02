package com.bank.repository;

import com.bank.entity.Nasabah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface NasabahRepository extends JpaRepository<Nasabah, String>, JpaSpecificationExecutor<Nasabah> {
    boolean existsByNik(String nik);
}
