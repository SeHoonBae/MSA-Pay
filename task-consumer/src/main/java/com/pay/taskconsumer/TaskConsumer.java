package com.pay.taskconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pay.common.RechargingMoneyTask;
import com.pay.common.SubTask;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Component
public class TaskConsumer {

    private final KafkaConsumer<String, String> consumer;

    private final TaskResultProducer taskResultProducer;

    public TaskConsumer(@Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
                        @Value("${task.topic}") String topic,
                        TaskResultProducer taskResultProducer){
        this.taskResultProducer = taskResultProducer;

        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);

        // consumer group
        props.put("group.id", "my-group");

        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        this.consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Collections.singletonList(topic));

        Thread consumerThread = new Thread(() -> {
            try {
                while(true){
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                    ObjectMapper mapper = new ObjectMapper();

                    for(ConsumerRecord<String, String> record : records){
                        // record: RechargingMoneyTask (jsonString)
                        RechargingMoneyTask task;

                        // task run
                        try {
                            task = mapper.readValue(record.value(), RechargingMoneyTask.class);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }

                        // task result 판단해서
                        for(SubTask subTask : task.getSubTaskList()){

                            // 어떤 subtask인지 판단해서, membership, banking
                            // external port, adapter를 통해서 아키텍처를 만들어주는게 맞으나 여기서는 pass
                            subTask.setStatus("success");
                        }

                        // produce TaskResult
                        this.taskResultProducer.sendTaskResult(task.getTaskID(), task);

                    }
                }
            } finally {
                consumer.close();
            }
        });
        consumerThread.start();
    }


}
