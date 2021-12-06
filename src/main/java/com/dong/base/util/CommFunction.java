package com.dong.base.util;

import com.alibaba.fastjson.JSON;
import com.dong.base.model.OrgEntity;
import com.dong.base.model.UserEntity;
import com.sun.istack.internal.NotNull;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/12/6
 */
public class CommFunction {

    public static void main(String[] args) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(22);
        OrgEntity orgEntity = executeJobDefault(userEntity, new Predicate<UserEntity>() {
            @Override
            public boolean test(UserEntity userEntity) {
                return userEntity!=null&&userEntity.getId()%2==1;
            }
        }, new Supplier<OrgEntity>() {
            @Override
            public OrgEntity get() {
                OrgEntity orgEntity = new OrgEntity();
                orgEntity.setOrgId(11L);
                return orgEntity;
            }
        }, new Supplier<OrgEntity>() {
            @Override
            public OrgEntity get() {
                System.out.println("传入的参数不正确");
                return null;
            }
        });
        System.out.println("返回结果："+ JSON.toJSONString(orgEntity));
    }


    /**
     * 任务处理
     * @param p 请求参数
     * @param checkParam 校验函数
     * @param supplier 消费者
     * @param errorHandler 错误处理
     * @param <T> 返回参数
     * @param <P> 任务的参数
     * @return
     */
    public static <T,P> T executeJobDefault(P p, Predicate<P> checkParam, Supplier<T> supplier,Supplier<T> errorHandler){
        return executeWithRetry(checkParam,p,supplier,null,null,errorHandler);
    }

    /**
     * 任务重试处理
     */
    public static <T,P> T executeWithRetry(@NotNull Predicate<P> checkParam, P t, @NotNull Supplier<T> supplier, Integer tryNum, Long interval, @NotNull Supplier<T> errorHandler) {
        if(tryNum==null || tryNum<=0){
            tryNum =1;
        }
        if(interval==null||interval<0){
            interval = 500L;
        }
        Throwable ex = null;
        for (int i=0;i<tryNum;i++){
           try{
               if(checkParam.test(t)){
                   return supplier.get();
               }
           }catch (Exception e){
               System.err.println(e.getMessage()+e);
                ex = e;
           }
            Optional.ofNullable(interval).ifPresent(sleeps->{
                try {
                    Thread.sleep(sleeps);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage()+e);
                }
            });
        }
        Optional.ofNullable(ex).ifPresent(throwable -> System.err.println((throwable.getMessage()+throwable)));
        return null == errorHandler ? null : errorHandler.get();
    }

}
