package com.example.restfulapi.service.order;

import com.example.restfulapi.entity.Order;
import com.example.restfulapi.entity.OrderDetail;
import com.example.restfulapi.entity.OrderDetailId;
import com.example.restfulapi.entity.Product;
import com.example.restfulapi.entityDTO.OrderDTO;
import com.example.restfulapi.repository.CustomerRepository;
import com.example.restfulapi.repository.OrderDetailRepository;
import com.example.restfulapi.repository.OrderRepository;
import com.example.restfulapi.repository.ProductRepository;
import com.example.restfulapi.response.ResponseApi;
import com.example.restfulapi.service.product.ProductService;
import com.example.restfulapi.status.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public ResponseApi listOrder() {
        return new ResponseApi(HttpStatus.OK,"success",orderRepository.findAll());
    }

    @Override
    public ResponseApi createOrder(Order order) {
        Order newOrder = new Order();
        newOrder.setShipName(order.getShipName());
        newOrder.setCreated_at(LocalDate.now());
        newOrder.setUpdated_at(order.getUpdated_at());
        newOrder.setShipAddress(order.getShipAddress());
        newOrder.setCustomerId(order.getCustomerId());
        newOrder.setStatus(OrderStatus.PENDING);
        orderRepository.save(newOrder);
        Set<OrderDetail> listOrderDetail = new HashSet<>();
        for (OrderDetail od: order.getOrderDetails()) {
            Product product = productRepository.findById(od.getId().getProductId()).get();

            OrderDetailId key = new OrderDetailId();

            key.setProductId(od.getId().getProductId());
            key.setOrderId(od.getOrderId());

            OrderDetail orderDetail = new OrderDetail();

            orderDetail.setOrder(newOrder);
            orderDetail.setProduct(product);

            orderDetail.setId(key);

            orderDetail.setQuantity(od.getQuantity());
            orderDetail.setUnitPrice(od.getUnitPrice());

            listOrderDetail.add(orderDetail);
            orderDetailRepository.save(orderDetail);
            orderRepository.save(newOrder);
        }
        newOrder.setOrderDetails(listOrderDetail);

        return new ResponseApi(HttpStatus.CREATED,"success", OrderDTO.convertEntityToDTO(newOrder));
    }

    @Override
    public ResponseApi findOrder(int id) {

        return null;
    }

    @Override
    public ResponseApi findOrderByUsername(String username) {
        return null;
    }

    @Override
    public ResponseApi createOrderDetail(OrderDetail orderDetail) {
        return null;
    }

    @Override
    public ResponseApi deleteOrder(int order) {
        return null;
    }
}
