package com.example.demo.entity.cars.car;

public enum EnumTypeOfBody {
    SEDAN("Седан"), UNIBERSAL("Универсал"), HATCHBACK("Хетчбек"), MICROBUS("Микроавтобус"), LIFTBACK("Лифтбэк"), PICKUP("Пикап"), VAN("Вэн");

    String name;

    EnumTypeOfBody(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
