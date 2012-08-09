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

package com.tailf.jnc;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


/**
 * A String wrapper extended by built in String based types.
 * <p>
 * White space collapse and replace methods, regexp pattern matchers, and
 * length assertion methods are provided.
 * 
 * @author emil@tail-f.com
 */
public class BaseString extends YangType<String> {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a BaseString object from a java.lang.String.
     * 
     * @param value The Java String.
     * @throws YangException If an invariant was broken during assignment.
     */
    public BaseString(String value) throws YangException {
        setValue(value);
    }

    /**
     * Sets the value of this object using a java.lang.String.
     * 
     * @param value The Java String.
     * @throws YangException If an invariant was broken during assignment.
     */
    @Override
    public void setValue(String value) throws YangException {
        this.value = value;
        check();
    }

    /**
     * Identity method provided because this class extends the YangType class.
     * 
     * @param s A string.
     * @return s.
     */
    @Override
    protected String fromString(String s) {
        return s;
    }

    /**
     * Nop method provided because this class extends the YangType class.
     */
    @Override
    public void check() throws YangException {
    }

    /**
     * Compares type of obj with this object to see if they can be equal.
     * 
     * @param obj Object to compare type with.
     * @return true if obj is an instance of BaseString or java.lang.String;
     *         false otherwise.
     */
    @Override
    public boolean canEqual(Object obj) {
        return obj instanceof BaseString;
    }

    /* ---------- Restrictions ---------- */

    /**
     * Checks that a regular expression matches the value of this object.
     * 
     * @param regex The regular expression.
     * @throws YangException If regexp has a syntax error or does not match.
     */
    protected void pattern(String regex) throws YangException {
        pattern(new String[] {regex});
    }

    /**
     * Checks that a set of regular expressions match the value of this object.
     * 
     * @param regexes The regular expressions.
     * @throws YangException If any regexp in regexes has a syntax error or
     *         does not match.
     */
    protected void pattern(String[] regexes) throws YangException {
        Object opaqueData = value;
        boolean matches = true;
        try {
            for (int i = 0; i < regexes.length; i++)
                if (!(matches = Pattern.matches(regexes[i], value)))
                    break;
        } catch (PatternSyntaxException e) {
            opaqueData = e;
            matches = false;
        }
        YangException.throwException(!matches, opaqueData);
    }

    /**
     * Whitespace replace. Replaces all occurrences of #x9 (tab), #xA (line
     * feed), and #xD (CR) with #x20 (space).
     */
    protected void wsReplace() {
        value = Utils.wsReplace(value);
    }

    /**
     * Whitespace replace. Contiguous sequences of 0x20 are collapsed into a
     * single 0x20, and initial and/or final 0x20s are deleted.
     */
    protected void wsCollapse() {
        value = Utils.wsCollapse(value);
    }

}