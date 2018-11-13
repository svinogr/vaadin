package com.example.demo.entity.jornal;

public enum EnumTypeRecord {
    AGREGAT("Агрегаты"), ACCUMULATOR("Аккумулторы"), TYRES("Шины"), SPARE_PART("Запасные части"), RASH_MATERIALS("Расходные материалы"),
    TO("ТО и ремонт"), OIL("Масла и смазка"), COMPLECTION("Комплектации"), DAMAGES("Повреждения и ДТП"), ETC("Прочее");
    String name;

    EnumTypeRecord(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
