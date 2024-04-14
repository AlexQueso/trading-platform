package alex.quesada.trading.service;

import alex.quesada.trading.controller.dto.SecurityRequest;
import alex.quesada.trading.controller.dto.SecurityResponse;
import alex.quesada.trading.domain.Security;
import alex.quesada.trading.infrastructure.SecurityRepository;
import alex.quesada.trading.service.mapper.SecurityMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecurityService {

    private final SecurityRepository securityRepository;

    private final SecurityMapper securityMapper;

    public SecurityService(SecurityRepository securityRepository, SecurityMapper securityMapper) {
        this.securityRepository = securityRepository;
        this.securityMapper = securityMapper;
    }

    public Optional<SecurityResponse> getSecurityById(String securityId) {
        return securityRepository.findById(securityId)
                .map(securityMapper::securityToSecurityResponse);
    }

    public List<SecurityResponse> getAll() {
        return securityRepository.findAll().stream()
                .map(securityMapper::securityToSecurityResponse)
                .toList();
    }

    public SecurityResponse saveNewSecurity(SecurityRequest securityRequest) {
        Security security = securityMapper.securityRequestToSecurity(securityRequest);
        return securityMapper.securityToSecurityResponse(securityRepository.save(security));
    }
}
