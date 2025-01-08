package util;

import java.io.File;

public class DirectoryUtil {
    public static void checkAndCreateDirectory(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new RuntimeException("Failed to create directory: " + path);
            }
        }
    }
}
