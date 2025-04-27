package agus.ramdan.base.dto.pjpur.collect;

import agus.ramdan.base.dto.pjpur.BillDTO;
import lombok.Data;

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
public class CollectDTO {
    private String terminalid;
    private String timestamp;
    private String oprid;
    private Integer batch;
    private List<BillDTO> bills;
}
