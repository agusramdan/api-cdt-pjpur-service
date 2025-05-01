package agus.ramdan.cdt.pjpur.service.deposit;

import agus.ramdan.base.exception.Errors;
import agus.ramdan.base.exception.XxxException;
import agus.ramdan.base.dto.pjpur.ResponseDTO;
import agus.ramdan.base.dto.pjpur.deposit.DepositDTO;
import agus.ramdan.cdt.pjpur.config.PJPURConfig;
import agus.ramdan.cdt.pjpur.controller.dto.PayloadDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.security.PrivateKey;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@AllArgsConstructor
@Log4j2
public class DepositService {
    private final RestClient restClient;
    private final PJPURConfig pjpurConfig;
    private final PrivateKey privateKey;
    private final ObjectMapper objectMapper;

    public ResponseDTO deposit(DepositDTO depositDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssSSSXXX");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Jakarta"));
        String timestamp =zonedDateTime.format(formatter);
        String url = pjpurConfig.getBaseUrl() + pjpurConfig.getDeposit().getPath();
        Map<String,Object> header = Map.of(
                "account", pjpurConfig.getAccount(),
                "jwt","JWT"
        );
        if (depositDTO.getOprid() == null) {
            depositDTO.setOprid(pjpurConfig.getDeposit().getOprid());
        }
        if (depositDTO.getSrcacc() == null) {
            depositDTO.setSrcacc(pjpurConfig.getDeposit().getSrcacc());
        }
        String payload;
        try {
            payload = objectMapper.writeValueAsString(PayloadDTO.builder()
                    .data(depositDTO)
                    .timestamp(timestamp)
                    .build()
            );
        } catch (JsonProcessingException e) {
            log.error("Error create: {}", e.getMessage());
            throw new XxxException("Error deposit", 500, null, null, new Errors("Error create", e.getMessage()));
        }
        String token = Jwts.builder()
                .setHeader(header)
                .setPayload(payload)
                // RS256 with privateKey
                .signWith(SignatureAlgorithm.ES256, privateKey).compact();
        log.info("Request deposit token: {}", token);
        return restClient
                .post()
                .uri(url)
                .header("X-MB-timestamp", timestamp)
                .body(token)
                .exchange((request, response) -> {
                    try {
                        return objectMapper.readValue(response.getBody(), ResponseDTO.class);
                    } catch (JsonProcessingException e) {
                        log.error("Error create: {} {}", response.getStatusText(),e.getMessage());
                        throw new XxxException("Error deposit : "+response.getStatusText(), response.getStatusCode().value(), null, null, new Errors("Error create",token));
                    }
                });
    }
}
