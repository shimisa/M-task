package org.example.unit;

import org.example.file.FileHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {
    @Test
    void shouldThrowWhenFileNotFound() {
        FileHandler handler = new FileHandler();
        assertThrows(IOException.class, () -> handler.readFile("nonexistent.txt"));
    }

    @Test
    void shouldReadAndWriteFile(@TempDir Path tempDir) throws IOException {
        FileHandler handler = new FileHandler();
        Path testFile = tempDir.resolve("test.txt");

        List<String> content = List.of("test line");
        handler.writeFile(testFile.toString(), content);

        List<String> read = handler.readFile(testFile.toString());
        assertEquals(content, read);
    }
}