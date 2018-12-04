package com.example.demo.entity.jornal;

import com.example.demo.entity.IEnumColumnNames;

public enum  EnumColumnNameForJournal implements IEnumColumnNames {
    CLOSED{
        @Override
        public String getDisplayName() {
            return "Закрыт";
        }

        @Override
        public String getColumnSearchName() {
            return "closed";
        }
    };

     EnumColumnNameForJournal() {
    }


    @Override
    public String toString() {
        return  getDisplayName();
    }

    public  boolean getVisibleForCombobox(){return true;}

}
