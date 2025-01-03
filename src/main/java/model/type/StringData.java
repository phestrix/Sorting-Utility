package model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
public class StringData extends DataType {
    private final ArrayList<String> data;

    public void appendData(String value) {
        data.add(value);
    }
    @Override
    public int getSize() {
        return data.size();
    }
}
