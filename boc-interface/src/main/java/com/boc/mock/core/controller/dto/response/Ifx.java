package com.boc.mock.core.controller.dto.response;

import lombok.Data;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "IFX")
@XmlAccessorType(XmlAccessType.FIELD)
public class Ifx {

    @XmlElement(name = "GeneralStatus")
    private GeneralStatus generalStatus;

    @XmlElement(name = "details")
    private Details details;

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class GeneralStatus {

        @XmlElement(name = "StatusCode")
        private String statusCode;

        @XmlElement(name = "StatusLevel")
        private String statusLevel;
        @XmlElement(name = "StatusDesc")
        private String statusDesc;
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Details {
        @XmlAttribute(name = "rcode")
        private String rcode;

        @XmlAttribute(name = "rdesc")
        private String rdesc;

        @XmlElement(name = "body")
        private Body body;
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Body {
        @XmlElement(name = "name")
        private String name;

        @XmlElement(name = "prod")
        private String prod;

        @XmlElement(name = "type")
        private String type;

        @XmlElement(name = "branch")
        private String branch;

        @XmlElement(name = "brnname")
        private String brnname;

        @XmlElement(name = "currency")
        private int currency;

        @XmlElement(name = "curname")
        private String curname;

        @XmlElement(name = "cubal")
        private String cubal;

        @XmlElement(name = "avbal")
        private String avbal;

        @XmlElement(name = "fobal")
        private String fobal;

        @XmlElement(name = "hobal")
        private String hobal;

        @XmlElement(name = "crbal")
        private String crbal;
    }
}
