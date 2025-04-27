package agus.ramdan.cdt.pjpur.service.verify;

import agus.ramdan.base.exception.Errors;
import agus.ramdan.base.exception.XxxException;
import agus.ramdan.base.dto.pjpur.ResponseDTO;
import agus.ramdan.cdt.pjpur.config.PJPURConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
@Log4j2
public class VerifyService {
    private final RestClient restClient;
    private final PJPURConfig pjpurConfig;
    private final PrivateKey privateKey;
    private final ObjectMapper objectMapper;

    public ResponseDTO verify(String str) {
        String url = pjpurConfig.getBaseUrl() + pjpurConfig.getVerify().getPath();
        Map<String,Object> header = Map.of(
                "account", pjpurConfig.getAccount(),
                "jwt","JWT"
        );
        val body = new HashMap<String,Object>();
        body.put("data", Map.of("trxrefno",str));
        String payload;
        try {
            payload = objectMapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            log.error("Error create: {}", e.getMessage());
            throw new XxxException("Error transferCreate", 500, null, null, new Errors("Error create", e.getMessage()));
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
                .body(token)
                .exchange((request, response) -> {
                    try {
                        return objectMapper.readValue(response.getBody(), ResponseDTO.class);
                    } catch (JsonProcessingException e) {
                        log.error("Error create: {}", e.getMessage());
                        throw new XxxException("Error transferCreate", response.getStatusCode().value(), null, null, new Errors("Error create", e.getMessage()));
                    }

                });
    }
}
