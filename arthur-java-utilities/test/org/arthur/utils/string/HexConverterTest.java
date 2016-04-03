/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arthur.utils.string;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author arahumed
 */
public class HexConverterTest {
    
    /**
     * Test conversion without delimiter
     */
    @Test
    public void encodeHex1Upper(){
        String text = "Hello world\n";
        byte[] b = text.getBytes();
        
        String hexes = HexConverter.getHexes(b, null, true);
        Assert.assertEquals(hexes, "48656c6c6f20776f726c640a".toUpperCase());
    }
    
    @Test
    public void encodeHex1Lower(){
        String text = "Hello world\n";
        byte[] b = text.getBytes();
        
        String hexes = HexConverter.getHexes(b, null, false);
        Assert.assertEquals(hexes, "48656c6c6f20776f726c640a".toLowerCase());
    }
    
    
    @Test
    public void stringToBytes1Upper(){
        String hex = "48656c6c6f20776f726c640a".toUpperCase();
        byte[] b = HexConverter.getBytes(hex, null);
        String s = new String (b);
        Assert.assertEquals("Hello world\n", s);
    }
    
    @Test
    public void stringToBytes1Lower(){
        String hex = "48656c6c6f20776f726c640a".toLowerCase();
        byte[] b = HexConverter.getBytes(hex, null);
        String s = new String (b);
        Assert.assertEquals("Hello world\n", s);
    }
    
    /**
     * Test conversion with delimiter
     */
    @Test
    public void encodeHex2(){
        String text = "Hello world\n";
        byte[] b = text.getBytes();
        
        String hexes = HexConverter.getHexes(b, ' ', true);
        Assert.assertEquals(hexes, "48 65 6c 6c 6f 20 77 6f 72 6c 64 0a ".toUpperCase());
    }
    
    
    @Test
    public void stringToBytes2(){
        String hex = "48 65 6c 6c 6f 20 77 6f 72 6c 64 0a ".toUpperCase();
        byte[] b = HexConverter.getBytes(hex, ' ');
        String s = new String (b);
        Assert.assertEquals("Hello world\n", s);
    }

}
