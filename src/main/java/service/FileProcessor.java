package service;

import io.Reader;
import io.Writer;
import model.type.DataType;
import model.type.FloatData;
import model.type.IntegerData;
import model.type.StringData;
import util.NumberValidator;

import java.util.List;
import java.util.Map;

public class FileProcessor {
    private final Reader reader;
    private final Writer writer;

    public FileProcessor(Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void cleanBeforeForPaths(List<DataType<?>> dataTypes, boolean append){
        for (DataType<?> dataType : dataTypes) {
            if(dataType instanceof IntegerData){
                writer.setPath("integers.txt");
                cleanBefore(append);
            } else if(dataType instanceof FloatData){
                writer.setPath("floats.txt");
                cleanBefore(append);
            } else if(dataType instanceof StringData){
                writer.setPath("strings.txt");
                cleanBefore(append);
            }

        }
    }

    private void cleanBefore(boolean append){
        writer.cleanBefore(append);
    }

    public Map<String, DataType<?>> getDatasFromFile(String filePath) {
        reader.setPath(filePath);
        reader.read();
        List<String> rawData = reader.getData();
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
        return Map.of(
                IntegerData.class.getName(), integerData,
                FloatData.class.getName(), floatData,
                StringData.class.getName(), stringData
        );
    }

    public DataType<?> getDataFromFile(String path) {
        reader.setPath(path);
        reader.read();
        List<String> rawData = reader.getData();
        if (rawData.stream().allMatch(NumberValidator::isLong)) {
            IntegerData integerData = new IntegerData();
            for (String s : rawData) {
                integerData.appendData(Long.parseLong(s));
            }
            return integerData;
        } else if (rawData.stream().allMatch(NumberValidator::isFloat)) {
            FloatData floatData = new FloatData();
            for (String s : rawData) {
                floatData.appendData(Float.parseFloat(s));
            }
            return floatData;
        } else {
            StringData stringData = new StringData();
            for (String s : rawData) {
                stringData.appendData(s);
            }
            return stringData;
        }

    }

    public void writeDataToFile(String filePath, DataType<?> data) {
        writer.setPath(filePath);
        writer.setDataToWrite(data.toStringList());
        writer.write();
    }
}



