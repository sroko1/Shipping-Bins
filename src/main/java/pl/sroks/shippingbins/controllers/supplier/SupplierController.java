package pl.sroks.shippingbins.controllers.supplier;


import org.springframework.web.bind.annotation.*;
import pl.sroks.shippingbins.models.supplier.SupplierDto;
import pl.sroks.shippingbins.service.SupplierService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/supplier")
@CrossOrigin("http://localhost:4200")
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/list")
    public List<SupplierDto> getSuppliers() {
        return supplierService.getSupplierList();
    }

    @GetMapping("/{id}")
    public SupplierDto getSupplier(@PathVariable int id) {
        return supplierService.findSupplierById(id);
    }

    @PostMapping("/createSupplier")
    public SupplierDto createSupplier(@RequestBody SupplierDto supplierDto) {
        return supplierService.createNewSupplier(supplierDto);
    }

    @PatchMapping("{id}/updateSupplier")
    public SupplierDto updateTruck(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        return supplierService.updateSupplier(id, fields);
    }

    @DeleteMapping("{id}/deleteSupplier")
    public void deleteSupplier(@PathVariable int id) {
        supplierService.deleteSupplierById(id);
    }
}

