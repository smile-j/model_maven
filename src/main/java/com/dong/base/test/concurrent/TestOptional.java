package com.dong.base.test.concurrent;

import com.dong.base.model.OrderEntity;
import com.dong.base.model.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * https://juejin.cn/post/7162882431932956680
 */
public class TestOptional {

    public OrderEntity getOrder(){
        OrderEntity order = new OrderEntity();
        order.setId("1001");
        order.setUser(UserEntity.builder().email("ab123gh@163.com").build());
        return order;
    }
    public Optional<OrderEntity> getOptionOrder(){
        OrderEntity order = new OrderEntity();
        order.setId("1001");
        order.setUser(UserEntity.builder().email("ab123gh@163.com").build());
        return Optional.ofNullable(order);
//        return Optional.empty();
    }

    public BigDecimal calcDefaultAmount(){
        return new BigDecimal("3.14");
    }

    @Test
    public  void testModel() {
        OrderEntity order = getOrder();
        String userCode = Optional.ofNullable(order)
                .map(OrderEntity::getUser)
                .map(UserEntity::getEmail)
                .map(String::toUpperCase)
                .orElse("default");
        System.out.println(userCode);
    }

    /**
     * Optional的创建
     *
     * ofNullable(e)方法,传入的e可以是null或不是null。
     * of(e)方法,传入的e不能是null，否则报NullPointerException
     * empty()方法  得到一个空的Optional，一般也用于返回值必须是Optional的场景
     */
    @Test
    public void testCreate(){
        Optional<OrderEntity> optional = null;
//       optional = Optional.of(null);
         optional = Optional.ofNullable(null);
         optional = Optional.empty();
        System.out.println(optional);
    }

    /**
     * ifPresent()方法
     */
    @Test
    public void isNull(){
        Optional<String> optional = Optional.empty();
        System.out.println(optional.isPresent());
        optional =  Optional.ofNullable(null);
        System.out.println(optional.isPresent());
    }

    /**
     * get()方法  获取Optional的值，当没有值时会抛出一个NoSuchElementException异常。
     *
     * orElse方法 没有值时会返回指定的值。
     *
     * orElseGet方法 同上，不过参数变成了一个提供默认值的lambda函数，这用在取指定值需要一定代价的场景，如下：
     *
     * orElseThrow方法 没有值时抛出一个指定的异常，如下：
     *
     */
    @Test
    public void testGetVal(){
        Optional<BigDecimal> amountOpt = Optional.empty();
        BigDecimal amount = amountOpt.orElseGet(() -> calcDefaultAmount());
        System.out.println(amount);

        Optional<OrderEntity> orderOption = getOptionOrder();
        OrderEntity orderEntity = orderOption.orElseThrow(() -> new NullPointerException("orderEntity is null" ));
        System.out.println(orderEntity.getId());
    }

    /**
     * 函数式处理
     *  ifPresent(Consumer<? super T> consumer)方法 :
     *      这个方法和isPresent()方法不一样，这个方法代表如果Optional有值时，就执行传入的lambda函数，如下：
     *  filter方法：这个方法用于过滤Optional中的值，若Optional有值，且值满足过滤函数，则返回此Optional，否则返回空Optional。
     *  map方法: 这个方法用于转换值,若Optional有值，执行map中的lambda函数转换值
     *      Optional还提供了一个flatMap方法，与map方法的区别是，传给flatMap的lambda函数，这个lambda函数的返回值需要是Optional。
     */
    @Test
    public void doAction(){
        Optional<OrderEntity> orderOption = getOptionOrder();
        orderOption.ifPresent((user) -> System.out.printf("%s\n", user.toString()));
        System.out.println("--------------------------------------");
        Optional<String> nameOpt = Optional.of("123");
        String id = nameOpt.filter(StringUtils::isNotBlank).orElse("001");
        System.out.println(id);
        System.out.println("====================");
        OrderEntity order = getOrder();
        String aDefault = Optional.ofNullable(order)
                .map(OrderEntity::getUser)
                .map(UserEntity::getEmail)
                .flatMap((s) -> Optional.ofNullable(s.toUpperCase()))
                .orElse("default");
        Optional<String> optionalS = Optional.ofNullable(order)
                .map(OrderEntity::getUser)
                .map(UserEntity::getEmail)
                .flatMap((s) -> Optional.ofNullable(s.toUpperCase()));
        System.out.println(aDefault);
    }
}
