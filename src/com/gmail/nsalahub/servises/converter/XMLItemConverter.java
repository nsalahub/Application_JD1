package com.gmail.nsalahub.servises.converter;

import com.gmail.nsalahub.reposetory.model.XMLItem;
import com.gmail.nsalahub.servises.model.xml.XMLItemDTO;

public interface XMLItemConverter {

    XMLItem fromDTO(XMLItemDTO xmlItemDTO);

    XMLItemDTO toDTO (XMLItem xmlItem);
}
