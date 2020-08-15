package com.example.zhongjimall.repository.sys;


import com.example.zhongjimall.entity.sys.RequestLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author longzhonghua
 * @createdata 3/6/2019 8:41 PM
 * @description 会员登录日志
 */
public interface RequestLogRepository extends JpaRepository<RequestLog,Long> {
    RequestLog findById(long id);

}
