package com.example.demo.entity.cars.personal;

import com.example.demo.entity.IEnumColumnNames;

public enum  EnumTypePerson implements IEnumColumnNames {
    DRIVER("Водитель"){
        @Override
        public String getDisplayName() {
            return name;
        }

        @Override
        public String getColumnSearchName() {
            return null;
        }
    }, TRACTORIST("Тракторист"){
        @Override
        public String getDisplayName() {
            return "Тракторист";
        }

        @Override
        public String getColumnSearchName() {
            return null;
        }
    }, MECHANIC("Механик"){
        @Override
        public String getDisplayName() {
            return "Механик";
        }

        @Override
        public String getColumnSearchName() {
            return null;
        }
    }, DISPETCHER("Диспетчер"){
        @Override
        public String getDisplayName() {
            return "Диспетчер";
        }

        @Override
        public String getColumnSearchName() {
            return null;
        }
    }, MEDCINE("Медицина"){
        @Override
        public String getDisplayName() {
            return "Медицина";
        }

        @Override
        public String getColumnSearchName() {
            return null;
        }
    }, OPERATOR_GPM("Оператор ГПМ"){
        @Override
        public String getDisplayName() {
            return "Оператор ГПМ";
        }

        @Override
        public String getColumnSearchName() {
            return null;
        }
    }, CONDUCTOR("Кондуктор"){
        @Override
        public String getDisplayName() {
            return "Кондуктор";
        }

        @Override
        public String getColumnSearchName() {
            return null;
        }
    };
    String name;

    EnumTypePerson(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
