package com.linkdoan.backend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linkdoan.backend.config.FileStorageProperties;
import com.linkdoan.backend.exception.FileStorageException;
import com.linkdoan.backend.io.ExcelWrite;
import com.linkdoan.backend.model.SheetData;
import com.linkdoan.backend.model.Template;
import com.linkdoan.backend.repository.TemplateRepository;
import com.linkdoan.backend.service.DocumentGenerationService;
import org.apache.log4j.Level;
import org.docx4j.XmlUtils;
import org.docx4j.convert.out.pdf.viaXSLFO.PdfSettings;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class DocumentGenerationServiceImpl implements DocumentGenerationService {

    @Autowired
    TemplateRepository templateRepository;


    private final Path fileStorageLocation;

    @Autowired
    public DocumentGenerationServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String generatePdfFileByType(HashMap<String, String> variables, String templateName) throws Exception {

        InputStream templateInputStream = this.getClass().getClassLoader().getResourceAsStream(templateName + ".docx");

        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateInputStream);

        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

        String xml = XmlUtils.marshaltoString(documentPart.getJaxbElement(), true);
        Object obj = XmlUtils.unmarshallFromTemplate(xml, variables);
        org.docx4j.convert.out.pdf.viaXSLFO.Conversion.log.setLevel(Level.OFF);
        documentPart.setJaxbElement((Document) obj);
        org.docx4j.convert.out.pdf.PdfConversion conversion = new org.docx4j.convert.out.pdf.viaXSLFO.Conversion(
                wordMLPackage);
        Path targetLocation = this.fileStorageLocation.resolve(templateName + ".pdf");
        OutputStream exportFile = new FileOutputStream(new File(targetLocation.toString()));
        PdfSettings pdfSettings = new PdfSettings();
        conversion.output(exportFile, pdfSettings);
        return templateName + ".pdf";
    }

    @Override
    public String generatePdfFile(HashMap<String, String> variables, Long id) throws Exception {
        Optional<Template> templateOptional = templateRepository.findById(id);
        if (!templateOptional.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy template này");
        Template template = templateOptional.get();
        String templateName = template.getPathName();
        return generatePdfFileByType(variables, templateName);

    }

    private String generateExcelFileById(HashMap<String, Object> variables, String templateName) throws Exception {
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> map = oMapper.convertValue(variables.get("map"), Map.class);
        List<Object> objectList = oMapper.convertValue(variables.get("list"), ArrayList.class);
        System.out.println(objectList);
        SheetData sheetData = new SheetData(map, objectList, "Sheet1");

        Path targetLocation = this.fileStorageLocation.resolve(templateName);
        File output = new File(targetLocation.toString());
        OutputStream exportFile = new FileOutputStream(output);

        try {
            ExcelWrite.writeData(templateName, exportFile, sheetData);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return output.getName();
    }

    @Override
    public String generateExcelFile(HashMap<String, Object> variables, Long id) throws Exception {
        Optional<Template> templateOptional = templateRepository.findById(id);
        if (!templateOptional.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy template này");
        return generateExcelFileById(variables, templateOptional.get().getPathName() + ".xlsx");
    }

}
