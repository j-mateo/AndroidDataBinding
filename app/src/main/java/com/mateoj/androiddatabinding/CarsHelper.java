package com.mateoj.androiddatabinding;

import com.mateoj.androiddatabinding.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by jose on 9/3/15.
 */
public class CarsHelper {
    public static List<Car> geListOfCars()
    {
        String[] makes = new String[]{
                "Honda",
                "Nissan",
                "Toyota",
                "Jeep",
                "Ford",
                "Chevy"
        };
        String[] models = new String[]{
                "Accord",
                "Altima",
                "Corolla",
                "Cherokee",
                "Escape",
                "Camaro"
        };
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            Random random = new Random();
            int ran = random.nextInt(6);
            cars.add(new Car(String.valueOf(2000 + i), makes[ran], models[ran] ,
                    String.valueOf(5000 + i * 50), String.valueOf(1000 + i *250)
                    ,"Some description", "file:///android_asset/car1.jpg" ));
        }
        return cars;
    }
}
