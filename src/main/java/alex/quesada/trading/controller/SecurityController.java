package alex.quesada.trading.controller;

import alex.quesada.trading.controller.dto.SecurityRequest;
import alex.quesada.trading.controller.dto.SecurityResponse;
import alex.quesada.trading.service.SecurityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/securities/")
public class SecurityController {

    private final SecurityService securityService;

    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("{securityId}")
    public ResponseEntity<SecurityResponse> getSecurityById(@PathVariable("securityId") String securityId) {
        return ResponseEntity.ok(securityService.getSecurityResponseById(securityId));
    }

    @GetMapping
    public List<SecurityResponse> getAllSecurities() {
        return securityService.getAll();
    }

    @PostMapping
    public ResponseEntity<SecurityResponse> saveNewSecurity(@RequestBody SecurityRequest security) {
        return ResponseEntity.ok(securityService.saveNewSecurity(security));
    }

    // PUT, DELETE ...
}
