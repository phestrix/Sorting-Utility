package model.statistics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StringStatisticSet {
    private int count;
    private int minLength;
    private int maxLength;
}
