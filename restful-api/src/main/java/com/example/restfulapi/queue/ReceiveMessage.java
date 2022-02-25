package com.example.restfulapi.queue;

import com.example.restfulapi.entityDTO.OrderDto;
import com.example.restfulapi.repository.OrderRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.restfulapi.queue.Config.QUEUE_ORDER;
import static com.example.restfulapi.queue.Config.QUEUE_PAYMENT;

@Component
public class ReceiveMessage {

    @Autowired
    ConsumerService consumerService;

    @RabbitListener(queues = {QUEUE_ORDER})
    public void receiveMessage(OrderDto orderDto){
        consumerService.handleMessage(orderDto);
        System.out.println("Đã xử lý order và lưu vào db");
    }
}
