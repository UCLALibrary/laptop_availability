package edu.ucla.library.libservices.clicc.laptops.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType( XmlAccessType.FIELD )
public class AvailableItems
{
  @XmlElement( name = "location" )
  private String location;
  @XmlElement( name = "chromeBooks" )
  private int chromeBooks;
  @XmlElement( name = "macs" )
  private int macs;
  @XmlElement( name = "windows" )
  private int windows;
  @XmlElement( name = "iPads" )
  private int iPads;

  public AvailableItems()
  {
    super();
  }

  public void setLocation( String location )
  {
    this.location = location;
  }

  public String getLocation()
  {
    return location;
  }

  public void setChromeBooks( int chromeBooks )
  {
    this.chromeBooks = chromeBooks;
  }

  public int getChromeBooks()
  {
    return chromeBooks;
  }

  public void setMacs( int macs )
  {
    this.macs = macs;
  }

  public int getMacs()
  {
    return macs;
  }

  public void setWindows( int windows )
  {
    this.windows = windows;
  }

  public int getWindows()
  {
    return windows;
  }

  public void setIPads( int iPads )
  {
    this.iPads = iPads;
  }

  public int getIPads()
  {
    return iPads;
  }
}
