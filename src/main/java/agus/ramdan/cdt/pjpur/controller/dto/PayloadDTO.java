package agus.ramdan.cdt.pjpur.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PayloadDTO {
    private Object data;
    private String timestamp;
}
