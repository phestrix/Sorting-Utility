package model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@Setter
@Getter
public class FloatData extends DataType {
    private ArrayList<Double> data;

    @Override
    public <Double> void appendData(Double value) {
        data.add((java.lang.Double) value);
    }

    @Override
    public ArrayList<Double> getData() {
        return null;
    }

    @Override
    public int getSize() {
        return data.size();
    }
}
