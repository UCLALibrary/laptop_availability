package edu.ucla.library.libservices.clicc.laptops.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType( XmlAccessType.FIELD )
public class AvailableLaptop
{
  @XmlElement( name = "publicName" )
  private String publicName;
  @XmlElement( name = "availableCount" )
  private int availableCount;

  public AvailableLaptop()
  {
    super();
  }

  public void setPublicName( String publicName )
  {
    this.publicName = publicName;
  }

  public String getPublicName()
  {
    return publicName;
  }

  public void setAvailableCount( int availableCount )
  {
    this.availableCount = availableCount;
  }

  public int getAvailableCount()
  {
    return availableCount;
  }
  
  public String toString()
  {
    return getPublicName() + " has " + getAvailableCount() + " laptops available";
  }
}
