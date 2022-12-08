package com.diy.stater.interceptor;

import com.diy.stater.autoconfigure.MybatisDivProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: levi
 * @description: TODO
 * @date: 2022-9-24 18:35
 * @version: 1.0
 */
@Intercepts(@Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class,
                Object.class,
                RowBounds.class,
                ResultHandler.class}))
@Component
@Slf4j
public class MybatisDivInterceptor implements Interceptor {
    @Resource
    private MybatisDivProperties mybatisDivProperties;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        boolean proceedFlag = false;
        Object obj = null;
        long durationTime = 0L;
        long startTime = System.currentTimeMillis();
        try {
            obj = invocation.proceed();
            // 记录耗时
            durationTime = System.currentTimeMillis() - startTime;
            proceedFlag = true;
            return obj;
        } finally {
            if (proceedFlag) {
                if (durationTime > mybatisDivProperties.getMonitorTimeThreshold() * 1000) {
                    log.info("********************该SQL超时了********************");
                    MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
                    Object param = invocation.getArgs()[1];
                    BoundSql boundSql = ms.getBoundSql(param);
                    String oldSql = boundSql.getSql();
                    log.info("SQL:" + oldSql);
                    log.info("执行时间:{}ms", durationTime);
                }
            } else {
                log.info("******************************监控寄了******************************");
            }
        }
    }

}
