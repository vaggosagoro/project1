package repository;

import databaseHelper.MySQLConnectionFactory;
import domain.Car;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class CarRepository {

    public List<Car> getCarsByExpirationDate(LocalDate expirationDate) throws SQLException {

        Date sqldate = Date.valueOf(expirationDate);

        Statement statement =  MySQLConnectionFactory.getConnection().createStatement();
        String sql= "SELECT * FROM VEHICLESINSURANCECHECK.cars  WHERE ExpirationDate < '"+sqldate+"' or ExpirationDate IS NULL";
        ResultSet resultSet = statement.executeQuery(sql);


        List<Car> cars = new LinkedList<>();

        while (resultSet.next()) {
            Car car = mapDBRowToCar(resultSet);
            cars.add(car);
        }

        return cars;
    }


    private Car mapDBRowToCar(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setYear(rs.getInt(1));
        car.setManufacturer(rs.getString(2));
        car.setModel(rs.getString(3));
        car.setRegistrationDate(LocalDate.parse(rs.getDate(4).toString(), DateTimeFormatter.ISO_LOCAL_DATE));
        car.setExpirationDate(LocalDate.parse(rs.getDate(5).toString(), DateTimeFormatter.ISO_LOCAL_DATE));
        car.setInsuranceCompany(rs.getString(6));
        car.setColor(rs.getString(7));
        car.setPlate(rs.getString(8));
        car.setOwnerId(rs.getInt(9));
        return car;
    }


}
