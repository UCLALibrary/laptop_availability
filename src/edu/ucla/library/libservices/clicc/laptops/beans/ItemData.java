package edu.ucla.library.libservices.clicc.laptops.beans;

import javax.xml.bind.annotation.*;

@XmlType(name = "item_data")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemData {

    @XmlElement(name = "barcode")
    private String barcode;
    @XmlElement(name = "base_status")
    private String status;
    @XmlElement(name = "physical_material_type")
    private String type;
    @XmlElement(name = "library")
    private String location;

    public ItemData() {
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
