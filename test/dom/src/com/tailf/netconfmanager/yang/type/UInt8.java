/*    -*- Java -*-
 *
 *  Copyright 2012 Tail-F Systems AB. All rights reserved.
 *
 *  This software is the confidential and proprietary
 *  information of Tail-F Systems AB.
 *
 *  $Id$
 *
 */

package com.tailf.netconfmanager.yang.type;

import com.tailf.netconfmanager.yang.YangException;

/**
 * Implements the built-in YANG data type "uint8".
 * 
 * @author emil@tail-f.com
 */
public class UInt8 extends Int16 {

    /**
     * Generated serial version UID, to be changed if this class is modified in
     * a way which affects serialization. Please see:
     * http://docs.oracle.com/javase/6/docs/platform/serialization/spec/version.html#6678
     */
    private static final long serialVersionUID = 6446944074038058498L;

    /**
     * Creates a YangUInt8 object from a String.
     * 
     * @param s The string.
     * @throws YangException If value could not be parsed from s or if the
     *                        parsed value is negative or larger than 0xff.
     */
    public UInt8(String s) throws YangException {
        super(s);
        setMinMax(0, 0xff);
        check();
    }

    /**
     * Creates a YangUInt8 object from a Number. This may involve rounding or
     * truncation.
     * 
     * @param value The initial value of the new YangUInt8 object.
     * @throws YangException If value is negative or larger than 0xff.
     */
    public UInt8(Number value) throws YangException {
        super(value);
        setMinMax(0, 0xff);
        check();
    }

    /** ---------- Restrictions ---------- */

    /*
     * (non-Javadoc)
     * @see com.tailf.netconfmanager.yang.type.Int#min(int)
     */
    @Override
    protected void min(int min) throws YangException {
        TypeUtil.restrict(value & 0xffffffffL, min & 0xffffffffL,
                TypeUtil.Operator.GR);
    }

    /*
     * (non-Javadoc)
     * @see com.tailf.netconfmanager.yang.type.Int#max(int)
     */
    @Override
    protected void max(int max) throws YangException {
        TypeUtil.restrict(value & 0xffffffffL, max & 0xffffffffL,
                TypeUtil.Operator.LT);
    }

}