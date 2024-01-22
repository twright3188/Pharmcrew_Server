package com.bumdori.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilsTest {

//    @Test
    public void removeHtml() {
        String s = "<html><body>1234<br>4567</body></html>";
        System.out.println(StringUtils.removeHtml(s));
    }
}