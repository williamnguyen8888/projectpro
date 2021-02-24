package com.william.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class HomeRestController {
    @GetMapping("/timeNow")
    public String timeNow(){
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy"));
    }
}
