package com.jujospring.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.jujospring.dto.Customer;

@Service
public class KafkaMessagePublisher {
  @Autowired
  private KafkaTemplate<String,Object> template;

  @SuppressWarnings("null")
  public void sendMessageToTopic(String message) {
    // CompletableFuture<SendResult<String, Object>> future = template.send("jujospring-topic8", 3, null, message);
    // future.whenComplete((result, ex)->{
    //   // result.getRecordMetadata().partition();
    //   if (ex == null) {
    //     System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
    //   } else {
    //     System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
    //   }
    // });

    template.send("jujospring-topic8", 3, null, "hi");
    template.send("jujospring-topic8", 1, null, "hello");
    template.send("jujospring-topic8", 2, null, "bug");
    template.send("jujospring-topic8", 2, null, "jones");
    template.send("jujospring-topic8", 0, null, "welcome");

  }

  public void sendEventsToTopic(Customer customer) {
    try {
      CompletableFuture<SendResult<String, Object>> future = template.send("jujospring-topic8", customer);
      future.whenComplete((result, ex)->{
        // result.getRecordMetadata().partition();
        if (ex == null) {
          System.out.println("Sent event=[" + customer.toString() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
        } else {
          System.out.println("Unable to send event=[" + customer.toString() + "] due to : " + ex.getMessage());
        }
      });
    } catch (Exception ex) {
      System.out.println("ERROR: " + ex.getMessage());
    }
  }
}
