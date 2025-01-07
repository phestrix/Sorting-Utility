package io;

import java.util.List;

public interface Reader {
    void read();
    void setPath(String path);
    List<String> getData();
}
