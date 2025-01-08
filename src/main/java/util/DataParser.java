package util;

import model.type.DataType;
import model.type.FloatData;
import model.type.IntegerData;
import model.type.StringData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataParser {
    public static Map<String, DataType<?>> parseData(List<String> rawData) {
        IntegerData integerData = new IntegerData();
        FloatData floatData = new FloatData();
        StringData stringData = new StringData();

        for (String s : rawData) {
            if (NumberValidator.isLong(s)) {
                integerData.appendData(Long.parseLong(s));
            } else if (NumberValidator.isFloat(s)) {
                floatData.appendData(Float.parseFloat(s));
            } else {
                stringData.appendData(s);
            }
        }

        Map<String, DataType<?>> dataMap = new HashMap<>();
        dataMap.put(IntegerData.class.getName(), integerData);
        dataMap.put(FloatData.class.getName(), floatData);
        dataMap.put(StringData.class.getName(), stringData);

        return dataMap;
    }
}