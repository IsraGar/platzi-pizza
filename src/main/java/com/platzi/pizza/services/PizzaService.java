package com.platzi.pizza.services;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository){
        this.pizzaRepository = pizzaRepository;
    }

    public List<PizzaEntity> getAll(){
        //return this.jdbcTemplate.query("SELECT * FROM pizza where available = 0", new BeanPropertyRowMapper<>(PizzaEntity.class));
        return  this.pizzaRepository.findAll();
    }

    public PizzaEntity getPizza(int idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizzaEntity){
        return this.pizzaRepository.save(pizzaEntity);
    }

    public boolean existsPizza(int idPizza){
        return this.pizzaRepository.existsById(idPizza);
    }

    public void deletePizza(int idPizza){
        this.pizzaRepository.deleteById(idPizza);
    }
}
