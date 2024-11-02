package net.ohnonick2.warpapi;

import com.google.gson.JsonObject;
import net.ohnonick2.warpapi.error.FileCreationException;
import org.intellij.lang.annotations.JdkConstants;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

public class FileManger {

    private String path;


    public FileManger(String path) {
        this.path = path;
    }


    /**
     *
     * @param filename

     * @param content
     * @return boolean
     * @throws FileCreationException
     * if result is true, the file was created successfully and the content was written to it
     * if result is false, the file could not be created or the content could not be written to it
     *
     */
    public boolean createFile(String filename, String content) {
        // Überprüfen, ob die Eingabewerte null sind
        if (filename == null) {
            System.err.println(FileCreationException.nullFilenameException().getMessage());
            return false; // Fehler bei null Dateiname
        }

        if (path == null) {
            System.err.println(FileCreationException.nullPathException().getMessage());
            return false; // Fehler bei null Pfad
        }

        if (content == null) {
            System.err.println(FileCreationException.nullContentException().getMessage());
            return false; // Fehler bei null Inhalt
        }

        File file = new File(path + filename);

        // Datei erstellen, wenn sie nicht existiert
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println(FileCreationException.fileCreationFailedException(e).getMessage());
                return false; // Fehler beim Erstellen der Datei
            }
        }

        // Inhalt in die Datei schreiben
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(content);
            return true; // Erfolgreich
        } catch (IOException e) {
            System.err.println(FileCreationException.contentWriteFailedException(e).getMessage());
            return false; // Fehler beim Schreiben des Inhalts
        }
    }

    /**
     *
     * @return boolean
     * @throws FileCreationException
     * if result is true, the file was deleted successfully
     * if result is false, the file could not be deleted
     *
     */
    public boolean removeFile(String filename) {
        if (filename == null) {
            System.err.println(FileCreationException.nullFilenameException().getMessage());
            return false; // Fehler bei null Dateiname
        }

        if (path == null) {
            System.err.println(FileCreationException.nullPathException().getMessage());
            return false; // Fehler bei null Pfad
        }

        File file = new File(path + filename);

        if (file.exists()) {
            return file.delete();
        }

        return false;
    }

    /**
     *
     * @return boolean
     * @throws FileCreationException
     * if result is true, the file exists
     * if result is false, the file does not exist
     *
     */
    public boolean existFile(String filename) {
        if (filename == null) {
            System.err.println(FileCreationException.nullFilenameException().getMessage());
            return false; // Fehler bei null Dateiname
        } else {

            return true;
        }
    }
}
