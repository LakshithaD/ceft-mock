// *************************************************************************************************
// *************************************************************************************************
// PRODUCT : ceft-suite
// CLASS : StringUtil
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

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

public class StringUtil extends StringUtils {

    public static final String NEW_LINE = System.getProperty("line.separator");

    public static final String SPACE = " ";

    public static final String EMPTY = "";

    public static final String ZERO = "0";

    public static final String TAB = "\t";

    public static final String LESS_THAN = "<";

    public static final String GREATER_THAN = ">";

    public static final String FORWARD_SLASH = "/";

    public static final String BACK_SLASH = "\\";

    public static boolean isNotNullNorEmpty(String... args) {

        for (String arg : args) {

            if (arg == null || arg.isEmpty()) {

                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    public static String randomString( int len )
    {

        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

    public static String fill(String  str, int length) {

        StringBuilder buider = new StringBuilder(str);
        while (buider.length() < length) {

            buider.insert(0, "0");
        }
        return buider.toString();
    }
}
