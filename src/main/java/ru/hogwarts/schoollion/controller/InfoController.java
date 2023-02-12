package ru.hogwarts.schoollion.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.schoollion.service.InfoService;

import java.net.UnknownHostException;

@RestController
@RequestMapping("info")
public class InfoController {

    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/get-port")
    public String getPort() {
        return "This port is currently enabled: " + infoService.getPort();
    }
}
