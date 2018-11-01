package com.example.demo.services.serviceImpl;


import com.example.demo.dao.CarRepository;
import com.example.demo.entity.cars.car.Car;
import com.example.demo.entity.cars.car.EnumTypeFuel;
import com.example.demo.entity.cars.car.EnumYesNo;
import com.example.demo.entity.cars.car.GeneralData;
import com.example.demo.entity.cars.utils.search.CarSpecification;
import com.example.demo.entity.cars.utils.search.MyFilterItem;
import com.example.demo.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Override
    public Car getById(long id) {
        return carRepository.findById(id).get();// getOne(id);
    }

    @Override
    public Car create(Car car) {
        System.out.println(car);
        Car save = carRepository.save(car);
        return save;
    }

    @Override
    public Car createUser(Car car) {
        return null;
    }

    @Override
    public Car update(Car car) {
        Car save = carRepository.save(car);
        return save;
    }

    @Transactional
    @Override
    public boolean delete(Car car) {
        carRepository.delete(car);
        return true;
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> findByExample(Optional<MyFilterItem> myFilterItem, int offset, int limit) {
        List<Car> resulList;
        Pageable pageable = PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "id"));
        if (myFilterItem.isPresent()) {
            Specification<Car> carSpecification = createSpecification(myFilterItem.get());
            resulList = carRepository.findAll(carSpecification, pageable).getContent();
            return resulList;
        } else {
            resulList = carRepository.findAll();
        }
        return resulList;
    }

    private Specification<Car> createSpecification(MyFilterItem myFilterItem) {
        Specification<Car> specification = null;

        switch (myFilterItem.getEnumColumnNames()) {
            case DATE_OF_TAKE_TO_BALLANCE:
                System.out.println("spec "+myFilterItem.getEnumColumnNames());

                specification = CarSpecification.generalByDateTaleToBalance(myFilterItem);
                break;
            case DECOMISSIONED:
                specification = CarSpecification.generalByDecomissionned(myFilterItem);
                break;
            case DATE_OF_COMMISSIONED:
                break;
            case FAULY:
                System.out.println("faulu spec "+myFilterItem.isChecked());
                specification = CarSpecification.generalByFauly(myFilterItem);
                break;
            case PODRAZDELENIE_OR_GARAGE:
                specification = CarSpecification.generalByGarageOrPodrazdelenie(myFilterItem);

                break;
            case COLONNA:
                specification = CarSpecification.generalByColonna(myFilterItem);

                break;
            case NUMBER_OF_GARAGE:
                specification = CarSpecification.generalByNumberGarage(myFilterItem);

                break;
            case NUMBER_OF_INVENTAR:
                specification = CarSpecification.generalByNumberInventar(myFilterItem);

                break;
            case TYPE_OF_FUEL:
                specification = CarSpecification.generalByTypeOfFuel(myFilterItem);

                break;
            case MILEAGE:
                specification = CarSpecification.generalByMeleage(myFilterItem);

                break;
            case MASHINE_HOURS:
                specification = CarSpecification.generalByMashineHours(myFilterItem);

                break;
            case VIN:
                specification = CarSpecification.passportByVin(myFilterItem);

                break;
            case TYPE_TS:
                specification = CarSpecification.passportByTypeTS(myFilterItem);

                break;
            case YEAR_OF_BUILD:
                specification = CarSpecification.passportByYearOfBuild(myFilterItem);

                break;
            case ECCO_OF_ENGINE:
                specification = CarSpecification.passportByEcoOfEngine(myFilterItem);

                break;
            case NUMBER_OF_ENGINE:

                specification = CarSpecification.passportByNumberEngine(myFilterItem);

                break;
            case NUMBER_OF_CHASSIS:
                specification = CarSpecification.passportByNumberOfChassis(myFilterItem);

                break;
            case NUMBER_OF_BODY:
                specification = CarSpecification.passportByNumberOfBody(myFilterItem);

                break;
            case POWER_OF_ENGINE:
                specification = CarSpecification.passportByPowerOfEngine(myFilterItem);

                break;
            case VOLUME_OF_ENGINE:
                specification = CarSpecification.passportByVolumeOfEngine(myFilterItem);

                break;
            case MAX_MASS:
                specification = CarSpecification.passportByMass(myFilterItem);

                break;
            case MAX_MASS_WITHOUT:
                specification = CarSpecification.passportByMassWithout(myFilterItem);

                break;
            case NUMBER_OF_PASSPORT_TS:
                specification = CarSpecification.passportByNumberOfPassport(myFilterItem);

                break;
            case REG_NUMBER:
                specification = CarSpecification.passportByRegNumber(myFilterItem);

                break;
            case QUANTITY_OF_PALLET:
                specification = CarSpecification.passportByQuantityOfPallete(myFilterItem);


                break;
            case WIDHT_OF_BODY:
                specification = CarSpecification.passportByWidhtOfBody(myFilterItem);

                break;
            case HEIGHT_OF_BODY:
                specification = CarSpecification.passportByHeightOfBody(myFilterItem);

                break;
            case LENGHT_OF_BODY:
                specification = CarSpecification.passportByLenghtOfBody(myFilterItem);

                break;
            case VOLUME_OF_BODY:
                specification = CarSpecification.passportByVolumetOfBody(myFilterItem);

                break;
            default:
                System.out.println("не удалсоь найти спецификацию");
        }
        return specification;
    }

    @Override
    public int getCount(Optional<MyFilterItem> myFilterItem) {
        int count = 0;

        if (myFilterItem.isPresent()) {
            Specification<Car> specification = createSpecification(myFilterItem.get());
            count = Math.toIntExact(carRepository.count(specification));
        }else {
           count = Math.toIntExact(carRepository.count());
        }
        return count;
//        Car car = new Car();
//        GeneralData generalData = new GeneralData();
//        generalData.setFauly(true);
//        car.setGeneralData(generalData);
//        // return Math.toIntExact(carRepository.count(Example.of(car)));
//        return 1;

//        if(queryProperty != null){
//            Car c = new Car();
//            c.setId(Integer.parseInt(queryProperty[1]));
//            System.out.println("fffffffffffffff");
//            Example<Car> example = Example.of(c);
//            long i = carRepository.count(example);
//            System.err.println("hhhhhhhjjjjjjjjjjjjjjjjjjjjjjjjjjjjj"+i);
//            return Math.toIntExact(i);
        //  } else return Math.toIntExact(carRepository.count());
        // return 0;
    }

}


