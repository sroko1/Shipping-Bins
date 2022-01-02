package pl.sroks.shippingbins.models.supplier;

import java.util.List;
import java.util.stream.Collectors;

public class SupplierConverter {

    public static SupplierDto convertToSupplierDto(Supplier supplier) {
        if (supplier == null) return null;
        return SupplierDto.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .build();

    }
    public static Supplier convertToSupplier(SupplierDto supplierDto) {
        if (supplierDto == null) return null;
        return Supplier.builder()
                .id(supplierDto.getId())
                .name(supplierDto.getName())
                .build();
    }

    public static List<Supplier> convertSupplierListDtoToSupplierList(List<SupplierDto> supplierDtoList) {
        return supplierDtoList.stream()
                .map(SupplierConverter::convertToSupplier)
                .collect(Collectors.toList());
    }

    public static List<SupplierDto> convertSupplierListToSupplierDtoList(List<Supplier> supplierList) {
        return supplierList.stream()
                .map(SupplierConverter::convertToSupplierDto)
                .collect(Collectors.toList());
    }
}
