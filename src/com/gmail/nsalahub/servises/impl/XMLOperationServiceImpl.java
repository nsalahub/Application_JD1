package com.gmail.nsalahub.servises.impl;

import com.gmail.nsalahub.servises.XMLOperationService;
import com.gmail.nsalahub.servises.model.xml.XMLItemsDTO;
import org.xml.sax.SAXException;

import javax.servlet.http.Part;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.util.List;

import com.gmail.nsalahub.servises.model.xml.XMLItemDTO;

public class XMLOperationServiceImpl implements XMLOperationService {

    private static XMLOperationServiceImpl instance;

    private XMLOperationServiceImpl() {
    }

    public static XMLOperationServiceImpl getInstance() {
        if (instance == null) {
            instance = new XMLOperationServiceImpl();
        }
        return instance;
    }



    @Override
    public File getFile(Part part, String fileNameUpload) {
        File fileParse = new File(fileNameUpload);
        try (InputStream fileContent = part.getInputStream(); OutputStream os = new FileOutputStream(fileParse)) {
            int input;
            while ((input = fileContent.read()) != -1) {
                os.write(input);
            }
            os.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("file dot't create");
            e.printStackTrace();
        }
        return fileParse;
    }

    @Override
    public List<XMLItemDTO> performParse(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(XMLItemsDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            XMLItemsDTO xmlItemsDTO = (XMLItemsDTO) unmarshaller.unmarshal(file);
            return xmlItemsDTO.getXmlItemDTOList();
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    public boolean isValid(File scheme, File xmlFile) {
        Source xmlSource = new StreamSource(xmlFile);
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(scheme);
            Validator validator = schema.newValidator();
            validator.validate(xmlSource);
            System.out.println(xmlSource.getSystemId() + " is valid");
            return true;
        } catch (SAXException e) {
            e.printStackTrace();
            System.out.println(xmlSource.getSystemId() + " is NOT valid reason:" + e);
            return false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
