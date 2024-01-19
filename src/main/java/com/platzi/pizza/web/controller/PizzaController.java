package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService){
        this.pizzaService = pizzaService;
    }

    @GetMapping("")
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int pages,
                                                    @RequestParam(defaultValue = "8") int elements){
        return ResponseEntity.ok(this.pizzaService.getAll(pages, elements));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> getPizza(@PathVariable("id") int idPizza){
        return ResponseEntity.ok(this.pizzaService.getPizza(idPizza));
    }

    @PostMapping()
    public ResponseEntity<PizzaEntity> savePizza(@RequestBody PizzaEntity pizzaEntity){
        if(pizzaEntity.getIdPizza() == null || !this.pizzaService.existsPizza(pizzaEntity.getIdPizza())){
            return ResponseEntity.ok(this.pizzaService.save(pizzaEntity));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping()
    public ResponseEntity<PizzaEntity> updatePizza(@RequestBody PizzaEntity pizzaEntity){
        if(pizzaEntity.getIdPizza() != null || !this.pizzaService.existsPizza(pizzaEntity.getIdPizza())){
            return ResponseEntity.ok(this.pizzaService.save(pizzaEntity));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePizza(@PathVariable("id") int idPizza){
        if(this.pizzaService.existsPizza(idPizza)){
            this.pizzaService.deletePizza(idPizza);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/available")
    public ResponseEntity<List<PizzaEntity>> getAvailable(){
        return ResponseEntity.ok(this.pizzaService.getAvailable());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable("name") String name){
        return ResponseEntity.ok(this.pizzaService.getByName(name));
    }

    @GetMapping("/with/{desc}")
    public ResponseEntity<List<PizzaEntity>> getByDescription(@PathVariable("desc") String description){
        return ResponseEntity.ok(this.pizzaService.getWith(description));
    }

    @GetMapping("/without/{desc}")
    public ResponseEntity<List<PizzaEntity>> getByNotDescription(@PathVariable("desc") String description){
        return ResponseEntity.ok(this.pizzaService.getWithout(description));
    }

    @GetMapping("/price/{menor}/{mayor}")
    public ResponseEntity<List<PizzaEntity>> getPriceBetween(@PathVariable("menor") Double menor,
                                                             @PathVariable("mayor") Double mayor){
        return ResponseEntity.ok(this.pizzaService.getPriceBetween(menor, mayor));
    }
}
