package com.example.demo.entity.cars.car;

public enum EnumTypeFuel {
    DISEL("Дизель"), PETROL("Бензин"), GAS("Газ");
    String name;

    EnumTypeFuel(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
