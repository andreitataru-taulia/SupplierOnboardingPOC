package com.taulia.supplier.onboarding.common.kafka
//@Configuration
//@EnableKafka
//@EnableKafkaStreams
//class KafkaConfig {
//
//    @Value(value = "${spring.kafka.bootstrap-servers}")
//    private String bootstrapAddress;
//
//    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
//    KafkaStreamsConfiguration kStreamsConfig() {
//        Map<String, Object> props = new HashMap<>()
//        props.put(APPLICATION_ID_CONFIG, "streams-app")
//        props.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress)
//        props.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName())
//        props.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName())
//
//        return new KafkaStreamsConfiguration(props);
//    }
//
//    // other config
//}
