package ru.hogwarts.schoollion.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!production")
public class InfoServiceTest implements InfoService{

    Logger logger = LoggerFactory.getLogger(InfoServiceTest.class);

    private Integer portForTest;

    @Override
    public Integer getPort() {
        logger.debug("Called method: public Integer getPort() ");
        return portForTest = 9999;
    }
}
