package pl.sroks.shippingbins.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import pl.sroks.shippingbins.exceptions.FileDoesNotExistException;
import pl.sroks.shippingbins.exceptions.supplier.SupplierAlreadyExistsException;
import pl.sroks.shippingbins.exceptions.supplier.SupplierNotFoundException;
import pl.sroks.shippingbins.models.supplier.Supplier;
import pl.sroks.shippingbins.models.supplier.SupplierConverter;
import pl.sroks.shippingbins.models.supplier.SupplierDto;
import pl.sroks.shippingbins.repositories.SupplierRepository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<SupplierDto> getSupplierList() {
        List<Supplier> supplierList = supplierRepository.findAll();
        return SupplierConverter.convertSupplierListToSupplierDtoList(supplierList);
    }

    public SupplierDto createNewSupplier(SupplierDto supplierDto) {
        if (supplierRepository.findSupplierByName(supplierDto.getName()) != null) {
            throw new SupplierAlreadyExistsException("Provided truck's name already exists: " + supplierDto.getName());
        }
        Supplier supplier = SupplierConverter.convertToSupplier(supplierDto);
        supplier.setId(supplier.getId());
        supplierRepository.save(supplier);
        return SupplierConverter.convertToSupplierDto(supplier);
    }

    public Supplier findSupplierInDB(int id) {
        return supplierRepository.findById(id).orElseThrow(() ->
                new SupplierNotFoundException("Could not find supplier with id: " + id));
    }

    public SupplierDto findSupplierById(int id) {
        Supplier supplier = findSupplierInDB(id);
        return SupplierConverter.convertToSupplierDto(supplier);
    }

    public SupplierDto updateSupplier(int id, Map<Object, Object> fields) {
        Supplier supplier = findSupplierInDB(id);
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Supplier.class, (String) key);
            if (field == null) {
                throw new FileDoesNotExistException("Field " + key + " does not exist");
            }
            field.setAccessible(true);
            ReflectionUtils.setField(field, supplier, value);
        });
        supplierRepository.save(supplier);
        return SupplierConverter.convertToSupplierDto(supplier);
    }

    @Transactional
    public void deleteSupplierById(int id) {
        if (supplierRepository.existsById(id)) {
            supplierRepository.deleteById(id);
        }
    }
}
