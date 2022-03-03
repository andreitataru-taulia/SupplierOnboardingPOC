package com.taulia.supplier.onboarding.common.elasticsearch

//import org.elasticsearch.client.RestHighLevelClient
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.data.elasticsearch.client.ClientConfiguration
//import org.springframework.data.elasticsearch.client.RestClients
//import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration
//
//import java.time.Duration
//
//@Configuration
//class ElasticsearchConfig extends AbstractElasticsearchConfiguration {
//
//    @Bean
//    @Override
//    RestHighLevelClient elasticsearchClient() {
//        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo("localhost:9200")
//                .usingSsl()
//                .withConnectTimeout(Duration.ofSeconds(5))
//                .withSocketTimeout(Duration.ofSeconds(3))
//                .build()
//
//        //http://localhost:9200
//
//        return RestClients.create(clientConfiguration).rest()
//    }
//}
