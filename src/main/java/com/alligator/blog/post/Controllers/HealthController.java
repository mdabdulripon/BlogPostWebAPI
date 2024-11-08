package com.alligator.blog.post.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {
    private final Environment _environment;

    @Autowired
    public HealthController(Environment environment) {
        _environment = environment;
    }

    @GetMapping
    public Map<String, String> health() {
        Map<String, String> response = new HashMap<>();
        response.put("application", "blog post web api");
        response.put("status", "healthy");
        response.put("port", _environment.getProperty("local.server.port"));
        return response;
    }

}
