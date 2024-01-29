package com.dev.backend.devstore.controller;

import com.dev.backend.devstore.domain.pix.PixChargeRequestDTO;
import com.dev.backend.devstore.service.PixService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/pix")
@SecurityRequirement(name = "bearer-key")
public class PixController {

    @Autowired
    private PixService pixService;

    @GetMapping("/key")
    public ResponseEntity listEvpKeys(){
        JSONObject response = this.pixService.listEvpKeys();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response.toString());
    }

    @GetMapping
    public ResponseEntity pixCreateEVP(){
        JSONObject response = this.pixService.pixCreateEVP();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response.toString());
    }

    @GetMapping("/charges")
    public ResponseEntity listCharges(){
        Map<String, Object> response =  this.pixService.pixListCharges();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/charges/{txid}")
    public ResponseEntity detailCharge(@PathVariable String txid){
        Map<String, Object> response = this.pixService.pixChargeDetail(txid);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping
    public ResponseEntity pixCreateCharge(@RequestBody PixChargeRequestDTO pixChargeRequest){
        JSONObject response = this.pixService.pixCreateCharge(pixChargeRequest);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response.toString());
    }

    @DeleteMapping("/{chave}")
    public ResponseEntity deleteKeyEVP(@PathVariable String chave){
        this.pixService.deleteKeyEVP(chave);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
}
