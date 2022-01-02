package pl.sroks.shippingbins.models.bin;

import java.util.List;
import java.util.stream.Collectors;

public class BinConverter {

    public static BinDto convertToBinDto(Bin bin) {
        if (bin == null) return null;
        return BinDto.builder()
                .id(bin.getId())
                .name(bin.getName())
                .width(bin.getWidth())
                .length(bin.getLength())
                .height(bin.getHeight())
                .price(bin.getPrice())
                .amount(bin.getAmount())
                .volume(bin.getVolume())
                .leasingPrice(bin.getLeasingPrice())
                .build();
    }
    public static Bin convertToBin(BinDto binDto) {
        if (binDto == null) return null;
        return Bin.builder()
                .id(binDto.getId())
                .name(binDto.getName())
                .width(binDto.getWidth())
                .length(binDto.getLength())
                .height(binDto.getHeight())
                .price(binDto.getPrice())
                .amount(binDto.getAmount())
                .volume(binDto.getVolume())
                .leasingPrice(binDto.getLeasingPrice())
                .build();
    }
    public static List<Bin> convertBinDtoListToBinList(List<BinDto> binDtoList) {
        return binDtoList.stream()
                .map(BinConverter::convertToBin)
                .collect(Collectors.toList());
    }

    public static List<BinDto> convertBinListToBinDtoList(List<Bin> binList) {
        return binList.stream()
                .map(BinConverter::convertToBinDto)
                .collect(Collectors.toList());
    }
}
