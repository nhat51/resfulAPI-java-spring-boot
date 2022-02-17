package com.example.restfulapi.queue;

import com.example.restfulapi.entity.Order;
import com.example.restfulapi.entityDTO.OrderDto;
import com.example.restfulapi.repository.OrderRepository;
import com.example.restfulapi.status.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import java.util.Optional;

import static com.example.restfulapi.queue.Config.DIRECT_EXCHANGE;

@Component
public class ConsumerService {
    @Autowired
    OrderRepository orderRepository;

    public void handleMessage(OrderDto orderDto){
        Optional<Order> order = orderRepository.findById(orderDto.getOrderId());
        if (order.isPresent()){
            order.get().setPayment_status(orderDto.getPaymentStatus());
            order.get().setStatus(orderDto.getOrderStatus());
        }
        orderRepository.save(order.get());

    }
}
