package com.nitesh.springBootApacheCamel.routes.c;

import java.util.Date;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class KafkaRoute extends RouteBuilder{

    final String KAFKA_ENDPOINT = "kafka:%s?brokers=localhost:9092";

    @Override
    public void configure() throws Exception {
        fromF(KAFKA_ENDPOINT, "stock-live")
                .log(LoggingLevel.ERROR, "[${header.kafka.OFFSET}] [${body}]")
                .bean(StockPriceEnricher.class)
                .toF(KAFKA_ENDPOINT, "stock-audit")
        ;
    }

    private class StockPriceEnricher {
        public String enrichStockPrice(String stockPrice) {
            return stockPrice + "," + new Date();
        }
    }
}
