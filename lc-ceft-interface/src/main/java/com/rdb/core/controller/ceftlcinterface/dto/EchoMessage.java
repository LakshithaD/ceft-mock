// *************************************************************************************************
// *************************************************************************************************
// PRODUCT : ceft-suite
// CLASS : NetworkMessage
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
import com.rdb.core.controller.ceftlcinterface.enums.NetworkMgtCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EchoMessage extends NetworkMessage {

    public EchoMessage() {

        this.networkMgtCode = NetworkMgtCode.ECHO_TEST;
    }

    @Override
    public Boolean isRequest() {

        return getMessageTypeIdentification() == MessageTypeIdentification.NETWORK_MGT_REQ;
    }
}
