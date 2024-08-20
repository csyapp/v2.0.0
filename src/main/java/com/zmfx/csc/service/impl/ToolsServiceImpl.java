package com.zmfx.csc.service.impl;

import com.zmfx.csc.service.ToolsService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;

@Service
public class ToolsServiceImpl implements ToolsService {

    public ToolsServiceImpl() {}

    @Override
    public byte[] transformFileToByte(File file) {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String formatStringWithLeadingZeros(String originalString, int desiredLength) {
        return String.format("%1$" + desiredLength + "s", originalString).replace(' ', '0');
    }

    public void removeUsedFiles() {
        try {
            Files.deleteIfExists(Paths.get("written.pdf"));
            Files.deleteIfExists(Paths.get("outputCard.pdf"));
            Files.deleteIfExists(Paths.get("finalCard.pdf"));
            Files.deleteIfExists(Paths.get("displayPic.jpeg"));
            Files.deleteIfExists(Paths.get("displayPic.png"));
            Files.deleteIfExists(Paths.get("displayPic.jpg"));
            Files.deleteIfExists(Paths.get("displayPic.gif"));
            Files.deleteIfExists(Paths.get("displayPic.tiff"));
            Files.deleteIfExists(Paths.get("finalPdf-1.png"));
            Files.deleteIfExists(Paths.get("finalPdf-2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isValidEmail(String email) {
        // Simple email validation using a regular expression
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
        return email.matches(emailRegex);
    }
}
