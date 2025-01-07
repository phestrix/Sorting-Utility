package io;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class DefaultFileReader implements Reader {
    @Setter
    @Getter
    private String path;
    @Setter
    @Getter
    private ArrayList<String> data;
    private BufferedReader input;

    public DefaultFileReader() {
        this.data = new ArrayList<>();
    }

    public DefaultFileReader(String path) {
        this.path = path;
        this.data = new ArrayList<>();
    }

    @Override
    public void read() {
        if (createInputStream(path)) {
            throw new RuntimeException("File is not readable or does not exist");
        }
        if (readFromStreamToData()) {
            throw new RuntimeException("Could not read from file");
        }
    }


    private boolean createInputStream(String path) {
        try {
            input = new BufferedReader(new FileReader(path));
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    private boolean readFromStreamToData() {
        try {
            data.clear();
            String line;
            while ((line = input.readLine()) != null) {
                data.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        finally{
            try {
                input.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
