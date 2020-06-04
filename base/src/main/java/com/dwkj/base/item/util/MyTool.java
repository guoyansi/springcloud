package com.dwkj.base.item.util;


import com.sun.management.OperatingSystemMXBean;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyDescriptor;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public final class MyTool {
    public static boolean isNumber(String str) {
        return isInt(str) || isDouble(str);
    }

    public static boolean isDouble(String str) {
        boolean isDouble = Pattern.compile("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$").matcher(str).find();
        return isDouble;
    }

    public static boolean isInt(String str) {
        boolean isInt = Pattern.compile("^-?[1-9]\\d*$").matcher(str).find();
        return isInt;
    }

    public static boolean isLong(String str) {
        boolean isLong = Pattern.compile("^-?[1-9]\\d*$").matcher(str).find();
        return isLong;
    }

    public static <T> T swap(Object origin, T target) {
        Field[] tFs = target.getClass().getDeclaredFields();//获得属性
        Field[] oFs = origin.getClass().getDeclaredFields();//获得属性
        for (int i = 0; i < tFs.length; i++) {
            String tname = tFs[i].getName();
            for (int j = 0; j < oFs.length; j++) {
                if (oFs[j].getName().equals(tname)) {
                    Object val = getValue(origin, tname);
                    setValue(target, tname, val);
                }
            }
        }
        return target;
    }

    /**
     * @param beanObj 实体类
     */
    private static Object getValue(Object beanObj, String name) {
        try {
            Field[] fields = beanObj.getClass().getDeclaredFields();//获得属性
            Class clazz = beanObj.getClass();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String names = field.getName();
                if (!names.equals(name)) {
                    continue;
                }
                // 此处应该判断beanObj,property不为null
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();
                if (getMethod != null) {
                    //System.out.println(beanObj+"的字段是:"+field.getName()+"，类型是："+field.getType()+"，取到的值是： "+getMethod.invoke(beanObj));
                    return getMethod.invoke(beanObj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param beanObj 实体类
     * @param name    字段名
     * @param value   值
     */
    private static void setValue(Object beanObj, String name, Object value) {
        try {
            Field[] fields = beanObj.getClass().getDeclaredFields();//获得属性
            Class clazz = beanObj.getClass();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String names = field.getName();
                if (!names.equals(name)) {
                    continue;
                }

                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), beanObj.getClass());
                Method setMethod = pd.getWriteMethod();
                if (setMethod != null) {
                    setMethod.invoke(beanObj, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getMd5(String str) throws Exception {
        String result = "";
        //String str = "123456";
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update((str).getBytes("UTF-8"));
        byte b[] = md5.digest();

        int i;
        StringBuffer buf = new StringBuffer("");

        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0) {
                i += 256;
            }
            if (i < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }

        result = buf.toString();
        return result;
    }

    public static Double toFixed(int i, Double d) {
        if (d == null) {
            return null;
        }
        String fixed = "#.";
        for (int a = 0; a < i; a++) {
            fixed += "0";
        }
        DecimalFormat df = new DecimalFormat(fixed);
        String s = df.format(d);
        return Double.parseDouble(s);
    }

    /**
     * 分到元的转换
     *
     * @param fen
     * @return
     */
    public static Double fenToYuan(Integer fen) {
        if (fen == null) {
            return null;
        }
        String fixed = "#.";
        for (int a = 0; a < 2; a++) {
            fixed += "0";
        }
        Double d = Double.parseDouble(fen.toString()) / 100;
        DecimalFormat df = new DecimalFormat(fixed);
        String s = df.format(d);
        return Double.parseDouble(s);
    }

    /**
     * 分到元的转换
     *
     * @param fen
     * @return
     */
    public static Double fenToYuan(Long fen) {
        if (fen == null) {
            return null;
        }
        String fixed = "#.";
        for (int a = 0; a < 2; a++) {
            fixed += "0";
        }
        Double d = Double.parseDouble(fen.toString()) / 100;
        DecimalFormat df = new DecimalFormat(fixed);
        String s = df.format(d);
        return Double.parseDouble(s);
    }

    public static String getNow() {
        return getNow("yyyy-MM-dd HH:mm:ss");
    }

    public static String getNow(String format) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(calendar.getTime());
    }


    public static String join(List<String> list, String split) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        int i = 1;
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            if (i != size) {
                sb.append(split);
            }
            i++;
        }
        return sb.toString();
    }

    /**
     * 获取操作系统名称
     *
     * @return
     */
    public static String getOsName() {
        // 操作系统
        String osName = System.getProperty("os.name");
        return osName;
    }

    /**
     * 获取系统cpu负载
     *
     * @return
     */
    public static double getSystemCpuLoad() {
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
                .getOperatingSystemMXBean();
        double SystemCpuLoad = osmxb.getSystemCpuLoad();
        return SystemCpuLoad;
    }

    /**
     * 获取jvm线程负载
     *
     * @return
     */
    public static double getProcessCpuLoad() {
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
                .getOperatingSystemMXBean();
        double ProcessCpuLoad = osmxb.getProcessCpuLoad();
        return ProcessCpuLoad;
    }

    /**
     * 获取总的物理内存
     *
     * @return
     */
    public static long getTotalMemorySize() {
        int kb = 1024;
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
                .getOperatingSystemMXBean();
        // 总的物理内存
        long totalMemorySize = osmxb.getTotalPhysicalMemorySize() / kb;
        return totalMemorySize;
    }

    /**
     * 获取剩余的物理内存
     *
     * @return
     */
    public static long getFreePhysicalMemorySize() {
        int kb = 1024;
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
                .getOperatingSystemMXBean();
        // 剩余的物理内存
        long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize() / kb;
        return freePhysicalMemorySize;
    }

    /**
     * 获取已使用的物理内存
     *
     * @return
     */
    public static long getUsedMemory() {
        int kb = 1024;
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
                .getOperatingSystemMXBean();
        //已使用的物理内存
        long usedMemory = (osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize()) / kb;
        return usedMemory;
    }

    public static boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null &&
                "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }
}
