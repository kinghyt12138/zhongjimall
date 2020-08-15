package com.example.zhongjimall.controller.shop;


import com.example.zhongjimall.entity.Order;
import com.example.zhongjimall.entity.Product;
import com.example.zhongjimall.repository.OrderRepository;
import com.example.zhongjimall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/createOrder")
    public String createOrder(Order order) throws Exception {
        Product p = productRepository.findByid(order.getProduct_id());
        order.setStatus(true);
        //价格信息要从库中获取,不然黑客伪造订单,购买虚拟产品就会自动发货
        //便于演示,这里获取的是一个产品的价格
        //对于多个产品价格叠加读者自行编写
        order.setAmount(p.getPrice());
        orderRepository.save(order);
        //获取保存后的数据表自增id
        Long order_id = order.getId();
        //传递给支付页面值
        return "redirect:/pay/?order_id=" + order_id;
    }
}
