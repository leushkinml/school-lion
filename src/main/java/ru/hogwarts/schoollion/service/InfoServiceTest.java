package ru.hogwarts.schoollion.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;

@Service
@Profile("!production")
public class InfoServiceTest implements InfoService{

    Logger logger = LoggerFactory.getLogger(InfoServiceTest.class);

    private String portForTest;
    @Override
    public String getPortInString() throws UnknownHostException {
        logger.debug("Called method: public String getPortInString() ");
        return portForTest = "999.999.9.999 TEST";
    }
}
