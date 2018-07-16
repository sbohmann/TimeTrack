package at.yeoman.timeTrack.storage;

import java.io.File;
import java.io.IOException;

class FileOperations {
    static void delete(File file)
            throws IOException {
        if (!file.delete()) {
            throw new IOException("Unable to delete file [" + file.getCanonicalPath() + "]");
        }
    }

    static void rename(File originalFile, File targetFile)
            throws IOException {
        if (!originalFile.renameTo(targetFile)) {
            throw new IOException("Unable to rename file [" + originalFile.getCanonicalPath() + "] to [" + targetFile + "]");
        }
    }
}
