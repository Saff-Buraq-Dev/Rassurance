package json;

import validation.MessageErreur;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterWrapper {

    private FileWriter writer;

    public FileWriterWrapper(FileWriter writer) {
        this.writer = writer;
    }

    public void write(String toWrite)  {
        try {
            writer.write(toWrite);
            writer.close();
        } catch (IOException e) {}

    }
}

