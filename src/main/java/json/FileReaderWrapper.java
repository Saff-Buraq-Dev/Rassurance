package json;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderWrapper {
    private BufferedReader reader;

    public FileReaderWrapper(BufferedReader reader) {
        this.reader = reader;
    }

    public String read() {
        String texte = "";
        String ligne;
        try {
            while ((ligne = reader.readLine()) != null) {
                texte += ligne;
            }
            reader.close();
        } catch (IOException e) {}
        return texte;
    }

}
