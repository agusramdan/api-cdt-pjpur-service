package agus.ramdan.cdt.pjpur.controller.command;

import agus.ramdan.base.dto.pjpur.ResponseDTO;
import agus.ramdan.base.dto.pjpur.deposit.DepositDTO;
import agus.ramdan.cdt.pjpur.service.deposit.DepositService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
//@Service
//@EnableCaching
@RestController
@RequestMapping("/api/cdt/core/pjpur/deposit/command")
@RequiredArgsConstructor
public class DepositCommandController {

    private final DepositService service;
    @PostMapping("")
    public ResponseEntity<ResponseDTO> create(
            @RequestBody DepositDTO dto,
            @RequestHeader HttpHeaders headers) {
        log.info("pjpur deposit");
        return ResponseEntity.ok(service.deposit(dto));
    }
}