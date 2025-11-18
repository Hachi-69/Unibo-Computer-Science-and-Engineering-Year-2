package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Objects;

/**
 * Application controller. Performs the I/O.
 */
final public class Controller {

    private File currentFile = new File(
            System.getProperty("user.home")
            + System.getProperty("file.separator")
            + "output.txt"
    );

    public void setCurrentFile(final File file) {
        Objects.requireNonNull(file);
        this.currentFile = file;
    }

    public File getCurrentFile() {
        return this.currentFile;
    }

    public String getCurrentFilePath() {
        return this.currentFile.getPath();
    }

    public void write(final String content) throws IOException {
        try (final FileWriter w = new FileWriter(this.currentFile)) {
            w.write(content);
        }
    }
}
