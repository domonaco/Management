package it.monaco.medical.service.configuration;

import java.io.*;
import java.util.Properties;

public class FileSystemResourceByEnvironmentVariable extends Properties {

    public FileSystemResourceByEnvironmentVariable(final String propertiesPathEnvironmentVariable) {
        final String path = this.resolveEnvironmentVariable(propertiesPathEnvironmentVariable);
        this.loadProperties(path);
    }

    private void loadProperties(final String path) {
        final File f = new File(path);

        if (!f.exists()) {
            throw new IllegalArgumentException(String.format("File '%s' does not exist.", path));
        }

        if (!f.canRead()) {
            throw new IllegalArgumentException(String.format("File '%s' is not readable.", path));
        }

        InputStream stream = null;
        try {
            stream = new FileInputStream(f);
            this.load(stream);

        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(String.format("File '%s' does not exist.", f.getAbsolutePath()));

        } catch (IOException e) {
            throw new RuntimeException(String.format("Error while loading properties from '%s': %s", f.getAbsolutePath(), e.getMessage()), e);

        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (Exception e) {
                }
            }
        }
    }

    private String resolveEnvironmentVariable(final String propertiesPathEnvironmentVariable) {
        final String path = System.getenv(propertiesPathEnvironmentVariable);
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException(String.format("System environment variable '%s' has no value.", propertiesPathEnvironmentVariable));
        }

        return path;
    }
}
