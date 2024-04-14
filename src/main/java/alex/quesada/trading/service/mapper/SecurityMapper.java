package alex.quesada.trading.service.mapper;

import alex.quesada.trading.controller.dto.SecurityRequest;
import alex.quesada.trading.controller.dto.SecurityResponse;
import alex.quesada.trading.domain.Security;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SecurityMapper {

    SecurityResponse securityToSecurityResponse(Security security);

    Security securityRequestToSecurity(SecurityRequest securityRequest);
}
