package com.example.demo.entity.jornal;

public enum EnumTypeOil {
    MOTOR_OIL("Моторные масла"), TR_GIDR_OIL("Тр. Гидр. Масла"),
    SPECIAL_OIL("Специальные масла"), PLAST_OIL("Пласт Масла"), ETC("Прочее");
    String name;

    EnumTypeOil(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
