package com.damda.ideaconcert.damda.admin.product.representation;

import java.util.List;

import com.damda.ideaconcert.damda.admin.product.application.AdminProductService;
import com.damda.ideaconcert.damda.product.application.ProductReadDto;
import com.damda.ideaconcert.damda.product.application.ProductRequestDto;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/management/product")
public class AdminProductController {
    private final AdminProductService adminProductService;
    @GetMapping("")
    public List<ProductReadDto> read(Pageable page) {
        return adminProductService.read(page);
    }

    @PostMapping("")
    public ResponseEntity<String> create(ProductRequestDto productRequest) {
        adminProductService.create(productRequest);
        return new ResponseEntity<>("상품 등록 완료", HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<String> update(ProductRequestDto productRequest) {
        adminProductService.update(productRequest);
        return new ResponseEntity<>("수정 되었습니다.", HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        adminProductService.deleteById(id);
        return new ResponseEntity<>("삭제 되었습니다." , HttpStatus.OK);
    }
}
