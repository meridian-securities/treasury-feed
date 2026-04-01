package com.meridian.treasuryfeed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TradeListener {

    private static final Logger log = LoggerFactory.getLogger(TradeListener.class);

    @Autowired
    private TreasuryPublisher treasuryPublisher;

    @JmsListener(destination = "TRADE.MATCHED")
    public void onMatchedTrade(String tradeJson) {
        log.info("Received matched trade from TRADE.MATCHED queue");
        // In production this would extract cash flow and funding requirements from the trade
        String feedJson = "{\"treasuryFeed\":true,\"source\":" + tradeJson + "}";
        treasuryPublisher.publishTreasuryFeed(feedJson);
    }
}
