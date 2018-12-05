package com.example.demo.services.serviceImpl;

import com.example.demo.dao.CarRepository;
import com.example.demo.entity.cars.car.Car;
import com.example.demo.entity.cars.car.EnumColumnNamesForCar;
import com.example.demo.services.CarService;
import com.example.demo.services.ItemService;
import com.example.demo.services.search.CarSpecification;
import com.example.demo.services.search.MyFilterItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
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
        System.out.println(car.getGeneralData().getTypeOfFuel());
        Car save = carRepository.save(car);
        return save;
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
        EnumColumnNamesForCar enumColumnNamesForCar = (EnumColumnNamesForCar) myFilterItem.getEnumColumnNamesFor();
        switch (enumColumnNamesForCar) {
            case DATE_OF_TAKE_TO_BALLANCE:
                specification = CarSpecification.generalByDateTaleToBalance(myFilterItem);
                break;
            case DECOMISSIONED:
                specification = CarSpecification.generalByDecomissionned(myFilterItem);
                break;
            case DATE_OF_COMMISSIONED:
                break;
            case FAULY:
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
            case TYPE_BODY:
                specification = CarSpecification.passportByTypeBody(myFilterItem);

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
    }

    @Override
    public List<Car> findByExampleWithoutPagable(Optional<MyFilterItem> myFilterItem) {
        List<Car> resulList;
        if (myFilterItem.isPresent()) {
            Specification<Car> carSpecification = createSpecification(myFilterItem.get());
            resulList = carRepository.findAll(carSpecification);
            return resulList;
        } else {
            resulList = carRepository.findAll();
        }
        return resulList;
    }

}
