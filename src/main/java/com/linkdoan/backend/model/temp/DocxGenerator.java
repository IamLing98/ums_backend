package com.linkdoan.backend.model.temp;


import org.apache.log4j.Level;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.docx4j.XmlUtils;
import org.docx4j.convert.out.pdf.viaXSLFO.PdfSettings;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Document;
import org.springframework.stereotype.Service;
//import org.docx4j.Docx4J;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

@Service
public class DocxGenerator {

    private static final String TEMPLATE_NAME = "template.docx";

    public String generateDocxFileFromTemplate(UserInformation userInformation) throws Exception {

        InputStream templateInputStream = this.getClass().getClassLoader().getResourceAsStream(TEMPLATE_NAME);

        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateInputStream);

        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

        HashMap<String, String> variables = new HashMap<>();
        variables.put("firstName", userInformation.getFirstName());
        variables.put("lastName", userInformation.getLastName());
        variables.put("salutation", userInformation.getSalutation());
        variables.put("message", userInformation.getMessage());

        String xml = XmlUtils.marshaltoString(documentPart.getJaxbElement(), true);
        Object obj = XmlUtils.unmarshallFromTemplate(xml, variables);
        org.docx4j.convert.out.pdf.viaXSLFO.Conversion.log.setLevel(Level.OFF);
        documentPart.setJaxbElement((Document) obj);
        org.docx4j.convert.out.pdf.PdfConversion conversion = new org.docx4j.convert.out.pdf.viaXSLFO.Conversion(
                wordMLPackage);
        OutputStream exportFile = new FileOutputStream(new File("pdf.pdf"));
        PdfSettings pdfSettings = new PdfSettings();
        conversion.output(exportFile, pdfSettings);
        return exportFile.toString();
    }

}