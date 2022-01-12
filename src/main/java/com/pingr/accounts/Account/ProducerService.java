package com.pingr.accounts.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    @Value(value = "${topic.accounts}")
    private String topic;

    @Value(value = "${topic.update-accounts}")
    private String topicUpdate;

    @Value(value = "${topic.delete-accounts}")
    private String topicDelete;

    @Autowired
    private KafkaTemplate<String, Object> template;

    public void sendMessage(Account message) {
        this.template.send(this.topic, message);
    }

    public void sendUpdateMessage(Account message) {
        this.template.send(this.topicUpdate, message);
    }

    public void sendDeleteMessage(Account message) {
        this.template.send(this.topicDelete, message);
    }
}

