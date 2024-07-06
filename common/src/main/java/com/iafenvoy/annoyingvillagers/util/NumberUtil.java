package com.iafenvoy.annoyingvillagers.util;

import java.text.DecimalFormat;

public class NumberUtil {
    public static String formatNumber(long origin) {
        String fileSizeString = "";
        if (origin < 0) return fileSizeString;
        DecimalFormat df = new DecimalFormat("#.0");
        if (origin < 1000)
            fileSizeString = df.format((double) origin);
        else if (origin < 1000000)
            fileSizeString = df.format((double) origin / 1000) + "K";
        else if (origin < 1000000000)
            fileSizeString = df.format((double) origin / 1000000) + "M";
        else
            fileSizeString = df.format((double) origin / 1000000000) + "G";
        return fileSizeString;
    }
}
