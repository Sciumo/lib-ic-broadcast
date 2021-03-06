/*
 *   Copyright (C) 2012 Caulfield IP Holdings (Caulfield)
 *   and/or its affiliates.
 *   All rights reserved. Use is subject to license terms.
 *
 *   Software Code is protected by Caulfield Copyrights. Caulfield hereby
 *   reserves all rights in and to Caulfield Copyrights and no license is
 *   granted under Caulfield Copyrights in this Software License Agreement.
 *   Caulfield generally licenses Caulfield Copyrights for commercialization
 *   pursuant to the terms of either Caulfield's Standard Software Source Code
 *   License Agreement or Caulfield's Standard Product License Agreement.
 *
 *   A copy of Caulfield's either License Agreement can be obtained on request
 *   by email from: info@caufield.org.
 */
package ca.gc.ic.broadcast.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.*;

/**
 *
 * @author jesse
 */
@Entity
@Table(name = "contours")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://ca.gc.ic/broadcast/entity")
@NamedQueries({
  @NamedQuery(name = "Contour.findAll", query = "SELECT c FROM Contour c"),
  @NamedQuery(name = "Contour.findByCallsBanr", query = "SELECT c FROM Contour c WHERE c.contourPK.callsBanr = :callsBanr"),
  @NamedQuery(name = "Contour.findByAzimuth", query = "SELECT c FROM Contour c WHERE c.contourPK.azimuth = :azimuth"),
  @NamedQuery(name = "Contour.findByValuDist", query = "SELECT c FROM Contour c WHERE c.contourPK.valuDist = :valuDist"),
  @NamedQuery(name = "Contour.findByName", query = "SELECT c FROM Contour c WHERE c.name = :name"),
  @NamedQuery(name = "Contour.findByLatEnd", query = "SELECT c FROM Contour c WHERE c.latEnd = :latEnd"),
  @NamedQuery(name = "Contour.findByLongEnd", query = "SELECT c FROM Contour c WHERE c.longEnd = :longEnd")})
public class Contour implements Serializable {

  @XmlTransient
  private static final long serialVersionUID = 1L;
  @EmbeddedId
  protected ContourPK contourPK;
  @Column(name = "name", length = 4)
  @XmlAttribute
  private String name;
  @Column(name = "lat_end", precision = 12)
  @XmlAttribute
  private double latEnd;
  @Column(name = "long_end", precision = 12)
  @XmlAttribute
  private double longEnd;
  @JoinColumns({
    @JoinColumn(name = "call_sign", referencedColumnName = "call_sign"),
    @JoinColumn(name = "banner", referencedColumnName = "banner")})
  @ManyToOne
  @XmlTransient
  private CanadaStation canadaStation;

  public Contour() {
  }

  public Contour(ContourPK contourPK) {
    this.contourPK = contourPK;
  }

  public Contour(String callsBanr, double azimuth, double valuDist) {
    this.contourPK = new ContourPK(callsBanr, azimuth, valuDist);
  }

  public ContourPK getContourPK() {
    return contourPK;
  }

  public void setContourPK(ContourPK contourPK) {
    this.contourPK = contourPK;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getLatEnd() {
    return latEnd;
  }

  public void setLatEnd(double latEnd) {
    this.latEnd = latEnd;
  }

  public double getLongEnd() {
    return longEnd;
  }

  public void setLongEnd(double longEnd) {
    this.longEnd = longEnd;
  }

  public CanadaStation getCanadaStation() {
    return canadaStation;
  }

  public void setCanadaStation(CanadaStation canadaStation) {
    this.canadaStation = canadaStation;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (contourPK != null ? contourPK.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {

    if (!(object instanceof Contour)) {
      return false;
    }
    Contour other = (Contour) object;
    if ((this.contourPK == null && other.contourPK != null) || (this.contourPK != null && !this.contourPK.equals(other.contourPK))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "ca.gc.ic.broadcast.entity.Contour[ contourPK=" + contourPK + " ]";
  }
}
