package pl.sroks.shippingbins.models.truck;


import java.util.List;
import java.util.stream.Collectors;

public class TruckConverter {

    public static TruckDto convertToTruckDto(Truck truck) {
        if (truck == null) return null;
        return TruckDto.builder()
                .id(truck.getId())
                .regNumber(truck.getRegNumber())
                .trailerType(truck.getTrailerType())
                .trailerMaxVolume(truck.getTrailerMaxVolume())
                .build();
    }

    public static Truck convertToTruck(TruckDto truckDto) {
        if (truckDto == null) return null;
        return Truck.builder()
                .id(truckDto.getId())
                .regNumber(truckDto.getRegNumber())
                .trailerType(truckDto.getTrailerType())
                .trailerMaxVolume(truckDto.getTrailerMaxVolume())
                .build();
    }

    public static List<Truck> convertTruckDtoListToTruckList(List<TruckDto> truckDtoList) {
        return truckDtoList.stream()
                .map(TruckConverter::convertToTruck)
                .collect(Collectors.toList());
    }

    public static List<TruckDto> convertTruckListToTruckDtoList(List<Truck> truckList) {
        return truckList.stream()
                .map(TruckConverter::convertToTruckDto)
                .collect(Collectors.toList());
    }
}
