package pl.sroks.shippingbins.controllers.bin;


import org.springframework.web.bind.annotation.*;
import pl.sroks.shippingbins.models.bin.BinDto;
import pl.sroks.shippingbins.service.BinService;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/bin")

public class BinController {
    private final BinService binService;

    public BinController(BinService binService) {
        this.binService = binService;
    }

    @GetMapping("/list")
    public List<BinDto> getBins() {
        return binService.getBinList();
    }

    @GetMapping("/{id}")
    public BinDto getBin(@PathVariable int id) {
        return binService.findBinById(id);
    }

    @PostMapping("/createBin")
    public BinDto createBin(@RequestBody BinDto binDto) {
        return binService.createNewBin(binDto);
    }

    @PatchMapping("/{id}/updateBin")
    public BinDto updateBin(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        return binService.updateBin(id, fields);
    }

    @DeleteMapping("/{id}/deleteBin")
    public void deleteBin(@PathVariable int id) {
        binService.deleteBinById(id);
    }
}
