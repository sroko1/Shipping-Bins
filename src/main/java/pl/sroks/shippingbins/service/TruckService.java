package pl.sroks.shippingbins.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import pl.sroks.shippingbins.exceptions.truck.TruckAlreadyExistsException;
import pl.sroks.shippingbins.exceptions.truck.TruckNotFoundException;
import pl.sroks.shippingbins.models.truck.Truck;
import pl.sroks.shippingbins.models.truck.TruckConverter;
import pl.sroks.shippingbins.models.truck.TruckDto;
import pl.sroks.shippingbins.repositories.TruckRepository;

import java.util.List;

@Service
public class TruckService {

    private final TruckRepository truckRepository;

    public TruckService(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    public List<TruckDto> getTruckList() {
        List<Truck> truckList = truckRepository.findAll();
        return TruckConverter.convertTruckListToTruckDtoList(truckList);
    }

    public TruckDto createNewTruck(TruckDto truckDto) {
        if (truckRepository.findTruckByRegNumber(truckDto.getRegNumber()) != null) {
            throw new TruckAlreadyExistsException("Provided truck's name already exists: " + truckDto.getRegNumber());
        }
        Truck truck = TruckConverter.convertToTruck(truckDto);
        truck.setId(truck.getId());
        truckRepository.save(truck);
        return TruckConverter.convertToTruckDto(truck);
    }

    public Truck findTruckInDB(int id) {
        return truckRepository.findById(id).orElseThrow(() ->
                new TruckNotFoundException("Could not find truck with id: " + id));
    }

    public TruckDto findTruckById(int id) {
        Truck truck = findTruckInDB(id);
        return TruckConverter.convertToTruckDto(truck);
    }

    public TruckDto updateTruck(TruckDto truckDto) {
        Truck truck = TruckConverter.convertToTruck(truckDto);
        truck.setRegNumber(truck.getRegNumber());
        truck.setTrailerType(truck.getTrailerType());
        truck.setTrailerMaxVolume(truck.getTrailerMaxVolume());
        truckRepository.save(truck);
        return TruckConverter.convertToTruckDto(truck);
    }

    @Transactional
    public void deleteTruckById(@PathVariable("id") int id) {
        if (truckRepository.existsById(id)) {
            truckRepository.deleteById(id);
        }
    }

}
