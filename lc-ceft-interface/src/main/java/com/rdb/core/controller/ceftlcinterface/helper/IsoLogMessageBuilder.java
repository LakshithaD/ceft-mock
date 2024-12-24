// *************************************************************************************************
// *************************************************************************************************
// PRODUCT : ceft-suite
// CLASS : IsoLogMessageBuilder
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

package com.rdb.core.controller.ceftlcinterface.helper;

import com.rdb.core.controller.ceftlcinterface.util.StringUtil;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.IsoValue;

public abstract class IsoLogMessageBuilder {

    public static String format(IsoMessage message) {

        StringBuilder buider = new StringBuilder();
        buider.append( StringUtil.NEW_LINE);
        Integer decType = message.getType();
        String hexType = decType == null ? "" : Integer.toHexString(decType);
        buider.append("Message Type : " + decType + ", (" + hexType + ")");
        buider.append(StringUtil.NEW_LINE);
        int count = 0;
        for (int i = 0; i < 128; i++) {

            IsoValue<?> val = message.getField( i);
            if (val != null) {

                if (count > 0) {

                    buider.append(StringUtil.NEW_LINE);
                }
                buider.append("Field : " + i);
                buider.append(" Value" + " : " + val.getValue());
                buider.append("		Formatted 	" + " : " + val.toString());
                count++;
            }
        }
        return buider.toString();
    }
}
