package agus.ramdan.cdt.pjpur.controller.query;

import agus.ramdan.base.dto.pjpur.ResponseDTO;
import agus.ramdan.cdt.pjpur.service.verify.VerifyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/cdt/core/pjpur/verify/query")
@RequiredArgsConstructor
public class VerifyQueryController {
    private final VerifyService service;
    @GetMapping("/{referenceId}")
    public ResponseEntity<ResponseDTO> getByReferenceId(@PathVariable String referenceId) {
        return ResponseEntity.ok(service.verify(referenceId));
    }
}