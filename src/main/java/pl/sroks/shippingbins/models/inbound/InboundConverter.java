package pl.sroks.shippingbins.models.inbound;

import java.util.List;
import java.util.stream.Collectors;

public class InboundConverter {

    public static InboundDto convertToInboundDto(Inbound inbound) {
        if (inbound == null) return null;
        return InboundDto.builder()
                .id(inbound.getId())
                .incomingType(inbound.getIncomingType())
                .quantity(inbound.getQuantity())
                .location(inbound.getLocation())
                .arrived(inbound.getArrived())
                .bin(inbound.getBin())
                .truck(inbound.getTruck())
                .supplier(inbound.getSupplier())
                .build();
    }

    public static Inbound convertToInbound(InboundDto inboundDto) {
        if (inboundDto == null) return null;
        return Inbound.builder()
                .id(inboundDto.getId())
                .incomingType(inboundDto.getIncomingType())
                .quantity(inboundDto.getQuantity())
                .location(inboundDto.getLocation())
                .arrived(inboundDto.getArrived())
                .bin(inboundDto.getBin())
                .truck(inboundDto.getTruck())
                .supplier(inboundDto.getSupplier())
                .build();
    }

    public static List<Inbound> convertInboundDtoListToInboundList(List<InboundDto> inboundDtoList) {
        return inboundDtoList.stream()
                .map(InboundConverter::convertToInbound)
                .collect(Collectors.toList());
    }

    public static List<InboundDto> convertInboundListToInboundDtoList(List<Inbound> inboundList) {
        return inboundList.stream()
                .map(InboundConverter::convertToInboundDto)
                .collect(Collectors.toList());
    }
}
