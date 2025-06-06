package agus.ramdan.base.dto.pjpur.deposit;

import agus.ramdan.base.dto.pjpur.BillDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * terminalid String M CRDM unique ID
 * timestamp String M yyyyMMddHHmmSS+XXXX
 * XXXX is the GMT
 * oprid String M CRDM organization depositor id
 * trxrefno String M Transaction Unique Reference Number
 * srcacc String M Source bank account
 * accinfo Object M See Account Info object
 * batch Integer M Current batch no
 * trxid Integer M Transaction sequence id
 * bills Array M Array of Bill Object
 */
@Data
public class DepositDTO {
    private String terminalid;
    private String timestamp;
    private String oprid;
    private String trxrefno;
    private String srcacc;
    private AccountInfoDTO accinfo;
    private Integer batch;
    private BigInteger trxid;
    private List<BillDTO> bills;
}
