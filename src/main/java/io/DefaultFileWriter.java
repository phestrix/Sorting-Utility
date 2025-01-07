package io;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor

public class DefaultFileWriter implements Writer {
    private String path;
    private List<String> dataToWrite;
    private BufferedWriter writer;

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void setDataToWrite(List<String> data) {
        this.dataToWrite = data;
    }

    @Override
    public void write() {
        try {
            writer = new BufferedWriter(new FileWriter(path, true));
            for (String line : dataToWrite) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cleanBefore(boolean append) {
        if (!append) {
            try {
                writer = new BufferedWriter(new FileWriter(path));
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

