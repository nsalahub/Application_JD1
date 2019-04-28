package com.gmail.nsalahub.servlet;

import com.gmail.nsalahub.servises.ItemService;
import com.gmail.nsalahub.servises.XMLOperationService;
import com.gmail.nsalahub.servises.impl.ItemServiceImpl;
import com.gmail.nsalahub.servises.impl.XMLOperationServiceImpl;
import com.gmail.nsalahub.servises.model.xml.XMLItemDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig
public class UploadServlet extends HttpServlet {


    private XMLOperationService xmlOperationService = XMLOperationServiceImpl.getInstance();
    private ItemService itemService = ItemServiceImpl.getInstance();

    private static final String SCHEMA_FILE_PATH = File.separator +"resources" +File.separator +"schema.xsd" ;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        System.out.println("File name " + fileName);
        String schemaPath = req.getServletContext().getRealPath(SCHEMA_FILE_PATH);
        System.out.println();
        if (filePart != null) {
            try (InputStream inputStream = filePart.getInputStream()) {
                File tempFile = getFileFromStream(inputStream);
                File fileSchema = new File(schemaPath);
                if (xmlOperationService.isValid(fileSchema, tempFile)) {
                    List<XMLItemDTO> xmlItemDTOS = xmlOperationService.performParse(tempFile);
                    for (XMLItemDTO xmlItemDTO : xmlItemDTOS) {
                        itemService.insertXMLItem(xmlItemDTO);
                    }
                    req.setAttribute("error", "success entered items");
                    resp.sendRedirect("/app/dispatcher?command=showxml");
                } else {
                    req.setAttribute("error", "file is not valid");
                    resp.sendRedirect("/app/dispatcher?command=showxml");
                }
            }catch (IOException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        } else {
            req.setAttribute("error","file path not fund");
            resp.sendRedirect("/app/dispatcher?command=showitems");
        }
    }

    private File getFileFromStream(InputStream inputStream) {
        File file = new File("tempFile");
        System.out.println("----------Start getting file-----");
        try (OutputStream outputStream = new FileOutputStream(file)) {
            int input;
            while ((input = inputStream.read()) != -1) {
                outputStream.write(input);
            }
            outputStream.flush();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("---------file error");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("stream problem");
            System.out.println(e.getMessage());
        }
        return file;
    }
}
