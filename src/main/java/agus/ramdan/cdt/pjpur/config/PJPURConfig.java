package agus.ramdan.cdt.pjpur.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "pjpur")
@Log4j2
public class PJPURConfig {
    private String baseUrl;
    private String account;
    private String privateKey;

    //PAYMENT_GATEWAY_CALLBACK_ENDPOINT
    private String callbackEndpoint;


    private ApiUrlConfig collect;
    private ApiUrlConfig deposit;
    private ApiUrlConfig verify;



    private LocalTransferBalanceConfig localTransfer;

    @Getter
    @Setter
    public static class ServiceConfig {
        private ApiUrlConfig checkAccountInquiry;
        private TransferBalanceConfig transferBalance;
    }
    @Getter
    @Setter
    public static class ApiUrlConfig {
        private String path;
        private String oprid;
    }
    @Getter
    @Setter
    public static class LocalTransferBalanceConfig {
        private String path;
        private Integer balanceId;
    }

    @Getter
    @Setter
    public static class TransferBalanceConfig {
        private String apiUrl;
        /**
         * 0 = Transfer Online
         * 1 = BI Fast Transfer
         */
        private String transferType = "1";
    }
}


