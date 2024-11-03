package net.ohnonick2.warpapi;

import net.ohnonick2.warpapi.error.FileCreationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class FileManger {

    private String path;

    /**
     * Konstruktor, der den Dateipfad setzt.
     *
     * @param path Der Pfad, in dem die Dateien verwaltet werden.
     */
    public FileManger(String path) {
        this.path = path;
    }

    /**
     * Erstellt eine Datei und schreibt den Inhalt hinein.
     *
     * @param filename Der Name der zu erstellenden Datei.
     * @param content Der Inhalt, der in die Datei geschrieben werden soll.
     * @return true, wenn die Datei erfolgreich erstellt und der Inhalt geschrieben wurde, ansonsten false.
     */
    public boolean createFile(String filename, String content) {
        if (filename == null) {
            System.err.println(FileCreationException.nullFilenameException().getMessage());
            return false;
        }

        if (path == null) {
            System.err.println(FileCreationException.nullPathException().getMessage());
            return false;
        }

        if (content == null) {
            System.err.println(FileCreationException.nullContentException().getMessage());
            return false;
        }

        File file = new File(path + filename);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println(FileCreationException.fileCreationFailedException(e).getMessage());
                return false;
            }
        }

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(content);
            return true;
        } catch (IOException e) {
            System.err.println(FileCreationException.contentWriteFailedException(e).getMessage());
            return false;
        }
    }

    /**
     * Liest den Inhalt einer Datei.
     *
     * @return Der Inhalt der Datei, wenn die Datei existiert, ansonsten null.
     */
    public File[] getFiles() {
        File file = new File(path);
        return file.listFiles();
    }

    /**
     * Löscht eine Datei.
     *
     * @param filename Der Name der zu löschenden Datei.
     * @return true, wenn die Datei erfolgreich gelöscht wurde, ansonsten false.
     */
    public boolean removeFile(String filename) {
        if (filename == null) {
            System.err.println(FileCreationException.nullFilenameException().getMessage());
            return false;
        }

        if (path == null) {
            System.err.println(FileCreationException.nullPathException().getMessage());
            return false;
        }

        File file = new File(path + filename);

        if (file.exists()) {
            return file.delete();
        }

        return false;
    }

    /**
     * Überprüft, ob eine Datei existiert.
     *
     * @param filename Der Name der Datei, deren Existenz überprüft werden soll.
     * @return true, wenn die Datei existiert, ansonsten false.
     */
    public boolean existFile(String filename) {
        if (filename == null) {
            System.err.println(FileCreationException.nullFilenameException().getMessage());
            return false;
        }

        File file = new File(path + filename);
        return file.exists();
    }

    /** Reads a file from the resources folder.
     *
     * @param path The path to the file.
     * @return The content of the file.
     */
    public String readFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }

        if (!file.canRead()) {
            return null;
        }

        InputStream inputStream = getClass().getResourceAsStream(path);
        StringBuilder stringBuilder = new StringBuilder();
        byte[] buffer = new byte[1024];
        int bytesRead;
        try {
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                stringBuilder.append(new String(buffer, 0, bytesRead));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();


    }
}
