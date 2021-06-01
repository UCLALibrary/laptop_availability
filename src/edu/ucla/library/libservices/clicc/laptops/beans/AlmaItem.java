package edu.ucla.library.libservices.clicc.laptops.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class AlmaItem {

    @XmlElement(name = "bib_data")
    private BibData bib;
    @XmlElement(name = "holding_data")
    private HoldingData holding;
    @XmlElement(name = "item_data")
    private ItemData item;

    public AlmaItem() {
    }

    public BibData getBib() {
        return bib;
    }

    public void setBib(BibData bib) {
        this.bib = bib;
    }

    public HoldingData getHolding() {
        return holding;
    }

    public void setHolding(HoldingData holding) {
        this.holding = holding;
    }

    public ItemData getItem() {
        return item;
    }

    public void setItem(ItemData item) {
        this.item = item;
    }
}
