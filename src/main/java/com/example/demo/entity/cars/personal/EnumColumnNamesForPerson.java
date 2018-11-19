package com.example.demo.entity.cars.personal;

public enum EnumColumnNamesForPerson {
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
    };

    EnumColumnNamesForPerson() {

    }

    @Override
    public String toString() {
        return getDisplayName();
    }

    public abstract String getDisplayName();
    public abstract String getColumnSearchName();
    public  boolean getVisibleForCombobox(){return true;}

}
