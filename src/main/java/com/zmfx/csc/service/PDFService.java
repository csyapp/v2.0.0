package com.zmfx.csc.service;

import com.zmfx.csc.domain.Carte;
import java.io.File;

public interface PDFService {
    File combinePDFPagesToByteArray(Float fontSize, Carte card);

    void deletePreviousGeneratedPdfFiled();
}
