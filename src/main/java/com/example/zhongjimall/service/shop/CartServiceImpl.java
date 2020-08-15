package com.example.zhongjimall.service.shop;



import com.example.zhongjimall.entity.Cart;
import com.example.zhongjimall.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@CacheConfig(cacheNames = "cart")
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public List<Cart> getCartList() {
        return cartRepository.findAll();
    }

    @Override
    public Cart findCartById(long id) {
        return cartRepository.findByid(id);
    }


}
