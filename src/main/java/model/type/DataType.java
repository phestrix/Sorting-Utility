package model.type;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class DataType<T> {
    protected List<T> data;

    public void appendData(T value) {
        data.add(value);
    }

    public int getSize(){
        return data.size();
    }

    public List<String> toStringList(){
        List<String> list = new ArrayList<>();
        for (T node : data) {
            String string = node.toString();
            list.add(string);
        }
        return list;
    }

}
