package com.example.demo.entity.cars.car;

public enum EnumColumnNames {
    DATE_OF_TAKE_TO_BALLANCE("Дата постановки на баланс"){
        @Override
        public String getDisplayName() {
            return "Дата постановки на баланс";
        }

        @Override
        public String getColumnSearchName() {
            return "dateOfTakeToBalanse";
        }
    },
    DECOMISSIONED("Списан"){
        @Override
        public String getDisplayName() {
            return "Списан";
        }

        @Override
        public String getColumnSearchName() {
            return "decommissioned";
        }
    }

    ,
    DATE_OF_COMMISSIONED("Дата списания"){
        @Override
        public String getDisplayName() {
            return "Дата списания";
        }

        @Override
        public String getColumnSearchName() {
            return "dateOfdecommissioned";
        }

    },
    FAULY("Сломан") {
        @Override
        public  String getDisplayName() {
            return "Сломан";
        }

        @Override
        public  String getColumnSearchName() {
            return "fauly";
        }
    },
    PODRAZDELENIE_OR_GARAGE("Подразделение(Гараж)") {
        @Override
        public   String getDisplayName() {
            return "Подразделение(Гараж)";
        }

        @Override
        public  String getColumnSearchName() {
            return "podrazdelenieOrGarage";
        }
    },
    COLONNA("Колонна") {
        @Override
        public String getDisplayName() {
            return "Колонна";
        }

        @Override
        public String getColumnSearchName() {
            return "colonna";
        }
    },
    NUMBER_OF_GARAGE("Номер гаража") {
        @Override
        public  String getDisplayName() {
            return "Номер гаража";
        }

        @Override
        public String getColumnSearchName() {
            return "numberOfGarage";
        }
    },
    NUMBER_OF_INVENTAR("Инвентарный номер") {
        @Override
        public String getDisplayName() {
            return "Инвентарный номер";
        }

        @Override
        public String getColumnSearchName() {
            return "numberOfInventar";
        }
    },
//    COMMENT("Комментарий") {
//        @Override
//        public String getDisplayName() {
//            return "Комментарий";
//        }
//
//        @Override
//        public String getColumnSearchName() {
//            return "comment";
//        }
//    },
    TYPE_OF_FUEL("Тип топлива") {
        @Override
        public String getDisplayName() {
            return "Тип топлива";
        }

        @Override
        public String getColumnSearchName() {
            return "typeOfFuel";
        }
    },
    MILEAGE("Пробег") {
        @Override
        public  String getDisplayName() {
            return "Пробег";
        }

        @Override
        public String getColumnSearchName() {
            return "mileage";
        }
    },
    DATE_OF_MILEAGE("Дата пробега") {
        @Override
        public String getDisplayName() {
            return "Дата пробега";
        }

        @Override
        public String getColumnSearchName() {
            return "dateOfMileage";
        }
        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    MASHINE_HOURS("Моточасы") {
        @Override
        public String getDisplayName() {
            return "Машиночасы";
        }

        @Override
        public String getColumnSearchName() {
            return "mashineHours";
        }
    },
    VIN("VIN") {
        @Override
        public String getDisplayName() {
            return "VIN";
        }

        @Override
       public String getColumnSearchName() {
            return "vin";
        }
    },
    MODEL_TS("Модель ТС") {
        @Override
        public String getDisplayName() {
            return "Модель ТС";
        }

        @Override
       public String getColumnSearchName() {
            return "modelTS";
        }
        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    TYPE_TS("Тип ТС") {
        @Override
        public String getDisplayName() {
            return "Тип ТС";
        }

        @Override
       public String getColumnSearchName() {
            return "typeTS";
        }
    },
    CATEGORY("Категория") {
        @Override
        public String getDisplayName() {
            return null;
        }

        @Override
      public  String getColumnSearchName() {
            return "category";
        }
        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    YEAR_OF_BUILD("Дата производства") {
        @Override
        public String getDisplayName() {
            return "Дата производства";
        }

        @Override
       public String getColumnSearchName() {
            return "yearOfBuild";
        }
    },
    MODEL_OF_ENGINE("Модель двигателя") {
        @Override
        public String getDisplayName() {
            return null;
        }

        @Override
       public String getColumnSearchName() {
            return "modelOfEngine";
        }
        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    ECCO_OF_ENGINE("Эко класс") {
        @Override
        public String getDisplayName() {
            return "Эко класс";
        }

        @Override
       public String getColumnSearchName() {
            return "eccoClass";
        }
    },
    NUMBER_OF_ENGINE("Номер двигателя") {
        @Override
       public String getDisplayName() {
            return "Номер двигателя";
        }

        @Override
     public   String getColumnSearchName() {
            return "numberOfEngine";
        }
    },
    NUMBER_OF_CHASSIS("Номер шасси") {
        @Override
        public String getDisplayName() {
                return "Номер шасси";
        }

        @Override
     public   String getColumnSearchName() {
            return "numberOfChassis";
        }
    },
    NUMBER_OF_BODY("Номер кузова") {
        @Override
        public String getDisplayName() {
            return "Номер кузова";
        }

        @Override
     public   String getColumnSearchName() {
            return "numberOfBody";
        }
    },
    COLOR("Цвет") {
        @Override
       public String getDisplayName() {
            return "Цвет";
        }

        @Override
     public   String getColumnSearchName() {
            return "color";
        }
        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    POWER_OF_ENGINE("Мощность двигателя") {
        @Override
        public String getDisplayName() {
            return "Мощность двигателя";
        }

        @Override
      public  String getColumnSearchName() {
            return "powerOfEngine";
        }
    },
    VOLUME_OF_ENGINE("Обьем двигателя") {
        @Override
       public String getDisplayName() {
            return "Обьем двигателя";
        }

        @Override
      public  String getColumnSearchName() {
            return "volumeOfEngine";
        }
    },
    MAX_MASS("Макс. Масса") {
        @Override
        public String getDisplayName() {
            return "Макс. Масса";
        }

        @Override
       public String getColumnSearchName() {
            return "maxMass";
        }
    },
    MAX_MASS_WITHOUT("Макс. Масса без нагрузки") {
        @Override
       public String getDisplayName() {
            return "Макс. Масса без нагрузки";
        }

        @Override
       public String getColumnSearchName() {
            return "maxMassWithout";
        }
    },
    BUILDER("Производитель") {
        @Override
        public String getDisplayName() {
            return "Производитель";
        }

        @Override
     public   String getColumnSearchName() {
            return "builder";
        }
        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },

    NUMBER_OF_PASSPORT_TS("Номер паспорта ТС") {
        @Override
       public String getDisplayName() {
            return "Номер паспорта ТС";
        }

        @Override
      public  String getColumnSearchName() {
            return "numberOfPassportTS";
        }
    },
    DATE_OF_PASSPORT_TS("Дата паспорта ТС") {
        @Override
        public String getDisplayName() {
            return null;
        }

        @Override
     public   String getColumnSearchName() {
            return "dateOfPassportTS";
        }
        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    PLACE_OF_INSSUANCE_OF_PASSPORT_TS("Место выдачи паспорта") {
        @Override
       public String getDisplayName() {
            return null;
        }

        @Override
      public  String getColumnSearchName() {
            return "placeOfIssuanceOfPassportTS";
        }
        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },

    COST("Цена") {
        @Override
       public String getDisplayName() {
            return "Цена";
        }

        @Override
      public  String getColumnSearchName() {
            return "cost";
        }
    },
    REG_NUMBER("Рег. номер") {
        @Override
        public String getDisplayName() {
            return "Рег. номер";
        }

        @Override
      public  String getColumnSearchName() {
            return "regNumber";
        }
    },
    OLDREG_NUMBER("Старый рег. номер") {
        @Override
        public String getDisplayName() {
            return null;
        }

        @Override
      public  String getColumnSearchName() {
            return "oldregNumber";
        }
        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    CERTIFICATE_OF_REGISTRATION("Сертификат регистрации") {
        @Override
      public  String getDisplayName() {
            return null;
        }

        @Override
     public   String getColumnSearchName() {
            return "certificateOfRegistration";
        }
        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    PLACE_OF_REGISTRATION("Место регистрации") {
        @Override
        public String getDisplayName() {
            return "Место регистрации";
        }

        @Override
      public  String getColumnSearchName() {
            return "placeOfregistration";
        }
        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    DATE_OF_REGISTRATION("Дата регистрации") {
        @Override
        public String getDisplayName() {
            return null;
        }

        @Override
      public  String getColumnSearchName() {
            return "dateOfRegistration";
        }
        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    TEMP_REGISTRATION("Временная регистрация") {
        @Override
       public String getDisplayName() {
            return null;
        }

        @Override
        public String getColumnSearchName() {
            return "tempRegistration";
        }
        @Override
        public boolean getVisibleForCombobox() {
            return false;
        }
    },
    QUANTITY_OF_PALLET("Количество паллет") {
        @Override
        public String getDisplayName() {
            return "Количество паллет";
        }

        @Override
       public String getColumnSearchName() {
            return "quantityOfPallet";
        }
    },
    LENGHT_OF_BODY("Длина кузова") {
        @Override
        public String getDisplayName() {
            return "Длина кузова";
        }

        @Override
       public String getColumnSearchName() {
            return "lenghtOfBody";
        }
    },
    WIDHT_OF_BODY("Ширина кузова") {
        @Override
      public   String getDisplayName() {
            return "Ширина кузова";
        }

        @Override
        public String getColumnSearchName() {
            return "widhtOfBody";
        }
    },
    HEIGHT_OF_BODY("Высота кузова") {
        @Override
        public String getDisplayName() {
            return "Высота кузова";
        }

        @Override
        public String getColumnSearchName() {
            return "heightOfBody";
        }
    },
    VOLUME_OF_BODY("Обьем кузова") {
        @Override
       public String getDisplayName() {
            return "Обьем кузова";
        }

        @Override
       public String getColumnSearchName() {
            return "volumeOfBody";
        }
    };

    String name;

    EnumColumnNames(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getDisplayName();
    }

    public abstract String getDisplayName();
    public abstract String getColumnSearchName();
    public  boolean getVisibleForCombobox(){return true;}
}
