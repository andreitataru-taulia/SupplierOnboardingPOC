package com.taulia.supplier.onboarding.common.db

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import groovy.util.logging.Slf4j
import lombok.AccessLevel
import lombok.NoArgsConstructor

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class JsonHelper {
    private static final ObjectMapper MAPPER = new ObjectMapper()

    static String toJson(Object object) {
        try {
            return MAPPER.writeValueAsString(object)
        } catch (JsonProcessingException e) {
            log.error("toJson: could not convert object to json string", e)
            return null
        }
    }

    static <T> T fromJson(String jsonString, Class<T> clazz) {
        try {
            return MAPPER.readValue(jsonString, clazz)
        } catch (IOException e) {
            log.error("fromJson: could not convert json string to object", e)
            return null
        }
    }
}
