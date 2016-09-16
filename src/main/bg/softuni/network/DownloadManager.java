package main.bg.softuni.network;

import main.bg.softuni.contracts.AsynchDownloader;
import main.bg.softuni.exceptions.InvalidPathException;
import main.bg.softuni.io.OutputWriter;
import main.bg.softuni.staticData.SessionData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DownloadManager implements AsynchDownloader {

    public void download(String fileURL) {
        URL url = null;
        ReadableByteChannel rbc = null;
        FileOutputStream fos = null;

        try {
            if (Thread.currentThread().getName().equals("main")) {
                OutputWriter.writeMessageOnNewLine("Started downloading..");
            }

            url = new URL(fileURL);
            rbc = Channels.newChannel(url.openStream());
            String fileName = extractNameOfFile(fileURL);
            File file = new File(SessionData.currentPath + "/" + fileName);
            fos = new FileOutputStream(file);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

            if (Thread.currentThread().getName().equals("main")) {
                OutputWriter.writeMessageOnNewLine("Download complete..");
            }
        } catch (InvalidPathException ipe) {
            OutputWriter.displayException(ipe.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }

                if (rbc != null) {
                    rbc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void downloadOnNewThread(String fileUrl) {
        Thread thread = new Thread(() -> download(fileUrl));
        OutputWriter.writeMessageOnNewLine(String.format("Worker thread %d started download..", thread.getId()));
        thread.setDaemon(false);
        thread.start();
    }

    private String extractNameOfFile(String fileURL) throws InvalidPathException {
        int indexOfLastSlash = fileURL.lastIndexOf('/');
        if (indexOfLastSlash == -1) {
            throw new InvalidPathException();
        }

        return fileURL.substring(indexOfLastSlash + 1);
    }
}
