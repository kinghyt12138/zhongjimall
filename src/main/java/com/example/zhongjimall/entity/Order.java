package com.example.zhongjimall.entity;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
//这里不能用order作为MySQL的表名，违背了MySQL表名规则
@Table(name = "orders")
public class Order extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//由数据库控制,auto是程序统一控制
    /**
     * @Description: 主键id
     */
    private Long id;
    //@Column(nullable = false, unique = true)

    /**
     * @Description: 商品id
     */
    private Long product_id;
    /**
     * @Description: 用户id
     */
    private Long user_id;
    /**
     * @Description: 订单金额
     */
    private Float amount;
    /**
    * @Description: 订单状态
    */
   private boolean status;

    /**
     * @Description: 创建时间
     */
    private Long createTime;
    /**
     * @Description: 修改时间
     */
    @LastModifiedDate
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Long updateTime;
}
