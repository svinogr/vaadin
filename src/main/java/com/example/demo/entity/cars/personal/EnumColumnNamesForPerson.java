package com.example.demo.entity.cars.personal;

import com.example.demo.entity.IEnumColumnNames;

public enum EnumColumnNamesForPerson implements IEnumColumnNames {
    DATE_OF_BIRTH{
        @Override
        public String getDisplayName() {
            return "Дата рождения";
        }

        @Override
        public String getColumnSearchName() {
            return "birthday";
        }
    },
    SURNAME{
        @Override
        public String getDisplayName() {
            return "Фамилия";
        }

        @Override
        public String getColumnSearchName() {
            return "surname";
        }
    },
    TYPE_PERSON{
        @Override
        public String getDisplayName() {
            return "Тип";
        }

        @Override
        public String getColumnSearchName() {
            return "enumTypePerson";
        }
    },
    FIRED{
        @Override
        public String getDisplayName() {
            return "Уволен";
        }

        @Override
        public String getColumnSearchName() {
            return "fired";
        }
    };

    EnumColumnNamesForPerson() {

    }

    @Override
    public String toString() {
        return getDisplayName();
    }

    public  boolean getVisibleForCombobox(){return true;}

}
