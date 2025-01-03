package model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@Setter
@Getter
public class FloatData extends DataType {
    private final ArrayList<Double> data;
    public void appendData(Double value) {
        data.add(value);
    }

    @Override
    public int getSize() {
        return data.size();
    }
}
