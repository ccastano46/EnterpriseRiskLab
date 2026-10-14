package eci.arem.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RiskController {
    @GetMapping("/risk-status")
    public Map<String, Object> riskStatus(@AuthenticationPrincipal Jwt jwt) {
        return Map.of(
                "status", "protected",
                "subject", jwt.getSubject(),
                "control", "JWT validation and authenticated access");
    }
}