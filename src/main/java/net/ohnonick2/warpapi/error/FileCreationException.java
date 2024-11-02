package net.ohnonick2.warpapi.error;


public class FileCreationException extends Exception {

    private static final String NULL_FILENAME = "Der Dateiname darf nicht null sein.";
    private static final String NULL_PATH = "Der Pfad darf nicht null sein.";
    private static final String NULL_CONTENT = "Der Inhalt darf nicht null sein.";
    private static final String FILE_CREATION_FAILED = "Die Datei konnte nicht erstellt werden.";
    private static final String CONTENT_WRITE_FAILED = "Der Inhalt konnte nicht in die Datei geschrieben werden.";


    public FileCreationException() {
        super();
    }

    public FileCreationException(String message) {
        super(message);
    }

    public FileCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public static FileCreationException nullFilenameException() {
        return new FileCreationException(NULL_FILENAME);
    }

    public static FileCreationException nullPathException() {
        return new FileCreationException(NULL_PATH);
    }

    public static FileCreationException nullContentException() {
        return new FileCreationException(NULL_CONTENT);
    }

    public static FileCreationException fileCreationFailedException(Throwable cause) {
        return new FileCreationException(FILE_CREATION_FAILED, cause);
    }

    public static FileCreationException contentWriteFailedException(Throwable cause) {
        return new FileCreationException(CONTENT_WRITE_FAILED, cause);
    }
}