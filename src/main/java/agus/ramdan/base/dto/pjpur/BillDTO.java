package agus.ramdan.base.dto.pjpur;

import lombok.Data;

import java.math.BigDecimal;

/**
 * denom Numeric M Denom
 * qty Numeric M Bill count
 */
@Data
public class BillDTO {
    private BigDecimal denom;
    private Integer qty;
}
