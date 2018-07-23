package com.example.democlient.democlient.controller;


import com.example.democlient.democlient.domain.Uttag;
import com.example.democlient.democlient.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ClientController {

    @Autowired
    @Qualifier("demoClientServiceImplMysql")
    private ClientService clientServiceMysql;

    @Autowired
    @Qualifier("demoClientServiceImpl")
    private ClientService clientService;

    @GetMapping("/enabled")
    public boolean enableClient(@RequestParam("value") boolean enabled) {
        return this.clientService.enableClient(enabled);
    }


    @GetMapping("/enabledmysql")
    public boolean enableClientMysql(@RequestParam("value") boolean enabled) {
        return this.clientServiceMysql.enableClient(enabled);
    }

    @GetMapping("/cleardb")
    public boolean cleanSystem(@RequestParam("clean") boolean clean) {
        return this.clientService.cleanServer(clean);
    }

    @GetMapping("/cleardbmysql")
    public boolean cleanSystemMysql(@RequestParam("clean") boolean clean) {
        return this.clientServiceMysql.cleanServer(clean);
    }

}
