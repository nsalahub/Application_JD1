package com.gmail.nsalahub.servises.model.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "Item")
public class XMLItemDTO {

    private String name;
    private String description;
    private String uniqueNumber;
    private BigDecimal price;

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "description")
    public void setDescription(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    @XmlElement(name = "price")
    public void setUniqueNumber(String uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    @XmlElement(name = "uniqueNumber")
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }


}
