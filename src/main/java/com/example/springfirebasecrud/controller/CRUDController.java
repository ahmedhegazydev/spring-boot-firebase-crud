package com.example.springfirebasecrud.controller;

import com.example.springfirebasecrud.model.CRUD;
import com.example.springfirebasecrud.service.CRUDService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class CRUDController {

    private final CRUDService crudService;

    public CRUDController(CRUDService crudService) {
        this.crudService = crudService;
    }

    @PostMapping("/create")
    public String createCRUD(@RequestBody CRUD crud) throws ExecutionException, InterruptedException {
        return crudService.createCrud(crud);
    }

    @GetMapping("/get")
    public CRUD getCrud(@RequestParam String documentId) throws ExecutionException, InterruptedException {
        return crudService.getCrud(documentId);
    }

    @PutMapping("/update")
    public String updateCrud(@RequestBody CRUD crud){
        return crudService.updateCrud();
    }

    @DeleteMapping("/delete")
    public String deleteCrud(@RequestParam String documnetId){
        return crudService.deleteCrud(documnetId);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testEndPoint(){
        return ResponseEntity.ok("Test result, is ok, Go ahead");
    }

}
