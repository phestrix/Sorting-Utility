package model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class IntegerData extends DataType<Long> {
    public IntegerData() {
        this.data = new ArrayList<>();
    }
}
