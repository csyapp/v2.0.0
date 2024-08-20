package com.zmfx.csc.service.impl;

import com.zmfx.csc.domain.Carte;
import com.zmfx.csc.domain.Template;
import com.zmfx.csc.service.PDFService;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.*;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PDFServiceImpl implements PDFService {

    private final org.slf4j.Logger log = LoggerFactory.getLogger(PDFServiceImpl.class);

    String strRecto = "recto.pdf";
    String strVerso = "verso.pdf";
    String strFinal = "final.pdf";
    String strFinal2 = "../webapp/content/pdf/";

    public PDFServiceImpl() {}

    @Override
    public File combinePDFPagesToByteArray(Float fontSize, Carte card) {
        deletePreviousGeneratedPdfFiled();

        PDDocument pdfRectoAss = addTextAndImageInPdf(fontSize, card);
        PDDocument pdfVersoAss = displayPDFFromDataBase(card.getTemplate().getPdfContentVerso());
        try {
            pdfRectoAss.save(strRecto);
            pdfVersoAss.save(strVerso);
            pdfRectoAss.close();
            pdfVersoAss.close();

            PDFMergerUtility pdfMerger = new PDFMergerUtility();
            pdfMerger.addSource(new File(strRecto));
            pdfMerger.addSource(new File(strVerso));
            String fileName =
                card.getMatricule() +
                "_" +
                LocalDateTime.now().getYear() +
                "_" +
                LocalDateTime.now().getYear() +
                LocalDateTime.now().getMonthValue() +
                "_" +
                LocalDateTime.now().getDayOfMonth() +
                LocalDateTime.now().getHour() +
                "_" +
                LocalDateTime.now().getNano() +
                ".pdf";
            pdfMerger.setDestinationFileName(fileName);
            pdfMerger.mergeDocuments(null);

            return new File(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePreviousGeneratedPdfFiled() {
        String currentDirectory = System.getProperty("user.dir");
        // Create a File object for the current directory
        File directory = new File(currentDirectory);
        // Get all files in the current directory
        File[] files = directory.listFiles();

        // Iterate over each file
        if (files != null) {
            for (File file : files) {
                // Check if the file name contains ".pdf"
                if (file.isFile() && file.getName().toLowerCase().contains(".pdf")) {
                    // Delete the file
                    if (file.delete()) {
                        log.info("File deleted: {}", file.getName());
                    } else {
                        log.info("Failed to delete file: {}", file.getName());
                    }
                }
            }
        }
    }

    public PDDocument addTextAndImageInPdf(Float fontSize, Carte card) {
        String fontPathNameSurnameMember = "/fonts/helvetica-bold.ttf";
        String fontPathMatricule = "/fonts/helvetica-bold.ttf";
        String fontPathDateDeliveryExpiry = "/fonts/helvetica-bold.ttf";

        Float facteur = (72f / 25.4f);
        Template template1 = miseAEchelleDesCoordoonees(facteur, card.getTemplate(), fontSize);

        PDDocument document = deleteOnePage(displayPDFFromDataBase(template1.getPdfContentRecto()));

        printTextInPDF(
            fontSize - 1.95f,
            template1.getMembreX().floatValue(),
            template1.getMembreY().floatValue(),
            card.getTemplate().getLibelle(),
            document,
            fontPathNameSurnameMember
        );

        printTextInPDF(
            fontSize,
            template1.getPrenomX().floatValue(),
            template1.getPrenomY().floatValue(),
            card.getSurname(),
            document,
            fontPathNameSurnameMember
        );
        printTextInPDF(
            fontSize,
            template1.getNomX().floatValue(),
            template1.getNomY().floatValue(),
            card.getName(),
            document,
            fontPathNameSurnameMember
        );

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        float dateDeliveryReduction = 3.5f;
        printTextInPDF(
            fontSize - dateDeliveryReduction,
            template1.getDateDelivranceX().floatValue(),
            template1.getDateDelivranceY().floatValue(),
            (card.getDateDelivrance() == null) ? LocalDate.now().plusDays(5).format(formatter) : card.getDateDelivrance().format(formatter),
            document,
            fontPathDateDeliveryExpiry
        );
        printTextInPDF(
            fontSize - dateDeliveryReduction,
            template1.getDateExpirationX().floatValue(),
            template1.getDateExpirationY().floatValue(),
            (card.getDateExpiration() == null) ? LocalDate.now().plusDays(5).format(formatter) : card.getDateExpiration().format(formatter),
            document,
            fontPathDateDeliveryExpiry
        );

        printTextInPDF(
            fontSize - 2,
            template1.getMatriculeX().floatValue(),
            template1.getMatriculeY().floatValue(),
            card.getMatricule(),
            document,
            fontPathMatricule
        );

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(() -> {});

        try {
            document = addImageInPDFsBox(card.getPicture(), card.getPictureExtension(), document, template1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return document;
    }

    public PDDocument addImageInPDFsBox(byte[] picture, String pictureExtension, PDDocument document, Template cardSample) {
        try {
            PDPage page = document.getPage(0);
            PDImageXObject image = PDImageXObject.createFromByteArray(document, picture, pictureExtension);

            // Set the position where the image will be added (adjust x and y as needed)
            float x = cardSample.getImageX().floatValue();
            float y = cardSample.getImageY().floatValue();

            float width = cardSample.getImageWidth().floatValue();
            float height = cardSample.getImageHeigth().floatValue();

            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
            contentStream.drawImage(image, x, y, width, height);
            contentStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return document;
    }

    public PDDocument displayPDFFromDataBase(byte[] pdfFileFromDataBase) {
        PDDocument document = null;
        try {
            if (pdfFileFromDataBase != null) document = Loader.loadPDF(pdfFileFromDataBase);
            return document;
        } catch (IOException e) {
            log.error("Erreur dans la methode :  displayPDFFromDataBase **** (byte[] pdfFileFromDataBase) ");
            throw new RuntimeException(e);
        }
    }

    public PDDocument deleteOnePage(PDDocument document) {
        if (document.getNumberOfPages() > 1) {
            document.removePage(1);
        }
        return document;
    }

    public Template miseAEchelleDesCoordoonees(Float facteur, Template template, Float fontSize) {
        //convertion des Millimeters en Points
        template.setSampleLongueur(template.getSampleLongueur() * facteur);
        template.setSampleLargeur(template.getSampleLargeur() * facteur);

        template.setImageX(template.getImageX() * facteur);
        template.setImageY(template.getImageY() * facteur);
        template.setImageWidth(template.getImageWidth() * facteur);
        template.setImageHeigth(template.getImageHeigth() * facteur);
        template.setMembreX(template.getMembreX() * facteur);
        template.setMembreY(template.getMembreY() * facteur);
        template.setNomX(template.getNomX() * facteur);
        template.setNomY(template.getNomY() * facteur);
        template.setPrenomX(template.getPrenomX() * facteur);
        template.setPrenomY(template.getPrenomY() * facteur);
        template.setQrX(template.getQrX() * facteur);
        template.setQrY(template.getQrY() * facteur);
        template.setQrWidth(template.getQrWidth() * facteur);
        template.setQrHeight(template.getQrHeight() * facteur);
        template.setMatriculeX(template.getMatriculeX() * facteur);
        template.setMatriculeY(template.getMatriculeY() * facteur);
        template.setDateDelivranceX(template.getDateDelivranceX() * facteur);
        template.setDateDelivranceY(template.getDateDelivranceY() * facteur);
        template.setDateExpirationX(template.getDateExpirationX() * facteur);
        template.setDateExpirationY(template.getDateExpirationY() * facteur);

        // calcul des dimensions en fonction de la police sélectionnée
        template.setImageY(template.getSampleLargeur() - template.getImageY() - template.getImageHeigth());
        template.setNomY(template.getSampleLargeur() - template.getNomY() - fontSize);
        template.setPrenomY(template.getSampleLargeur() - template.getPrenomY() - fontSize);
        template.setQrY(template.getSampleLargeur() - template.getQrY());
        template.setMembreY(template.getSampleLargeur() - template.getMembreY() - fontSize + 2f);
        template.setMatriculeY(template.getSampleLargeur() - template.getMatriculeY() - fontSize + 2f);
        template.setDateDelivranceY(template.getSampleLargeur() - template.getDateDelivranceY() - fontSize);
        template.setDateExpirationY(template.getSampleLargeur() - template.getDateExpirationY() - fontSize);

        return template;
    }

    public void printTextInPDF(float fontSize, float xPos, float yPos, String text, PDDocument document, String fontPath) {
        try {
            PDPage page = document.getPage(0);
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            PDType0Font font = PDType0Font.load(document, PDFServiceImpl.class.getResourceAsStream(fontPath));
            contentStream.setFont(font, fontSize);
            contentStream.beginText();
            contentStream.newLineAtOffset(xPos, yPos);
            contentStream.showText(text);
            contentStream.endText();
            contentStream.close();
        } catch (IOException e) {
            log.error("Error adding text: " + text);
            throw new RuntimeException(e);
        }
    }
}
