package com.example.demo.entity.cars.car;

public enum EnumTypeFuel {
    DISEL("Дизель"), PETROL("Бензин"), GAS("Газ");
    String name;

    EnumTypeFuel(String name) {
        this.name = name;
    }

    public static EnumTypeFuel lookByName(String name) {
        EnumTypeFuel enumTypeFuel;

        switch (name.toLowerCase()) {
            case "газ":
                enumTypeFuel = EnumTypeFuel.GAS;
                break;
            case "бензин":
                enumTypeFuel = EnumTypeFuel.PETROL;
                break;
            case "дизель":
                enumTypeFuel = EnumTypeFuel.DISEL;
                break;
            default:
                enumTypeFuel = null;

        }
        return enumTypeFuel;
    }

    @Override
    public String toString() {
        return name;
    }
}
