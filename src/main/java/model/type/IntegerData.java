package model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class IntegerData extends DataType {
    private final ArrayList<Integer> data;

    public void appendData(Integer value) {
        data.add(value);
    }
    @Override
    public int getSize() {
        return data.size();
    }
}
