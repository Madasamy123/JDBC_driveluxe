package com.Madasamy.driveluxe.service;

import com.Madasamy.driveluxe.exception.DriveluxeException;
import com.Madasamy.driveluxe.model.Car;
import com.Madasamy.driveluxe.repository.DriveluxeRepository;

import org.springframework.stereotype.Service;


import java.util.List;

@Service

public class DriveluxeService {
    private final DriveluxeRepository driveluxeRepository;

    // constructor

    public DriveluxeService(DriveluxeRepository driveluxeRepository) {
        this.driveluxeRepository = driveluxeRepository;
    }


    // add car data

    public Car addCar(Car car) {

        driveluxeRepository.findByModel(car.getModel()).ifPresent(c -> {
            throw new DriveluxeException("Car already exists: " + car.getModel());
        });

        return driveluxeRepository.save(car);    //  car object save in database


    }

   //  find all cars

    public List<Car> getAllCars() {
        return driveluxeRepository.findAll();
    }



    // get car by Id


    public Car getCarsById(int id) {
        return driveluxeRepository.findById(id)
                .orElseThrow(() -> new DriveluxeException("Car with ID " + id + " not found"));
    }


    // delete car


    public void deleteCar(int id) {

        if(!driveluxeRepository.existsById(id)){
            throw new DriveluxeException("Car Id is not found");
        }
        driveluxeRepository.deleteById(id);
    }


    //  update car

    public Car updateCar(int id, Car carDetails) {



        return driveluxeRepository.findById(id).map(existingCar -> {
            existingCar.setModel(carDetails.getModel());
            existingCar.setFuelType(carDetails.getFuelType());
            existingCar.setBrand(carDetails.getBrand());
            existingCar.setPrice(carDetails.getPrice());
            existingCar.setYear(carDetails.getYear());
            return driveluxeRepository.save(existingCar);
        }).orElseThrow(() -> new DriveluxeException("Car not found with ID: " + id));
    }


// search cars custom query


    public List<Car> searchCars(String keyword) {

        return driveluxeRepository.searchByKeyword(keyword);
    }


    //
//    // New method to get all cars ordered by creation date (latest first)
//    public List<Car> getAllCarsLatestFirst() {
//        return driveluxeRepository.findAllCarsLatestFirst();
//    }


}
