package pl.sroks.shippingbins.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import pl.sroks.shippingbins.exceptions.FileDoesNotExistException;
import pl.sroks.shippingbins.exceptions.bin.BinAlreadyExistsException;
import pl.sroks.shippingbins.exceptions.bin.BinNotFoundException;
import pl.sroks.shippingbins.models.bin.Bin;
import pl.sroks.shippingbins.models.bin.BinConverter;
import pl.sroks.shippingbins.models.bin.BinDto;
import pl.sroks.shippingbins.repositories.BinRepository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class BinService {
    private final BinRepository binRepository;

    public BinService(BinRepository binRepository) {
        this.binRepository = binRepository;
    }

    public List<BinDto> getBinList() {
        List<Bin> binList = binRepository.findAll();
        return BinConverter.convertBinListToBinDtoList(binList);
    }

    public BinDto createNewBin(BinDto binDto) {
        if (binRepository.findBinByName(binDto.getName()) != null) {
            throw new BinAlreadyExistsException("Provided bin's name already exists: " + binDto.getName());
        }
        Bin bin = BinConverter.convertToBin(binDto);
        bin.setId(bin.getId());
        bin.setVolume(bin.getLength() * bin.getHeight() * bin.getWidth());
        bin.setLeasingPrice(bin.getPrice() * bin.getAmount());
        binRepository.save(bin);
        return BinConverter.convertToBinDto(bin);

    }

    public Bin findBinInDB(int id) {
        return binRepository.findById(id).orElseThrow(() ->
                new BinNotFoundException("Could not found Bin with id: " + id));
    }

    public BinDto findBinById(int id) {
        Bin bin = findBinInDB(id);
        return BinConverter.convertToBinDto(bin);
    }

    public BinDto updateBin(int id, Map<Object, Object> fields) {
        Bin bin = findBinInDB(id);
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Bin.class, (String) key);
            if (field == null) {
                throw new FileDoesNotExistException("Field " + key + " does not exist");
            }
            field.setAccessible(true);
            ReflectionUtils.setField(field, bin, value);
        });
        binRepository.save(bin);
        return BinConverter.convertToBinDto(bin);
    }

    @Transactional
    public void deleteBinById(@PathVariable("id") int id) {
        if (binRepository.existsById(id)) {
            binRepository.deleteById(id);
        }
    }
}
