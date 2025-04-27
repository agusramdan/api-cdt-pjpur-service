package agus.ramdan.cdt.pjpur.controller.command;

import agus.ramdan.base.dto.pjpur.ResponseDTO;
import agus.ramdan.base.dto.pjpur.collect.CollectDTO;
import agus.ramdan.cdt.pjpur.service.collect.CollectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
//@Service
//@EnableCaching
@RestController
@RequestMapping("/api/cdt/core/pjpur/collect/command")
@RequiredArgsConstructor
public class CollectCommandController {

    private final CollectService service;
    @PostMapping("")
    public ResponseEntity<ResponseDTO> create(
            @RequestBody CollectDTO dto,
            @RequestHeader HttpHeaders headers) {
        log.info("pjpur collect: {}", dto);
        return ResponseEntity.ok(service.collect(dto));
    }
}