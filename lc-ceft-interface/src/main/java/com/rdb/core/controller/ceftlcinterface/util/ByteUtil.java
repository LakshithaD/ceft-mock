// *************************************************************************************************
// *************************************************************************************************
// PRODUCT : ceft-suite
// CLASS : ByteUtil
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

package com.rdb.core.controller.ceftlcinterface.util;

public class ByteUtil {

    /**
     * The method will return the length of bytes by decoding hex encoded length
     * bytes
     *
     * @param bytes
     *            length bytes.
     * @return Length value of bytes
     */
    public static int getLength(byte[] bytes) {

        int[] a = new int[4];
        for (int i = 0; i < bytes.length; i++) {

            if (bytes[i] < 0) {

                byte[] tmp = { 0, 0, 0, bytes[i] };
                a[i] = decode(tmp);
            } else {

                a[i] = bytes[i];
            }
        }
        int length = a[3] + a[2] * 256 + a[1] * 65536 + a[0] * 16777216;
        return length;
    }

    private static int decode(byte[] bi) {

        return bi[3] & 0xFF | (bi[2] & 0xFF) << 8 | (bi[1] & 0xFF) << 16
                       | (bi[0] & 0xFF) << 24;
    }
}
