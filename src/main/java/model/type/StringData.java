package model.type;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class StringData extends DataType<String> {
    public StringData() {
        this.data = new ArrayList<>();
    }
}
