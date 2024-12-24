// *************************************************************************************************
// *************************************************************************************************
// PRODUCT : ceft-suite
// CLASS : ReversalMessage
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
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReversalMessage extends FinancialMessage {

    private MessageTypeIdentification originalTypeIdentification;

    /** Field 90 - 05-10 Original system trace audit number */
    private String orgStan;

    /** Field 90 - 11-20 Original Transmission date and time */
    private LocalDateTime orgTransmissionDateAndTime;

    /** Field 90 - 21-31 Original acquirer institution ID */
    private String orgAcquirerIdentification;

    /** Field 90 - 32-43 Original Retrieval Reference Number */
    private String orgRetRefNo;

    @Override
    public Boolean isRequest() {

        return getMessageTypeIdentification() == MessageTypeIdentification.REVERSAL_REQ;
    }

}
