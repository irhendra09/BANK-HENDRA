package com.bank.controller;

import com.bank.dto.request.SearchNasabahRequest;
import com.bank.dto.response.PagingResponse;
import com.bank.dto.response.WebResponse;
import com.bank.entity.Nasabah;
import com.bank.service.NasabahService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/nasabah")
@RequiredArgsConstructor
public class NasabahController {
    private final NasabahService nasabahService;

    @PostMapping
    public ResponseEntity<?> createNew(@RequestBody Nasabah nasabah) {
        Nasabah newNasabah = nasabahService.add(nasabah);
        WebResponse<Nasabah> response = WebResponse.<Nasabah>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("successfully created new nasabah")
                .data(newNasabah)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/bulk")
    public ResponseEntity<?> createNewBulk(@RequestBody List<Nasabah> nasabah) {
        List<Nasabah> newNasabah = nasabahService.addBulk(nasabah);
        WebResponse<List<Nasabah>> response = WebResponse.<List<Nasabah>>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("successfully created new nasabah")
                .data(newNasabah)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNasabah(@PathVariable("id") String id) {
        Nasabah nasabah= nasabahService.getById(id);
        WebResponse<Nasabah> response = WebResponse.<Nasabah>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully get nasabah")
                .data(nasabah)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllNasabah( @RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "10") Integer size,
                                            @RequestParam(required = false) String nik,
                                            @RequestParam(required = false) String nama) {

        SearchNasabahRequest request = SearchNasabahRequest.builder()
                .page(page)
                .size(size)
                .nik(nik)
                .nama(nama)
                .build();

        Page<Nasabah> nasabahPage = nasabahService.getAll(request);

        PagingResponse pagingResponse = PagingResponse.builder()
                .page(request.getPage())
                .size(request.getSize())
                .totalPage(nasabahPage.getTotalPages())
                .totalElements(nasabahPage.getTotalElements())
                .build();

        WebResponse<List<Nasabah>> response = WebResponse.<List<Nasabah>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully get nasabah")
                .data(nasabahPage.getContent())
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<?> updateNasabah(@RequestBody Nasabah nasabah) {
        Nasabah updated = nasabahService.update(nasabah);
        WebResponse<Nasabah> response = WebResponse.<Nasabah>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully updated nasabah")
                .data(updated)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteNasabah(@PathVariable("id") String id) {
        nasabahService.deleteById(id);
        WebResponse<String> response= WebResponse.<String>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully deleted nasabah")
                .data("OK")
                .build();
        return ResponseEntity.ok(response);
    }

}
