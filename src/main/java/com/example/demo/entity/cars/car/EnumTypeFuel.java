package com.example.demo.entity.cars.car;

public enum EnumTypeFuel {
    DISDEL("Дизель"), PETROL("Бензин"), GAS("Газ");
    String name;

    EnumTypeFuel(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
