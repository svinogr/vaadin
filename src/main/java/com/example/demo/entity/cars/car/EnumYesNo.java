package com.example.demo.entity.cars.car;

public enum EnumYesNo {
    YES("Да") {
        @Override
        public boolean isYes() {
            return true;
        }
    }, NO("Нет") {
        @Override
        public boolean isYes() {
            return false;
        }
    };
    private String name;

    EnumYesNo(String name) {
        this.name = name;
    }

    public abstract boolean isYes();

    @Override
    public String toString() {
        return name;
    }
}
