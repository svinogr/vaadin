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
    COMMENT("Комментарий") {
        @Override
        public String getDisplayName() {
            return "Комментарий";
        }

        @Override
        public String getColumnSearchName() {
            return "comment";
        }
    },
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
    },
    MASHINE_HOURS("Машиночасы") {
        @Override
        public String getDisplayName() {
            return "Машиночасы";
        }

        @Override
        public String getColumnSearchName() {
            return "mashineHours";
        }
    };
    //,
//    VIN("VIN") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//       public String getColumnSearchName() {
//            return "vin";
//        }
//    },
//    MODEL_TS("Модель ТС") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//       public String getColumnSearchName() {
//            return "modelTS";
//        }
//    },
//    TYPE_TS("Тип ТС") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//       public String getColumnSearchName() {
//            return "typeTS";
//        }
//    },
//    CATEGORY("Категория") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//      public  String getColumnSearchName() {
//            return "category";
//        }
//    },
//    YEAR_OF_BUILD("Дата производства") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//       public String getColumnSearchName() {
//            return "yearOfBuild";
//        }
//    },
//    MODEL_OF_ENGINE("Модель двигателя") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//       public String getColumnSearchName() {
//            return "modelOfEngine";
//        }
//    },
//    ECCO_OF_ENGINE("Эко класс") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//       public String getColumnSearchName() {
//            return "eccoClass";
//        }
//    },
//    NUMBER_OF_ENGINE("Номер двигателя") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//     public   String getColumnSearchName() {
//            return "numberOfEngine";
//        }
//    },
//    NUMBER_OF_CHASSIS("Номер шасси") {
//        @Override
//        String getDisplayName() {
//                return null;
//        }
//
//        @Override
//     public   String getColumnSearchName() {
//            return "numberOfChassis";
//        }
//    },
//    NUMBER_OF_BODY("Номер кузова") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//     public   String getColumnSearchName() {
//            return "numberOfBody";
//        }
//    },
//    COLOR("Цвет") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//     public   String getColumnSearchName() {
//            return "color";
//        }
//    },
//    POWER_OF_ENGINE("Мощность двигателя") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//      public  String getColumnSearchName() {
//            return "powerOfEngine";
//        }
//    },
//    VOLUME_OF_ENGINE("Обьем двигателя") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//      public  String getColumnSearchName() {
//            return "volumeOfEngine";
//        }
//    },
//    MAX_MASS("Макс. Масса") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//       public String getColumnSearchName() {
//            return "maxMass";
//        }
//    },
//    MAX_MASS_WITHOUT("Макс. Масса без нагрузки") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//       public String getColumnSearchName() {
//            return "maxMassWithout";
//        }
//    },
//    BUILDER("Производитель") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//     public   String getColumnSearchName() {
//            return "builder";
//        }
//    },
//    NUMBER_OF_PASSPORT_TS("Номер паспорта ТС") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//      public  String getColumnSearchName() {
//            return "numberOfPassportTS";
//        }
//    },
//    DATE_OF_PASSPORT_TS("Дата паспорта ТС") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//     public   String getColumnSearchName() {
//            return "dateOfPassportTS";
//        }
//    },
//    PLACE_OF_INSSUANCE_OF_PASSPORT_TS("Место выдачи паспорта") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//      public  String getColumnSearchName() {
//            return "placeOfIssuanceOfPassportTS";
//        }
//    },
//    COST("Цена") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//      public  String getColumnSearchName() {
//            return "cost";
//        }
//    },
//    REG_NUMBER("Рег. номер") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//      public  String getColumnSearchName() {
//            return "regNumber";
//        }
//    },
//    OLDREG_NUMBER("Старый рег. номер") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//      public  String getColumnSearchName() {
//            return "oldregNumber";
//        }
//    },
//    CERTIFICATE_OF_REGISTRATION("Сертификат регистрации") {
//        @Override
//      public  String getDisplayName() {
//            return null;
//        }
//
//        @Override
//     public   String getColumnSearchName() {
//            return "certificateOfRegistration";
//        }
//    },
//    PLACE_OF_REGISTRATION("Место регистрации") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//      public  String getColumnSearchName() {
//            return "placeOfregistration";
//        }
//    },
//    DATE_OF_REGISTRATION("Дата регистрации") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//      public  String getColumnSearchName() {
//            return "dateOfRegistration";
//        }
//    },
//    TEMP_REGISTRATION("Временная регистрация") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//        public String getColumnSearchName() {
//            return "tempRegistration";
//        }
//    },
//    QUANTITY_OF_PALLET("Количество паллет") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//       public String getColumnSearchName() {
//            return "quantityOfPallet";
//        }
//    },
//    LENGHT_OF_BODY("Длина кузова") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//       publicpublic String getColumnSearchName() {
//            return "lenghtOfBody";
//        }
//    },
//    WIDHT_OF_BODY("Ширина кузова") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//        publicString getColumnSearchName() {
//            return "widhtOfBody";
//        }
//    },
//    HEIGHT_OF_BODY("Высота кузова") {
//        @Override
//        publicString getDisplayName() {
//            return null;
//        }
//
//        @Override
//        String getColumnSearchName() {
//            return "heightOfBody";
//        }
//    },
//    VOLUME_OF_BODY("Обьем кузова") {
//        @Override
//        String getDisplayName() {
//            return null;
//        }
//
//        @Override
//       public String getColumnSearchName() {
//            return "volumeOfBody";
//        }
//    };

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
}
