package org.arthur.utils.string;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Converts a byte(s) to and from hex string, padded by 0.
 * @author arahumed
 */
public class HexConverter {
    private static final int CHAR_UNSIGNED_MAX_PLUS_ONE = 0x100;
    
    private static final String[] HEX_TABLE_UPPER;
    private static final String[] HEX_TABLE_LOWER;
    private static final Map<String, Byte> REVERSE_HEX_TABLE;
    
    static {
        HEX_TABLE_UPPER = new String[CHAR_UNSIGNED_MAX_PLUS_ONE];
        HEX_TABLE_LOWER = new String[CHAR_UNSIGNED_MAX_PLUS_ONE];
        REVERSE_HEX_TABLE = new HashMap<>();
        
        Collections.unmodifiableMap(REVERSE_HEX_TABLE);
        
        for (int i = 0; i < CHAR_UNSIGNED_MAX_PLUS_ONE; i++){
            HEX_TABLE_UPPER[i] = String.format("%02X", i);
            HEX_TABLE_LOWER[i] = String.format("%02x", i);
            REVERSE_HEX_TABLE.put(HEX_TABLE_UPPER[i], (byte)i);
            REVERSE_HEX_TABLE.put(HEX_TABLE_LOWER[i], (byte)i);
        }
    }
    
    public static String getHex(int i, boolean upperCase){
        if (upperCase){
            return getHex(i, HEX_TABLE_UPPER);
        } else {
            return getHex(i, HEX_TABLE_LOWER);
        }
    }
    
    /**
     * Convert a byte into hex String.
     * @param i
     * @return 
     */
    private static String getHex(int i, String[] table){
        return table[i & 0xFF];
    }
    
    /**
     * Convert a hex string to single byte character. 
     * We assume that the format of the string looks like 00 01 ... FF
     * Throws illegalArgumentException if conversion is not possible.
     * @param hex
     * @return 
     */
    public static byte getByte(String hex){
        Byte value = REVERSE_HEX_TABLE.get(hex);
        if (value == null){
            throw new IllegalArgumentException("Expected format of string \"00\", \"01\", ..., \"FF\"");
        }
        return value;
    }
        
    /**
     * Return a hex string converted from a byte array.
     * @param array array to be converted to Hex.
     * @param delimiter delimiter to after each byte converted. 
     * Can be null for no delimiter.
     * @return 
     */
    public static String getHexes(byte[] array, Character delimiter, boolean upperCase){
        StringBuilder sb = new StringBuilder();
        
        String[] charTable = (upperCase)? HEX_TABLE_UPPER: HEX_TABLE_LOWER;
        
        for (int i = 0; i < array.length; i++){
            sb.append(getHex(array[i], charTable));
            if (delimiter != null){
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }
    
    /**
     * Convert hex string back to byte array.
     * Throws illegalArgumentException if conversion is not possible.
     * @param fromHex hex string to be converted
     * @param delimiter single character delimiter to add. Can be null for no delimiter.
     * @return 
     */
    public static byte[] getBytes(String fromHex, Character delimiter){
        if (delimiter != null){
            StringTokenizer st = new StringTokenizer(fromHex, delimiter.toString());
            final int totalTokens = st.countTokens();
            final byte[] values = new byte[totalTokens];
            for (int i = 0; i < totalTokens; i++){
                String next = st.nextToken();
                values[i] = getByte(next);
            }
            return values;
        } else {
            if ((fromHex.length() & 1) != 0){
                throw new IllegalArgumentException("Hex String length must be even.");
            }
            
            final byte[] values = new byte[fromHex.length() >> 1];
            
            for (int i = 0; i < fromHex.length(); i+=2 ){
                String subStr = fromHex.substring(i, i+2);
                values[i >> 1] = getByte(subStr);
            }
            return values;
        }
    }
    
    
}
