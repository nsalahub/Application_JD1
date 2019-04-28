package com.gmail.nsalahub.servises;

import com.gmail.nsalahub.servises.model.xml.XMLItemDTO;

import javax.servlet.http.Part;
import java.io.File;
import java.util.List;

public interface XMLOperationService {

    boolean isValid(File xsdFile, File xmlFile);

    File getFile(Part part, String fileNameUpload);

    List<XMLItemDTO> performParse(File file);
}
