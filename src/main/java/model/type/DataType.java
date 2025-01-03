package model.type;

import java.util.ArrayList;

public abstract class DataType {
    public abstract <T> void appendData(T value);

    abstract public ArrayList<?> getData();

    abstract public int getSize();
}
