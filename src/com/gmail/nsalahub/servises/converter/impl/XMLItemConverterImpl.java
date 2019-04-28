package com.gmail.nsalahub.servises.converter.impl;

import com.gmail.nsalahub.reposetory.model.XMLItem;
import com.gmail.nsalahub.servises.converter.XMLItemConverter;
import com.gmail.nsalahub.servises.model.xml.XMLItemDTO;

public class XMLItemConverterImpl  implements XMLItemConverter {

    private static XMLItemConverterImpl instance;

    private XMLItemConverterImpl() {
    }

    public static XMLItemConverterImpl getInstance(){
        if (instance == null){
            instance = new XMLItemConverterImpl();
        }
        return instance;
    }


    @Override
    public XMLItem fromDTO(XMLItemDTO xmlItemDTO) {
        XMLItem xmlItem = new XMLItem();
        xmlItem.setName(xmlItemDTO.getName());
        xmlItem.setDescription(xmlItemDTO.getDescription());
        xmlItem.setUniqueNumber(xmlItemDTO.getUniqueNumber());
        xmlItem.setPrice(xmlItemDTO.getPrice());
        return xmlItem;
    }

    @Override
    public XMLItemDTO toDTO(XMLItem xmlItem) {
        XMLItemDTO xmlItemDTO = new XMLItemDTO();
        xmlItemDTO.setName(xmlItem.getName());
        xmlItemDTO.setPrice(xmlItem.getPrice());
        xmlItemDTO.setUniqueNumber(xmlItemDTO.getUniqueNumber());
        xmlItemDTO.setDescription(xmlItemDTO.getDescription());
        return null;
    }
}
