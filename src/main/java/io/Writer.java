package io;

import java.util.List;

public interface Writer {
    void write();
    void setPath(String path);
    void setDataToWrite(List<String> data);
    void cleanBefore(boolean append);
    void setAppend(boolean append);
}
