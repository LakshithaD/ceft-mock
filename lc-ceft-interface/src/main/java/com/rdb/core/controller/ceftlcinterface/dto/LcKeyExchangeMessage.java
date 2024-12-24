// *************************************************************************************************
// *************************************************************************************************
// PRODUCT : ceft-suite
// CLASS : KeyExchangeMessage
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

import com.rdb.core.controller.ceftlcinterface.enums.NetworkMgtCode;

public class LcKeyExchangeMessage extends NetworkMessage {

    public LcKeyExchangeMessage() {

        this.networkMgtCode = NetworkMgtCode.KEY_MESSAGE_LC;
    }
}
