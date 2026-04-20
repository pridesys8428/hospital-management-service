package com.example.hospitalmanagement.controllers;

import com.example.hospitalmanagement.model.AiQueryRequest;
import com.example.hospitalmanagement.repository.DynamicQueryRepository;
import com.example.hospitalmanagement.services.AiQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/ai")
public class AiQueryController {

    @Autowired
    private AiQueryService aiQueryService;

    @Autowired
    private DynamicQueryRepository dynamicQueryRepository;

    @PostMapping("/query")
    public ResponseEntity<?> processNaturalLanguage(@RequestBody AiQueryRequest request) {
        try {
            String sql = aiQueryService.generateSQLFromPrompt(request.getPrompt());
            Matcher matcher = Pattern.compile("(SELECT\\s+.*?;)").matcher(sql);
            if (matcher.find()) {
                sql = matcher.group(1);
            }
            List<Map<String, Object>> results = dynamicQueryRepository.executeSelect(sql);
            return ResponseEntity.ok(Map.of(
                    "query", sql,
                    "data", results
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

}
