package com.dong.base.test.aa;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TaxEntry {
    private String state;
    private String city;
    private int numEntries;
    private double price;    //Constructors, getters, hashCode, equals etc}1.2.3.4.5.6.7.8.


    public static void main(String[] args) {
        //计算每个城市的税目总数非常简单：
        List<TaxEntry> taxes = Lists.newArrayList();
        Map<String, Integer> totalNumEntriesByCity = taxes.stream().collect(
                Collectors.groupingBy(TaxEntry::getCity,
                        Collectors.summingInt(TaxEntry::getNumEntries)));


    }

    //去求每个省和城市的总税目数
    public void  StateCityGroup(String state, String city) {
        List<TaxEntry> taxes = Lists.newArrayList();
        Map<StateCityGroup, Integer> totalNumEntriesForStateCity =  taxes.stream().collect(Collectors.groupingBy(
                p -> new StateCityGroup(p.getState(), p.getCity()),Collectors.summingInt(TaxEntry::getNumEntries))  );//1.2.3.4.


    }

    //找到一个给定的省和城市的税目数和平均价格的总和
    void TaxEntryAggregation (int totalNumEntries, double averagePrice ) {
        List<TaxEntry> taxes = Lists.newArrayList();
        Map<StateCityGroup, TaxEntry> aggregationByStateCity = taxes.stream().collect(Collectors.groupingBy(
                p -> new StateCityGroup(p.getState(), p.getCity()), Collectors.collectingAndThen(Collectors.toList(),
                        list -> {int entries = list.stream().collect(Collectors.summingInt(TaxEntry::getNumEntries));
                    double priceAverage = list.stream().collect(Collectors.averagingDouble(TaxEntry::getPrice));
                    return new TaxEntry("","",entries, priceAverage);})));//1.2.3.4.5.6.7.8.

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class StateCityGroup{
        private String state;
        private String city;
    }
}