package service;

import domain.Car;
import repository.CarRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CarService {

    CarRepository carRepository = new CarRepository();

    public Map<String, Car> getExpiredInsurancedCarsSortedByLicencePlate() throws SQLException {
        List<Car> cars = carRepository.getCarsByExpirationDate(LocalDate.now());
        return mapCarsByLicencePlate(cars);
    }

    private TreeMap<String, Car> mapCarsByLicencePlate(List<Car> cars) {
        TreeMap<String, Car> map = new TreeMap<>();
        for (Car car:cars) {
            map.put(car.getPlate(), car);
        }
        return map;
    }
}
