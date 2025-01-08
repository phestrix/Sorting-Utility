package service;

import io.Reader;
import io.Writer;
import lombok.Getter;
import lombok.Setter;
import model.type.DataType;
import util.DataParser;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class FileProcessor {
    private final Reader reader;
    private final Writer writer;
    private boolean append;

    public FileProcessor(Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public Map<String, DataType<?>> getDataFromFile(String filePath) {
        reader.setPath(filePath);
        reader.read();
        List<String> rawData = reader.getData();
        return DataParser.parseData(rawData);
    }

    public void writeDataToFile(String filePath, DataType<?> data) {
        writer.setPath(filePath);
        writer.setDataToWrite(data.toStringList());
        writer.setAppend(append);
        writer.write();
    }
}



