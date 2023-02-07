package co.develhope.crud.controllers;

import co.develhope.crud.entities.Car;
import co.develhope.crud.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cars")
public class CarControllers {

    @Autowired
    private CarRepository carRepository;

    @PostMapping("")
    public Car create(@RequestBody Car car){
        Car carSaved = carRepository.saveAndFlush(car);
        return carSaved;
    }

    @GetMapping("")
    public List<Car> getAllCar(){
        return carRepository.findAll();
    }


    @GetMapping("/{id}")
    public Car getSingleCar(@PathVariable long id){
        Car car;
        if(carRepository.existsById(id)){
           car = carRepository.getById(id);
        }else {
            car = new Car();
        }return car;
    }


    @PutMapping("/{id}")
    public Car updateCar(@PathVariable long id, @RequestParam String type){
        Car car;
        if (carRepository.existsById(id)){
            car = carRepository.getById(id);
            car.setType(type);
            car = carRepository.saveAndFlush(car);
        }else{
            car = new Car();
        }
        return car;
    }

    @DeleteMapping("/{id}")
    public void deleteSingleCar(@PathVariable long id){
        if(carRepository.existsById(id)){
            carRepository.deleteById(id);
        }else {
            ResponseEntity.status(HttpStatus.CONFLICT).body("Auto non trovata");
        }
    }

    @DeleteMapping("")
    public void deleteAll(){
        carRepository.deleteAll();
    }
}
