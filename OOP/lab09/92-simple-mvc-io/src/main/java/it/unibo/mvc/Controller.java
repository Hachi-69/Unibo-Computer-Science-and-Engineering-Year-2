package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Application controller. Performs the I/O.
 */
public final class Controller {

    private File currentFile = new File(
            System.getProperty("user.home")
                    + System.getProperty("file.separator")
                    + "output.txt");

    /**
     * Sets (replaces) the current file that subsequent write operations will
     * target. The provided reference must be non-null; the file is not created
     * or validated at this point.
     *
     * @param file the non-null file to become the current output target
     * @throws NullPointerException if {@code file} is {@code null}
     */
    public void setCurrentFile(final File file) {
        Objects.requireNonNull(file);
        this.currentFile = file;
    }

    /**
     * Returns the current file instance used as the output target.
     *
     * @return the current {@link File}
     */
    public File getCurrentFile() {
        return this.currentFile;
    }

    /**
     * Returns the path (as supplied by {@link File#getPath()}) of the current
     * output file.
     *
     * @return the current file's path string
     */
    public String getCurrentFilePath() {
        return this.currentFile.getPath();
    }

    /**
     * Writes the given textual content to the current file, overwriting any
     * existing data. The write uses the platform-default character encoding.
     *
     * @param content the text to persist; may be empty but not {@code null}
     * @throws IOException          if an I/O error occurs while opening or writing
     * @throws NullPointerException if {@code content} is {@code null}
     */
    public void write(final String content) throws IOException {
        Objects.requireNonNull(content);
        try (java.io.Writer w = java.nio.file.Files.newBufferedWriter(
                this.currentFile.toPath(),
                java.nio.charset.StandardCharsets.UTF_8)) {
            w.write(content);
        }
    }
}
