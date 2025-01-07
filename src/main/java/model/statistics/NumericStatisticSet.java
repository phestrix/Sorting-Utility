package model.statistics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NumericStatisticSet<T extends Number> {
    private int count;
    private T min;
    private T max;
    private T mean;
    private T sum;
}
