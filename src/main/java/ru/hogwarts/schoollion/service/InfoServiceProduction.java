package ru.hogwarts.schoollion.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("production")
public class InfoServiceProduction implements InfoService{

    Logger logger = LoggerFactory.getLogger(InfoServiceProduction.class);

    @Value("${server.port}")
    private Integer port;

    @Override
    public Integer getPort() {
      logger.debug("Called method: public Integer getPort() ");
      return port;
    }

}
