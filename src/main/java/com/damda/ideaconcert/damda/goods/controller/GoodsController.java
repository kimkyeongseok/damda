package com.damda.ideaconcert.damda.goods.controller;

import com.damda.ideaconcert.damda.goods.dto.GoodsInsertReq;
import com.damda.ideaconcert.damda.goods.dto.GoodsRes;
import com.damda.ideaconcert.damda.goods.dto.GoodsUpdateReq;
import com.damda.ideaconcert.damda.goods.service.GoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class GoodsController {
    private final GoodsService goodsService;

    @GetMapping("/goods")
    public ResponseEntity<List<GoodsRes>> subscribeGoodsList(){

        List<GoodsRes> list = goodsService.subscribeGoodsList();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("/goods")
    public ResponseEntity<?> subscribeGoodsInsert(@RequestBody GoodsInsertReq req){
        goodsService.subscribeGoodsInsert(req);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("/goods/{goodsNo}")
    public ResponseEntity<?> subscribeGoodsUpdate(@PathVariable Integer goodsNo, GoodsUpdateReq req){
        goodsService.subscribeGoodsUpdate(goodsNo,req);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/goods/{goodsNo}")
    public ResponseEntity<?> subscribeGoodsDelete(@PathVariable Integer goodsNo){
        goodsService.subscribeGoodsDelete(goodsNo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
