package com.meridian.treasuryfeed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class TreasuryPublisher {

    private static final Logger log = LoggerFactory.getLogger(TreasuryPublisher.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void publishTreasuryFeed(String feedJson) {
        log.info("Publishing treasury feed to TREASURY.FEED queue");
        jmsTemplate.convertAndSend("TREASURY.FEED", feedJson);
        log.info("Treasury feed published successfully");
    }
}
