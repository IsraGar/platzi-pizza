package com.platzi.pizza.services;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.persistence.repository.PizzaPagSortRepository;
import com.platzi.pizza.persistence.repository.PizzaRepository;
import com.platzi.pizza.services.dto.UpdatePizzaPriceDto;
import com.platzi.pizza.services.exception.EmailAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;
    @Autowired
    public PizzaService(PizzaRepository pizzaRepository,
                        PizzaPagSortRepository pizzaPagSortRepository){
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    public Page<PizzaEntity> getAll(int pages, int elements) {
        //return this.jdbcTemplate.query("SELECT * FROM pizza where available = 0", new BeanPropertyRowMapper<>(PizzaEntity.class));
        Pageable pageRequest = PageRequest.of(pages, elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
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

    public Page<PizzaEntity> getAvailable(int pages, int elements, String sortBy, String sortDirection){
        System.out.println("********** "+this.pizzaRepository.countByVeganTrue());
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(pages, elements, sort);
        return this.pizzaPagSortRepository.findAllByAvailableTrue(pageRequest);
    }

    public PizzaEntity getByName(String name){
        return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
    }

    public List<PizzaEntity> getWith(String description){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(description);
    }

    public List<PizzaEntity> getWithout(String description){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(description);
    }

    public List<PizzaEntity> getPriceBetween(Double menor, Double mayor){
        return this.pizzaRepository.findAllByAvailableTrueAndPriceBetween(menor, mayor);
    }

    @Transactional(noRollbackFor = EmailAPIException.class, propagation = Propagation.REQUIRED)
    public void updatePrice(UpdatePizzaPriceDto dto){
        this.pizzaRepository.updatePrice(dto);
        this.sendEmail();
    }

    private void sendEmail(){
    throw new EmailAPIException();
    }
}
