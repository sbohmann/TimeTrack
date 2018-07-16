package at.yeoman.timeTrack.storage;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

class Locking {
    static FileLock lock(File file)
            throws IOException {
        FileLock result = new RandomAccessFile(file, "rw").getChannel().tryLock();
        if (result == null) {
            throw new IOException("Unable to lock storage file");
        }
        return result;
    }
}
