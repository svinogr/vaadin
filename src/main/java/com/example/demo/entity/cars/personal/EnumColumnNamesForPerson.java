package com.example.demo.entity.cars.personal;

import com.example.demo.entity.IEnumColumnNames;

public enum EnumColumnNamesForPerson implements IEnumColumnNames {
    NAME{
        @Override
        public String getDisplayName() {
            return "Имя";
        }

        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }

        @Override
        public String getColumnSearchName() {
            return "name";

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
    },PATRONYMIC{
        @Override
        public String getDisplayName() {
            return "Отчество";
        }

        @Override
        public String getColumnSearchName() {
            return "patronymic";
        }

        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
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
     PHONE{
@Override
public String getDisplayName() {
        return "Телефон";
        }

@Override
public String getColumnSearchName() {
        return "phone";
        }

@Override
public boolean getVisibleForCombobox() {
        return false;
        }
        },
    ADDRESS{
        @Override
        public String getDisplayName() {
            return "Адрес";
        }

        @Override
        public String getColumnSearchName() {
            return "address";
        }

        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    COMMENT{
        @Override
        public String getDisplayName() {
            return "Комментарий";
        }

        @Override
        public String getColumnSearchName() {
            return "commment";
        }

        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    POSITION{
        @Override
        public String getDisplayName() {
            return "Должность";
        }

        @Override
        public String getColumnSearchName() {
            return "position";
        }

        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    ORDER{
        @Override
        public String getDisplayName() {
            return "Приказ";
        }

        @Override
        public String getColumnSearchName() {
            return "order_name";
        }

        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    DATE_ORDER{
        @Override
        public String getDisplayName() {
            return "Дата приказа";
        }

        @Override
        public String getColumnSearchName() {
            return "date_order";
        }

        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    TABEL{
        @Override
        public String getDisplayName() {
            return "Табель";
        }

        @Override
        public String getColumnSearchName() {
            return "namber_tabel";
        }

        @Override
        public boolean getVisibleForCombobox() {
            return false;
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
