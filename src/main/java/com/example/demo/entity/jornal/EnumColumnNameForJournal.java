package com.example.demo.entity.jornal;

import com.example.demo.entity.IEnumColumnNames;

public enum EnumColumnNameForJournal implements IEnumColumnNames {

    TYPE_OF_RECORD {
        @Override
        public String getDisplayName() {
            return "Тип записи";
        }

        @Override
        public String getColumnSearchName() {
            return "enumTypeRecord";
        }

        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    TYPE_OF_TO {
        @Override
        public String getDisplayName() {
            return "Тип ТО";
        }

        @Override
        public String getColumnSearchName() {
            return "typeTo";
        }

        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    TYPE_OF_OIL {
        @Override
        public String getDisplayName() {
            return "Тип смазочнных материаллов";
        }

        @Override
        public String getColumnSearchName() {
            return "typeOil";
        }

        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    NAME {
        @Override
        public String getDisplayName() {
            return "Имя";
        }

        @Override
        public String getColumnSearchName() {
            return "name";
        }

        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    MODEL {
        @Override
        public String getDisplayName() {
            return "Mодель";
        }

        @Override
        public String getColumnSearchName() {
            return "model";
        }

        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    CODE {
        @Override
        public String getDisplayName() {
            return "Код";
        }

        @Override
        public String getColumnSearchName() {
            return "code";
        }

        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    DATE_OF_SETUP {
        @Override
        public String getDisplayName() {
            return "Дата установки";
        }

        @Override
        public String getColumnSearchName() {
            return "date_setup";
        }

        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    MILEAGE_OF_SETUP {
        @Override
        public String getDisplayName() {
            return "Пробег при установке";
        }

        @Override
        public String getColumnSearchName() {
            return "setup_mileage";
        }

        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    COST {
        @Override
        public String getDisplayName() {
            return "Стоимость";
        }

        @Override
        public String getColumnSearchName() {
            return "cost";
        }

        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    QUANTITY {
        @Override
        public String getDisplayName() {
            return "Количество";
        }

        @Override
        public String getColumnSearchName() {
            return "enumTypeRecord";
        }

        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    TYPE_OF_UNITS {
        @Override
        public String getDisplayName() {
            return "Единицы измерения";
        }

        @Override
        public String getColumnSearchName() {
            return "type_of_units";
        }

        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    COMMENT {
        @Override
        public String getDisplayName() {
            return "Комментарий";
        }

        @Override
        public String getColumnSearchName() {
            return "comment";
        }

        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    DATE_OF_DELETE {
        @Override
        public String getDisplayName() {
            return "Дата снятия";
        }

        @Override
        public String getColumnSearchName() {
            return "date_delete";
        }

        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    MILEAGE_OF_DELETE {
        @Override
        public String getDisplayName() {
            return "Пробег при снятиии";
        }

        @Override
        public String getColumnSearchName() {
            return "delete_mileage";
        }

        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    CAUSE {
        @Override
        public String getDisplayName() {
            return "Причина";
        }

        @Override
        public String getColumnSearchName() {
            return "cause";
        }

        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },

    CLOSED {
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
        return getDisplayName();
    }

    public boolean getVisibleForCombobox() {
        return true;
    }

}
