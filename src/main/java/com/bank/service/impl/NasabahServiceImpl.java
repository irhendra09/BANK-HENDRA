package com.bank.service.impl;

import com.bank.dto.request.SearchNasabahRequest;
import com.bank.entity.Nasabah;
import com.bank.repository.NasabahRepository;
import com.bank.service.NasabahService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NasabahServiceImpl implements NasabahService {

    private final NasabahRepository nasabahRepository;

    @Override
    public Nasabah add(Nasabah nasabah) {
        return nasabahRepository.save(nasabah);
    }

    @Override
    public Nasabah getById(String id) {
        Optional<Nasabah> optionalNasabah = nasabahRepository.findById(id);
        if (optionalNasabah.isPresent()) return optionalNasabah.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nasabah not found");
    }

    @Override
    public Nasabah update(Nasabah request) {
        Optional<Nasabah> optionalNasabah = nasabahRepository.findById(request.getId());
        if (optionalNasabah.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nasabah not found");
        return nasabahRepository.save(request);
    }

    @Override
    public void deleteById(String id) {
        Optional<Nasabah> optionalNasabah = nasabahRepository.findById(id);
        if (optionalNasabah.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nasabah not found");
        nasabahRepository.delete(optionalNasabah.get());
    }

    @Override
    public Page<Nasabah> getAll(SearchNasabahRequest request) {
        if (request.getPage() <= 0) request.setPage(1);
        Pageable pageable = PageRequest.of((request.getPage()-1), request.getSize());
        Specification<Nasabah> specification = getNasabahSpecification(request);
        return nasabahRepository.findAll(specification, pageable);
    }

    @Override
    public List<Nasabah> addBulk(List<Nasabah> nasabahs) {
        return nasabahRepository.saveAll(nasabahs);
    }

    private Specification<Nasabah> getNasabahSpecification(SearchNasabahRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getNik() != null) {
                Predicate nikPredicate = criteriaBuilder.like(root.get("nik"), "%" + request.getNik() + "%");
                predicates.add(nikPredicate);
            }
            if (request.getNama() != null) {
                Predicate namePredicate = criteriaBuilder.like(root.get("nama"), "%" + request.getNama() + "%");
                predicates.add(namePredicate);
            }
            return query.where(predicates.toArray(new Predicate[] {})).getRestriction();
        };
    }
}
