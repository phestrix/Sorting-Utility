package model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class FloatData extends DataType<Float> {
    public FloatData() {
        this.data = new ArrayList<>();
    }

}
