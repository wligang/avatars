package com.wlgdo.avatar.web.common.locks;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.lang.reflect.Method;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/6/17 1:00
 */
public class LockService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(com.choice.cloud.smp.basic.lock.Lock)")
    public void lockPointcut() {

    }

    @Around("lockPointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        Lock lock = getMethodAnnotation(point);
        String lockKeySuffix = getLockKeySuffix(point);

        // 获得锁对象实例
        String lockKey = lock.lockKeyPrefix().getInfo() + lockKeySuffix;
        RedisLock redisLock = new RedisLock(stringRedisTemplate, lockKey, String.valueOf(lock.expireTime()), lock.timeOut() * 1000 * 100);
        try {
            if (redisLock.tryLock()) {
                return point.proceed(point.getArgs());
            } else {
                logger.error("{}:获取redis锁失败,锁的key是 = {}", lock.description(), lockKey);
                throw new Exception(lock.description() + "获取锁失败");
            }
        } finally {
            if (TransactionSynchronizationManager.isActualTransactionActive()) {
                TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                    @Override
                    public void afterCommit() {
                        redisLock.unlock();
                        super.afterCommit();
                    }
                });
            } else {
                redisLock.unlock();
            }
        }
    }

    /**
     * 获取分布式锁后缀
     *
     * @param point
     * @return
     */
    private String getLockKeySuffix(ProceedingJoinPoint point) throws Exception {
        Object[] methodParam = point.getArgs();
        String lockKeySuffix;
        try {
            lockKeySuffix = (String) methodParam[0];
        } catch (ClassCastException e) {
            logger.error("加锁方法参数不合法:{}", methodParam[0]);
            throw new Exception("加锁方法参数不合法");
        }
        return lockKeySuffix;
    }


    /**
     * 获取分布式锁注解中的参数
     *
     * @param joinPoint
     * @return
     */
    private Lock getMethodAnnotation(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        return method.getAnnotation(Lock.class);
    }

}
