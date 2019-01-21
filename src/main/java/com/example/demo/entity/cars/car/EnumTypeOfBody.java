package com.example.demo.entity.cars.car;

public enum EnumTypeOfBody {
    SEDAN("Седан"),
    UNIBERSAL("Универсал"),
    HATCHBACK("Хетчбек"), MICROBUS("Микроавтобус"),
    LIFTBACK("Лифтбэк"), PICKUP("Пикап"), VAN("Вэн");

    String name;

    EnumTypeOfBody(String name) {
        this.name = name;
    }

    public static EnumTypeOfBody lookByName(String name) {
        EnumTypeOfBody enumTypeOfBody;

        switch (name.toLowerCase()) {
            case "cедан":
                enumTypeOfBody = EnumTypeOfBody.SEDAN;
                break;
            case "универсал":
                enumTypeOfBody = EnumTypeOfBody.UNIBERSAL;
                break;
            case "хетчбек":
                enumTypeOfBody = EnumTypeOfBody.HATCHBACK;
                break;
            case "микроавтобус":
                enumTypeOfBody = EnumTypeOfBody.MICROBUS;
                break;
            case "лифтбэк":
                enumTypeOfBody = EnumTypeOfBody.LIFTBACK;
                break;
            case "пикап":
                enumTypeOfBody = EnumTypeOfBody.PICKUP;
                break;
            case "вэн":
                enumTypeOfBody = EnumTypeOfBody.VAN;
                break;
            default:
                enumTypeOfBody = null;

        }
        return enumTypeOfBody;
    }

    @Override
    public String toString() {
        return name;
    }
}
