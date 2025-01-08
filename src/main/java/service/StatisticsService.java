package service;

import model.statistics.NumericStatisticSet;
import model.statistics.StringStatisticSet;
import model.type.DataType;
import model.type.FloatData;
import model.type.IntegerData;
import model.type.StringData;

import java.util.List;

public class StatisticsService {

    public int getShortStat(List<DataType<?>> dataList) {
        int count = 0;
        for (DataType<?> data : dataList) {
            count += data.getSize();
        }
        return count;
    }

    public NumericStatisticSet<? extends Number> collectFullNumericStatistic(List<DataType<?>> listOfData) {
        if (listOfData.get(0) instanceof IntegerData) {
            var set = new NumericStatisticSet<>(0, Long.MAX_VALUE, Long.MIN_VALUE, 0L, 0L);
            for (var node : listOfData) {
                var tmp = getFullIntegerStat((IntegerData) node);
                set.setCount(set.getCount() + tmp.getCount());
                set.setMax(set.getMax() < tmp.getMax() ? tmp.getMax() : set.getMax());
                set.setMin(set.getMin() < tmp.getMin() ? set.getMin() : tmp.getMin());
                set.setSum(set.getSum() + tmp.getSum());
                set.setMean((set.getMean() * tmp.getCount() + tmp.getMean() * set.getCount())
                        / (set.getCount()) + tmp.getCount());
            }
            return set;
        } else if (listOfData.get(0) instanceof FloatData) {
            var set = new NumericStatisticSet<>(0, Float.MAX_VALUE, Float.MIN_VALUE, 0f, 0f);
            for (var node : listOfData) {
                var tmp = getFullFloatStat((FloatData) node);
                set.setCount(set.getCount() + tmp.getCount());
                set.setMax(set.getMax() < tmp.getMax() ? tmp.getMax() : set.getMax());
                set.setMin(set.getMin() < tmp.getMin() ? set.getMin() : tmp.getMin());
                set.setSum(set.getSum() + tmp.getSum());
                set.setMean((set.getMean() * tmp.getCount() + tmp.getMean() * set.getCount())
                        / (set.getCount()) + tmp.getCount());
            }
            return set;
        }
        return null;
    }

    public StringStatisticSet collectFullStringStatistic(List<DataType<?>> listOfData) {
        var set = new StringStatisticSet(0, 0, 0);
        for (var node : listOfData) {
            var tmp = getFullStringStat((StringData) node);
            set.setCount(set.getCount() + tmp.getCount());
            set.setMaxLength(Math.max(set.getMaxLength(), tmp.getMaxLength()));
            set.setMinLength(Math.max(set.getMinLength(), tmp.getMinLength()));
        }
        return set;
    }

    private NumericStatisticSet<Long> getFullIntegerStat(IntegerData data) {
        NumericStatisticSet<Long> stat = new NumericStatisticSet<>();
        stat.setCount(data.getData().size());
        stat.setMean((long) data.getData().stream().mapToLong(Long::longValue).average().orElse(0));
        stat.setMin(data.getData().stream().mapToLong(Long::longValue).min().orElse(0));
        stat.setMax(data.getData().stream().mapToLong(Long::longValue).max().orElse(0));
        stat.setSum(data.getData().stream().mapToLong(Long::longValue).sum());
        return stat;
    }


    private NumericStatisticSet<Float> getFullFloatStat(FloatData data) {
        NumericStatisticSet<Float> stat = new NumericStatisticSet<>();
        stat.setCount(data.getData().size());
        stat.setMean((float) data.getData().stream().mapToDouble(Float::floatValue).average().orElse(0));
        stat.setMin((float) data.getData().stream().mapToDouble(Float::floatValue).min().orElse(0));
        stat.setMax((float) data.getData().stream().mapToDouble(Float::floatValue).max().orElse(0));
        stat.setSum((float) data.getData().stream().mapToDouble(Float::floatValue).sum());
        return stat;
    }

    private StringStatisticSet getFullStringStat(StringData data) {
        StringStatisticSet stat = new StringStatisticSet();
        stat.setCount(data.getData().size());
        stat.setMinLength(data.getData().stream().mapToInt(String::length).min().orElse(0));
        stat.setMaxLength(data.getData().stream().mapToInt(String::length).max().orElse(0));
        return stat;
    }

}
