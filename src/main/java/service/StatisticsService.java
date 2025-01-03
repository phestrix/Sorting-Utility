package service;

import model.StatisticSet;
import model.type.DataType;
import model.type.FloatData;
import model.type.IntegerData;

public class StatisticsService {
    public static int getShortStat(DataType data){
        return data.getData().size();
    }

    public static StatisticSet<Integer> getFullIntStat(IntegerData data){
        StatisticSet<Integer> stat = new StatisticSet<>();
        stat.setCount(data.getData().size());
        stat.setMean((int) data.getData().stream().mapToInt(Integer::intValue).average().orElse(0));
        stat.setMin(data.getData().stream().mapToInt(Integer::intValue).min().orElse(0));
        stat.setMax(data.getData().stream().mapToInt(Integer::intValue).max().orElse(0));
        return stat;
    }

    public static StatisticSet<Double> getFullFloatStat(FloatData data){
        StatisticSet<Double> stat = new StatisticSet<>();
        stat.setCount(data.getData().size());
        stat.setMean(data.getData().stream().mapToDouble(Double::doubleValue).average().orElse(0));
        stat.setMin(data.getData().stream().mapToDouble(Double::doubleValue).min().orElse(0));
        stat.setMax(data.getData().stream().mapToDouble(Double::doubleValue).max().orElse(0));
        return stat;
    }
}
