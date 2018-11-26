package com.example.demo.entity.organisation;

import com.example.demo.entity.IEnumColumnNames;

public enum  EnumColumnNameForOrg implements IEnumColumnNames {

    NAME{
        @Override
        public String getDisplayName() {

            return "Название";
        }

        @Override
        public String getColumnSearchName() {
            return "name";
        }
    },
    INN{
        @Override
        public String getDisplayName() {
            return "ИНН";
        }

        @Override
        public String getColumnSearchName() {
            return "inn";
        }
    },
    EGRUL {
        @Override
        public String getDisplayName() {
            return "ЕГРЮЛ";
        }

        @Override
        public String getColumnSearchName() {
            return "egrul";
        }
    },
    OKPO{
        @Override
        public String getDisplayName() {
            return "ОКПО";
        }

        @Override
        public String getColumnSearchName() {
            return "okpo";
        }
    },
    OGRN{
        @Override
        public String getDisplayName() {
            return "ОГРН";
        }

        @Override
        public String getColumnSearchName() {
            return  "ogrn";
        }
    },
    KPP{
        @Override
        public String getDisplayName() {
            return "КПП";
        }

        @Override
        public String getColumnSearchName() {
            return "kpp";
        }
    };

     EnumColumnNameForOrg() {
    }
    public  boolean getVisibleForCombobox(){return true;}


    @Override
    public String toString() {
        return getDisplayName();
    }
}
