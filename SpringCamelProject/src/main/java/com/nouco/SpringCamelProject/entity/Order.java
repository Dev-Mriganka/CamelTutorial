package com.nouco.SpringCamelProject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class Order {

    @XmlAttribute
    private int id;
    @XmlAttribute
    private String partName;
    @XmlAttribute
    private int amount;
    @XmlAttribute
    private String customerName;
}
