package ru.hogwarts.schoollion.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
@Profile("production")
public class InfoServiceProduction implements InfoService{

    Logger logger = LoggerFactory.getLogger(InfoServiceProduction.class);

    private String port;

    public String getPortInString() throws UnknownHostException {
        logger.debug("Called method: public String getPortInString() ");

        return port = InetAddress.getLocalHost().getHostAddress();
    }

//    @Value("${local.server.port}")
//    @LocalServerPort
//    private Integer port;
//
//    public Integer getPort() {
//        logger.debug("Called method: public Integer getPort() ");
//        return port;
//    }

}
