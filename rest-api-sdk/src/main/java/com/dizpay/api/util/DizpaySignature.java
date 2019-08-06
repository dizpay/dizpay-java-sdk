package com.dizpay.api.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.*;

/**
 * dizpay signature
 */
public class DizpaySignature {
    /**
     * signature request parameters
     *
     * @param obj
     * @return
     */
    public static String signature(Object obj, String appId, String appKey) {
        String signature = "";
        try {
            Map<String, String> parametersMap = toMap(obj);
            parametersMap.put("app_id", appId);
            parametersMap.put("app_key", appKey);
            parametersMap.remove("signature");
            String url = formatUrlMap(parametersMap, false, false);
            signature = MD5Encode(url);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return signature;
    }

    /**
     * class field cast to map
     *
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, String> toMap(Object object) {
        Map<String, String> map = new HashMap();
        try {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                if ("serialVersionUID".equals(field.getName())) {
                    continue;
                }
                field.setAccessible(true);
                String dstName = camelToUnderline(field.getName());
                String val = String.valueOf(field.get(object));
                if (StringUtils.isNotBlank(val) && !"null".equalsIgnoreCase(val)) {
                    map.put(dstName, val);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return map;
    }

    /**
     * Method Purpose: md5 encode
     *
     * @param origin
     * @return
     */
    public static String MD5Encode(String origin) {
        String resultString = null;
        try {
            //md5加密
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(origin.getBytes("utf-8"));
            final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();
            StringBuilder ret = new StringBuilder(bytes.length * 2);
            for (int i = 0; i < bytes.length; i++) {
                ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
                ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
            }
            resultString = ret.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return resultString;
    }

    /**
     * Method Purpose: Sort all incoming parameters according to the Unicode code of the field name from small to large (dictionary order), and generate a url parameter string.<br>
     * Implementation steps: <br>
     *
     * @param paraMap    Map object to sort
     * @param urlEncode  Do you need URLENCODE?
     * @param keyToLower Do you need to convert Key to all lowercase?
     *                   true:Key is converted to lowercase，false:Not converting
     * @return
     */
    public static String formatUrlMap(Map<String, String> paraMap, boolean urlEncode, boolean keyToLower) {
        String buff = "";
        Map<String, String> tmpMap = paraMap;
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(tmpMap.entrySet());
            // Sort all incoming parameters by ASCII code of the field name from small to large (dictionary order)
            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {

                @Override
                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });
            // Construct a URL key-value pair format
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds) {
                if (StringUtils.isNotBlank(item.getKey())) {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (urlEncode) {
                        val = URLEncoder.encode(val, "utf-8");
                    }
                    if (keyToLower) {
                        buf.append(key.toLowerCase() + "=" + val);
                    } else {
                        buf.append(key + "=" + val);
                    }
                    buf.append("&");
                }
            }
            buff = buf.toString();
            if (buff.isEmpty() == false) {
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return buff;
    }

    /**
     * camel to Underline
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        if (StringUtils.isEmpty(param)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int len = param.length();
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append('_');
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
