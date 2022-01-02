package pl.sroks.shippingbins.controllers.truck;

import org.springframework.web.bind.annotation.*;
import pl.sroks.shippingbins.models.truck.TruckDto;
import pl.sroks.shippingbins.service.TruckService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/truck")
public class TruckController {
    private final TruckService truckService;

    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }

    @GetMapping("/list")
    public List<TruckDto> getTrucks() {
        return truckService.getTruckList();
    }


    @GetMapping("/{id}")
    public TruckDto getTruck(@PathVariable int id) {
        return truckService.findTruckById(id);
    }

    @PostMapping("/createTruck")
    public TruckDto createNewTruck(@Valid @RequestBody TruckDto truckDto) {
        return truckService.createNewTruck(truckDto);
    }

    @PatchMapping("/{id}/updateTruck")
    public TruckDto updateTruck(@RequestBody TruckDto truckDto) {
        return truckService.updateTruck(truckDto);
    }

    @DeleteMapping("/{id}/deleteTruck")
    public void deleteTruck(@PathVariable int id) {
        truckService.deleteTruckById(id);
    }
}