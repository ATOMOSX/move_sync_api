package com.movesync.move_sync_api.infrastructurecross.config;

import com.movesync.move_sync_api.infrastructurecross.Constants;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> builder
                .simpleDateFormat(Constants.DATE_TIME_PATTERN)
                .timeZone(Constants.DEFAULT_TIMEZONE);
    }
}
