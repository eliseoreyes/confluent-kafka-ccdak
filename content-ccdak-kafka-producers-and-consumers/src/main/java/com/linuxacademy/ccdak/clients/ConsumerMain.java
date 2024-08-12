package com.linuxacademy.ccdak.clients;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ConsumerMain {

    public static void main(String[] args) {
    	
	Properties prop = new Properties();
	prop.setProperty("bootstrap.servers", "localhost:9092");
	prop.setProperty("group.id", "group1");
	prop.setProperty("enable.auto.commit", "false");
	prop.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	prop.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

	KafkaConsumer<String, String> consumer = new KafkaConsumer<>(prop);

	consumer.subscribe(Arrays.asList("test-topic1", "test-topic2"));

	while(true){
	   
	  ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
	  
	  for (ConsumerRecord<String, String> record : records){
	  	System.out.println("key=" + record.key() +
			           ", value=" +record.value() + 
				   ", topic=" +record.topic() + 
				   ", partition=" +record.partition() + 
				   ", offset=" +record.offset());
	  }
	   consumer.commitSync();
	}
    }

}
