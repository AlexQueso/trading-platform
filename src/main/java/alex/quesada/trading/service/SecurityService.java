package alex.quesada.trading.service;

import alex.quesada.trading.controller.dto.SecurityRequest;
import alex.quesada.trading.controller.dto.SecurityResponse;
import alex.quesada.trading.domain.Security;
import alex.quesada.trading.exception.SecurityNotFoundException;
import alex.quesada.trading.infrastructure.SecurityRepository;
import alex.quesada.trading.service.mapper.SecurityMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Log4j2
public class SecurityService {

    private final SecurityRepository securityRepository;

    private final SecurityMapper securityMapper;

    public SecurityService(SecurityRepository securityRepository, SecurityMapper securityMapper) {
        this.securityRepository = securityRepository;
        this.securityMapper = securityMapper;
    }

    public Optional<SecurityResponse> getSecurityResponseById(String securityId) {
        log.info("Retrieving security with id {} from database... ", securityId);
        return securityRepository.findById(securityId)
                .map(securityMapper::securityToSecurityResponse);
    }

    public Security getSecurityById(String securityId) {
        log.info("Retrieving security with id {} from database... ", securityId);
        try {
            return securityRepository.findById(securityId).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new SecurityNotFoundException("Security with id " + securityId + " was not found.");
        }
    }

    public List<SecurityResponse> getAll() {
        log.info("Retrieving all securities from database... ");
        return securityRepository.findAll().stream()
                .map(securityMapper::securityToSecurityResponse)
                .toList();
    }

    public SecurityResponse saveNewSecurity(SecurityRequest securityRequest) {
        log.info("Saving new Security: {}...", securityRequest);
        Security security = securityMapper.securityRequestToSecurity(securityRequest);
        return securityMapper.securityToSecurityResponse(securityRepository.save(security));
    }
}
