package pl.sroks.shippingbins.controllers.inbound;

import org.springframework.web.bind.annotation.*;
import pl.sroks.shippingbins.models.inbound.InboundDto;
import pl.sroks.shippingbins.service.InboundService;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/inbound")
public class InboundController {
    private final InboundService inboundService;

    public InboundController(InboundService inboundService) {
        this.inboundService = inboundService;
    }

    @GetMapping("/list")
    public List<InboundDto> getInbounds() {
        return inboundService.getInboundList();
    }

    @GetMapping("/{id}")
    public InboundDto getInbound(@PathVariable int id) {
        return inboundService.findInboundById(id);
    }

    @PostMapping("/createInbound")
    public InboundDto createInbound(@RequestBody InboundDto inboundDto) {
        return inboundService.createNewInbound(inboundDto);
    }

    @PatchMapping("/{id}/updateInbound")
    public InboundDto updateInbound(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        return inboundService.updateInbound(id, fields);
    }

    @DeleteMapping("/{id}/deleteInbound")
    public void deleteInbound(@PathVariable int id) {
        inboundService.deleteInboundById(id);
    }
}
