package com.kafka.config;

import com.kafka.enums.Topic;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

    @Bean
    public NewTopic notificationTopic() {
        return TopicBuilder.
                name(Topic.NOTIFICATION_TOPIC.getName())
                .partitions(5)
                .build();
    }

    @Bean
    public NewTopic smsTopic() {
        return TopicBuilder.name(Topic.SMS_TOPIC.getName()).partitions(3).build();
    }
}
