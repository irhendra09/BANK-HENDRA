package com.bank.service;

import com.bank.dto.request.SearchNasabahRequest;
import com.bank.entity.Nasabah;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NasabahService {
    Nasabah add(Nasabah request);
    Nasabah getById(String id);
    Nasabah update(Nasabah request);
    void deleteById(String id);
    Page<Nasabah> getAll(SearchNasabahRequest request);
    List<Nasabah> addBulk(List<Nasabah> nasabahs);
}
