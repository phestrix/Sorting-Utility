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

    @Override
    public <String> void appendData(String value) {
        data.add((java.lang.String) value);
    }

    @Override
    public int getSize() {
        return data.size();
    }
}
