package com.linkdoan.backend.model.temp;


import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Document;
import org.springframework.stereotype.Service;
import org.docx4j.Docx4J;
import java.io.File;
import java.io.InputStream;
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

        documentPart.setJaxbElement((Document) obj);

        File exportFile = new File("welcome.docx");
        wordMLPackage.save(exportFile);
        return exportFile.getName();
    }

}