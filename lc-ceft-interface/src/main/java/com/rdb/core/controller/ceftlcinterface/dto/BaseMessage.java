// *************************************************************************************************
// *************************************************************************************************
// PRODUCT : ceft-suite
// CLASS : BaseMessage
// ************************************************************************************************
//
// Copyright(C) 2019 SprintLabs (Pvt) Ltd.
// All rights reserved.
//
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF
// SprintLabs (Pvt) Ltd.
//
// This copy of the Source Code is intended for SprintLabs (Pvt) Ltd 's internal use only
// and is
// intended for view by persons duly authorized by the management of SprintLabs (Pvt) Ltd. 
//No part of this file may be reproduced or distributed in any form or by any
// means without the written approval of the Management of SprintLabs (Pvt) Ltd.
//
// *************************************************************************************************

package com.rdb.core.controller.ceftlcinterface.dto;

import com.rdb.core.controller.ceftlcinterface.enums.MessageTypeIdentification;
import com.rdb.core.controller.ceftlcinterface.enums.ResponseCode;
import com.solab.iso8583.IsoMessage;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseMessage {

    /** The message type identification is the message type identification*/
    protected MessageTypeIdentification messageTypeIdentification;

    /** Field 7 format (MMDDhhmmss) */
    protected LocalDateTime transmissionDateAndTime;

    /** Field 11 System Trace Audit Number */
    protected String stan;

//    /** Field 15 format (MMDD) */
//    protected LocalDate settlementDate;

//    /** Field 32 Acquiring Institution Identification Code */
//    protected String acquirerIdetification;

    /** Field 39 Response Code */
    protected ResponseCode responseCode;

    private IsoMessage requestIsoMessage;

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString( this, ToStringStyle.JSON_STYLE);
    }

    public abstract Boolean isRequest();



}
