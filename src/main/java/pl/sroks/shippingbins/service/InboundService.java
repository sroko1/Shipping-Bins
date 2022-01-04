package pl.sroks.shippingbins.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import pl.sroks.shippingbins.exceptions.FileDoesNotExistException;
import pl.sroks.shippingbins.exceptions.inbound.InboundNotFoundException;
import pl.sroks.shippingbins.exceptions.inbound.InboundPositiveDigitNotFoundException;
import pl.sroks.shippingbins.models.bin.Bin;
import pl.sroks.shippingbins.models.inbound.Inbound;
import pl.sroks.shippingbins.models.inbound.InboundConverter;
import pl.sroks.shippingbins.models.inbound.InboundDto;
import pl.sroks.shippingbins.models.supplier.Supplier;
import pl.sroks.shippingbins.models.truck.Truck;
import pl.sroks.shippingbins.models.truck.TruckConverter;
import pl.sroks.shippingbins.models.truck.TruckDto;
import pl.sroks.shippingbins.repositories.BinRepository;
import pl.sroks.shippingbins.repositories.InboundRepository;
import pl.sroks.shippingbins.repositories.SupplierRepository;
import pl.sroks.shippingbins.repositories.TruckRepository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class InboundService {

    private final InboundRepository inboundRepository;
    private final BinRepository binRepository;
    private final TruckRepository truckRepository;
    private final SupplierRepository supplierRepository;

    public List<InboundDto> getInboundList() {
        List<Inbound> inboundList = inboundRepository.findAll();
        return InboundConverter.convertInboundListToInboundDtoList(inboundList);
    }

    public InboundDto createNewInbound(InboundDto inboundDto) {
        if (truckRepository.findTruckByRegNumber(inboundDto.getTruck().getRegNumber()) == null) {
            Truck truck = Truck.builder()
                    .id(inboundDto.getTruck().getId())
                    .regNumber(inboundDto.getTruck().getRegNumber())
                    .trailerType("NORMAL")
                    .trailerMaxVolume(13600)
                    .build();
            truckRepository.save(truck);

        }
        if (supplierRepository.findSupplierByName(inboundDto.getSupplier().getName()) == null) {
            Supplier supplier = Supplier.builder()
                    .id(inboundDto.getSupplier().getId())
                    .name(inboundDto.getSupplier().getName())
                    .build();
            supplierRepository.save(supplier);
        }
// here
        if (binRepository.findBinByName(inboundDto.getBin().getName()) == null) {
            Bin bin = Bin.builder()
                    .id(inboundDto.getBin().getId())
                    .name(inboundDto.getBin().getName())
                    .width(1240)
                    .length(835)
                    .height(966)
                    .volume(100)
                    .price(91.69)
                    .amount(inboundDto.getQuantity())
                    .leasingPrice(inboundDto.getBin().getPrice() * inboundDto.getBin().getAmount())
                    .build();
            binRepository.save(bin);
        } else {
            Bin bin =
                    binRepository.findBinByName(inboundDto.getBin().getName());
            bin.setAmount(bin.getAmount() + inboundDto.getQuantity());
            binRepository.save(bin);


        }


        if (inboundDto.getQuantity() < 0) {
            throw new InboundPositiveDigitNotFoundException("Provided quantity can not be negative digit: " + inboundDto.getQuantity());
        }

        Inbound inbound = Inbound.builder()
                .id(inboundDto.getId())
                .incomingType(inboundDto.getIncomingType())
                .quantity(inboundDto.getQuantity())
                .location(inboundDto.getLocation())
                .arrived(inboundDto.getArrived())
                .bin(binRepository.findBinByName(inboundDto.getBin().getName()))
                .truck(truckRepository.findTruckByRegNumber(inboundDto.getTruck().getRegNumber()))
                .supplier(supplierRepository.findSupplierByName(inboundDto.getSupplier().getName()))
                .build();
        inboundRepository.save(inbound);
        return InboundConverter.convertToInboundDto(inbound);
    }

    public Inbound findInboundInDB(int id) {
        return inboundRepository.findById(id).orElseThrow(() ->
                new InboundNotFoundException("Could not find inbound with id: " + id));
    }

    public InboundDto findInboundById(int id) {
        Inbound inbound = findInboundInDB(id);
        return InboundConverter.convertToInboundDto(inbound);
    }

    @Transactional
    public void deleteInboundById(int id) {
        if (inboundRepository.existsById(id)) {
            inboundRepository.deleteById(id);
        }
    }



    public InboundDto updateInbound( int id, InboundDto inboundDto) {
       /*
        if (truckRepository.findTruckByRegNumber(inboundDto.getTruck().getRegNumber()) == null) {
            Truck truck = Truck.builder()
                    .id(inboundDto.getTruck().getId())
                    .regNumber(inboundDto.getTruck().getRegNumber())
                    .trailerType("NORMAL")
                    .trailerMaxVolume(13600)
                    .build();
            truckRepository.save(truck);

        }
        if (supplierRepository.findSupplierByName(inboundDto.getSupplier().getName()) == null) {
            Supplier supplier = Supplier.builder()
                    .id(inboundDto.getSupplier().getId())
                    .name(inboundDto.getSupplier().getName())
                    .build();
            supplierRepository.save(supplier);
        }
// here
        if (binRepository.findBinByName(inboundDto.getBin().getName()) == null) {
            Bin bin = Bin.builder()
                    .id(inboundDto.getBin().getId())
                    .name(inboundDto.getBin().getName())
                    .width(1240)
                    .length(835)
                    .height(966)
                    .volume(100)
                    .price(91.69)
                    .amount(inboundDto.getQuantity())
                    .leasingPrice(inboundDto.getBin().getPrice() * inboundDto.getBin().getAmount())
                    .build();
            binRepository.save(bin);
        } else {
            Bin bin =
                    binRepository.findBinByName(inboundDto.getBin().getName());
            bin.setAmount(bin.getAmount() + inboundDto.getQuantity());
            binRepository.save(bin);

        }

        if (inboundDto.getQuantity() < 0) {
            throw new InboundPositiveDigitNotFoundException("Provided quantity can not be negative digit: " + inboundDto.getQuantity());
        }

        */

        Inbound inbound = inboundRepository.getById(id);
        inbound.setIncomingType(inboundDto.getIncomingType());
        inbound.setQuantity(inboundDto.getQuantity());
        inbound.setLocation(inboundDto.getLocation());
        inbound.setArrived(inboundDto.getArrived());
        inbound.setBin(binRepository.findBinByName(inboundDto.getBin().getName()));
        inbound.setTruck(truckRepository.findTruckByRegNumber(inboundDto.getTruck().getRegNumber()));
        inbound.setSupplier(supplierRepository.findSupplierByName(inboundDto.getSupplier().getName()));
        inboundRepository.save(inbound);
        return InboundConverter.convertToInboundDto(inbound);
    }

}
