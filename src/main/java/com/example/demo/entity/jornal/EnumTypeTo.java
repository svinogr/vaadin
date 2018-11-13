package com.example.demo.entity.jornal;

public enum EnumTypeTo {
    TO0("ТО 0"), TO1("ТО 1");
    String name;

    EnumTypeTo(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
