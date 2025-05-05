package agus.ramdan.cdt.pjpur.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Configuration
@RequiredArgsConstructor
public class RestClientConfig {

    @Bean
    public RestClient restClient(RestClient.Builder builder,PJPURConfig pjpurConfig) {
        return builder
                .requestFactory(new HttpComponentsClientHttpRequestFactory())
                .baseUrl(pjpurConfig.getBaseUrl())
                .defaultHeader("X-MB-account", pjpurConfig.getAccount())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/jwt")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public PrivateKey privateKey(PJPURConfig pjpurConfig) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String pem = pjpurConfig.getPrivateKey(); // your PEM content
        pem = pem.replaceAll("-----.*-----", "").replaceAll("\\s", "");
        byte[] der = Base64.getDecoder().decode(pem);

        // Now decode using KeyFactory
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(der);
        KeyFactory kf = KeyFactory.getInstance("EC");
        return kf.generatePrivate(keySpec);
    }
}
