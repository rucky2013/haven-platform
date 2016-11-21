/*
 * Copyright 2016 Code Above Lab LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codeabovelab.dm.common.utils;

import java.io.Serializable;
import java.util.function.IntPredicate;

/**
 */
public class StringUtils {
    private StringUtils() {
    }

    public static String before(String s, char c) {
        int pos = s.indexOf(c);
        if(pos < 0) {
            throw new IllegalArgumentException("String '" + s + "' must contains '" + c + "'.");
        }
        return s.substring(0, pos);
    }

    public static String after(String s, char c) {
        int pos = s.indexOf(c);
        if(pos < 0) {
            throw new IllegalArgumentException("String '" + s + "' must contains '" + c + "'.");
        }
        return s.substring(pos + 1);
    }

    public static String afterLast(String s, char c) {
        int pos = s.lastIndexOf(c);
        if(pos < 0) {
            throw new IllegalArgumentException("String '" + s + "' must contains '" + c + "'.");
        }
        return s.substring(pos + 1);
    }

    /**
     * Split string into two pieces at last appearing of delimiter.
     * @param s string
     * @param c delimiter
     * @return null if string does not contains delimiter
     */
    public static String[] splitLast(String s, char c) {
        int pos = s.lastIndexOf(c);
        if(pos < 0) {
            return null;
        }
        return new String[] {s.substring(0, pos), s.substring(pos + 1)};
    }

    /**
     * Split string into two pieces at last appearing of delimiter.
     * @param s string
     * @param delimiter delimiter
     * @return null if string does not contains delimiter
     */
    public static String[] splitLast(String s, String delimiter) {
        int pos = s.lastIndexOf(delimiter);
        if(pos < 0) {
            return null;
        }
        return new String[] {s.substring(0, pos), s.substring(pos + delimiter.length())};
    }

    /**
     * Return string which contains only chars for which charJudge give true.
     * @param src source string, may be null
     * @param charJudge predicate which consume codePoint (not chars)
     * @return string, null when incoming string is null
     */
    public static String retain(String src, IntPredicate charJudge) {
        if (src == null) {
            return null;
        }
        final int length = src.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int cp = src.codePointAt(i);
            if(charJudge.test(cp)) {
                sb.appendCodePoint(cp);
            }
        }
        return sb.toString();
    }

    /**
     * Retain only characters which is {@link #isAz09(int)}
     * @param src source string, may be null
     * @return string, null when incoming string is null
     */
    public static String retainAz09(String src) {
        return retain(src, StringUtils::isAz09);
    }

    /**
     * Retain chars which is acceptable as file name or part of url on most operation systems. <p/>
     * It: <code>'A'-'z', '0'-'9', '_', '-', '.'</code>
     * @param src source string, may be null
     * @return string, null when incoming string is null
     */
    public static String retainForFileName(String src) {
        return retain(src, StringUtils::isAz09);
    }

    /**
     * Test that specified codePoint is an ASCII letter or digit
     * @param cp codePoint
     * @return true for specified chars
     */
    public static boolean isAz09(int cp) {
        return cp >= '0' && cp <= '9' ||
               cp >= 'a' && cp <= 'z' ||
               cp >= 'A' && cp <= 'Z';
    }

    /**
     * Test that specified codePoint is an ASCII letter, digit or hyphen '-'.
     * @param cp codePoint
     * @return true for specified chars
     */
    public static boolean isAz09Hyp(int cp) {
        return isAz09(cp) || cp == '-';
    }


    /**
     * Chars which is acceptable as file name or part of url on most operation systems. <p/>
     * It: <code>'A'-'z', '0'-'9', '_', '-', '.'</code>
     * @param cp codePoint
     * @return true for specified chars
     */
    public static boolean isForFileName(int cp) {
        return isAz09(cp) || cp == '-' || cp == '_' || cp == '.';
    }

    /**
     * Invoke {@link Object#toString()} on specified argument, if arg is null then return null.
     * @param o
     * @return null or result of o.toString()
     */
    public static String valueOf(Object o) {
        return o == null? null : o.toString();
    }

    /**
     * Test that each char of specified string match for predicate. <p/>
     * Note that it method does not support unicode, because it usual applicable only for match letters that placed under 128 code.
     * @param str string
     * @param predicate char matcher
     * @return true if all chars match
     */
    public static boolean match(String str, IntPredicate predicate) {
        final int len = str.length();
        for(int i = 0; i < len; i++) {
            if(!predicate.test(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Is a <code>match(str, StringUtils::isAz09);</code>.
     * @param str string
     * @return true if string match [A-Za-z0-9]*
     */
    public static boolean matchAz09(String str) {
        return match(str, StringUtils::isAz09);
    }

    /**
     * Is a <code>match(str, StringUtils::isAz09Hyp);</code>.
     * @param str string
     * @return true if string match [A-Za-z0-9-]*
     */
    public static boolean matchAz09Hyp(String str) {
        return match(str, StringUtils::isAz09Hyp);
    }
}
