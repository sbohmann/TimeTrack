package at.yeoman.timeTrack.storage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileLock;

public class Storage {
    private static final String StorageFileName = "data";

    private static final String TemporaryWritingFileName = StorageFileName + "_";

    private File storageDirectory;

    private final File storageFile;

    private FileLock storageFileLock;

    private final File temporaryWritingFile;

    private FileLock temporaryWritingFileLock;

    public Storage()
            throws IOException {
        setStorageDirectory();
        storageFile = new File(storageDirectory, StorageFileName);
        storageFileLock = Locking.lock(storageFile);

        temporaryWritingFile = new File(storageDirectory, TemporaryWritingFileName);
        temporaryWritingFileLock = Locking.lock(temporaryWritingFile);
    }

    public void writeData(byte[] data)
            throws IOException {
        try (OutputStream out = new FileOutputStream(temporaryWritingFile)) {
            out.write(data);
        }
        FileOperations.delete(storageFile);
        FileOperations.rename(temporaryWritingFile, storageFile);
        storageFileLock.release();
        storageFileLock = temporaryWritingFileLock;
        temporaryWritingFileLock = Locking.lock(temporaryWritingFile);
    }

    private void setStorageDirectory()
            throws IOException {
        File applicationDataDirectory = getApplicationDataDirectory();
        storageDirectory = new File(applicationDataDirectory, "at.yeoman.imageview");
        checkStorageDirectory();
    }

    private File getApplicationDataDirectory()
            throws IOException {
        File applicationDataDirectory = new File(System.getenv("APPDATA"));
        if (!applicationDataDirectory.isDirectory()) {
            throw new IOException("Not a directory [" + applicationDataDirectory + "]");
        }
        return applicationDataDirectory;
    }

    private void checkStorageDirectory()
            throws IOException {
        if (storageDirectory.exists()) {
            if (!storageDirectory.isDirectory()) {
                throw new IOException("Not a directory [" + storageDirectory.getCanonicalPath() + "]");
            }
        } else {
            if (!storageDirectory.mkdir()) {
                throw new IOException("Unable to create directory [" + storageDirectory.getCanonicalPath() + "]");
            }
        }
    }
}
