package com.example.demo.entity.users;

import com.example.demo.entity.IEnumColumnNames;

public enum EnumUserColumnNameForUser implements IEnumColumnNames {
    SURNAME{
        @Override
        public String getDisplayName() {
            return "Фамилия";
        }

        @Override
        public String getColumnSearchName() {
            return "surname";
        }
    };

    @Override
    public String toString() {
        return  getDisplayName();
    }

    public  boolean getVisibleForCombobox(){return true;}
}
