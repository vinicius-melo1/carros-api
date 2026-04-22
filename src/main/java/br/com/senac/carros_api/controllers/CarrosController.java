package br.com.senac.carros_api.controllers;


import br.com.senac.carros_api.dtos.CarrosRequestDto;
import br.com.senac.carros_api.entidades.Carros;
import br.com.senac.carros_api.services.CarrosService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/carros")
public class CarrosController {
    private CarrosService carrosService;

    public CarrosController(CarrosService carrosService) {
        this.carrosService = carrosService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Carros>> listar() {
        return ResponseEntity
                .ok(carrosService.listar());
    }

    @PostMapping("/criar")
    public ResponseEntity<Carros> criar(@RequestBody CarrosRequestDto carro) {
        try {
            return ResponseEntity.ok(carrosService.criar(carro));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Carros> atualizar(
            @RequestBody CarrosRequestDto carro,
            @PathVariable Long id) {

        try {
            return ResponseEntity.ok(carrosService.atualizar(id,carro));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }

    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id) {
        try{
            carrosService.deletar(id);
            return ResponseEntity.ok(null);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(null);
        } catch(Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
