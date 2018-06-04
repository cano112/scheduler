package pl.edu.agh.wiet.studiesplanner.gui.service;

import com.vaadin.ui.Upload;

import java.io.File;
import java.io.OutputStream;

public class UploadReciver implements Upload.Receiver, Upload.SucceededListener {

    public File file;

    @Override
    public OutputStream receiveUpload(String filename, String mimeType) {
        // Create and return a file output stream
        System.out.println(filename);
        return null;
    }

    public void uploadSucceeded(Upload.SucceededEvent event) {
        // Show the uploaded file in the image viewer
        //image.setSource(new FileResource(file));


    }
}
