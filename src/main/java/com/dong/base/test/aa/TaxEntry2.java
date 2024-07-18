package com.dong.base.test.aa;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TaxEntry2 {

     private String state;
     private String city;
     private BigDecimal rate;
     private BigDecimal price;

    /**
     * 对于每个不同的<省-城市>，我们如何能找到税目的总数以及税率和价格的乘积的总和(∑(税率*价格))。
     * 其中需要注意的点是使用BigDecimal进行多字段聚合。
     */
    public static void RatePriceAggregation(){
        List<TaxEntry2> taxes = Arrays.asList( new TaxEntry2("New York", "NYC", BigDecimal.valueOf(0.2), BigDecimal.valueOf(20.0)),
                new TaxEntry2("New York", "NYC", BigDecimal.valueOf(0.4), BigDecimal.valueOf(10.0)),
                new TaxEntry2("New York", "NYC", BigDecimal.valueOf(0.6), BigDecimal.valueOf(10.0)),
                new TaxEntry2("Florida", "Orlando", BigDecimal.valueOf(0.3), BigDecimal.valueOf(13.0)));//1.2.3.4.5.

//        Map<StateCityGroup, StateCityGroup> mapAggregation = taxes.stream().collect(Collectors.toMap(p -> new StateCityGroup(p.getState(), p.getCity()),
//                p -> new RatePriceAggregation(1, p.getRate().multiply(p.getPrice())),
//                (u1, u2) -> new RatePriceAggregation( u1.getCount() + u2.getCount(), u1.getRatePrice().add(u2.getRatePrice()))  ));




    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class StateCityGroup{
        private String state;
        private String city;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class RatePriceAggregation{
         private int count;
         private BigDecimal ratePrice;
    }

}
