package com.xj.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import com.xj.Constants;

/**
 * Currency Utility Class used to convert string to number
 *
 */
public final class NumberUtil {

    /**
     * Checkstyle rule: utility classes should not have public constructor
     */
    private NumberUtil() {
    }

    /**
     * Return default numberPattern
     *
     * @return a string representing the number pattern on the UI
     */
    public static String getPattern() {
        Locale locale = LocaleContextHolder.getLocale();
        String defaultPattern;
        try {
            defaultPattern = ResourceBundle.getBundle(Constants.BUNDLE_KEY, locale)
                    .getString("number.format");
        } catch (MissingResourceException mse) {
            defaultPattern = "###,##0";
        }

        return defaultPattern;
    }

    /**
     * This method generates a string representation of a number based
     * on the System Property 'number.Format'
     * in the format you specify on input
     *
     * @param lLong A long to convert
     * @return a string representation of the number
     */
    public static String convertLongToNumber(Long lLong) {
    	if (lLong == null) {
            return "";
        }
    	DecimalFormat formatter = new DecimalFormat(getPattern());
    	return formatter.format(lLong);
    }

    /**
     * This method converts a number to a long using the numberPattern
     *
     * @param number the number to convert
     * @return a long object
     */
    public static Long convertNumberToLong(String number) {
    	if (number == null) {
            return new Long(0);
        }
    	DecimalFormat formatter = new DecimalFormat(getPattern());
    	try {
    	    Number num = formatter.parse(number);
    	    return num.longValue();
    	} catch (ParseException pe) {
            return new Long(0);
        }
    }

    /**
     * This method generates a string representation of a number based
     * on the System Property 'number.Format'
     * in the format you specify on input
     *
     * @param iInteger A integer to convert
     * @return a string representation of the number
     */
    public static String convertIntegerToNumber(Integer iInteger) {
    	if (iInteger == null) {
            return "";
        }
    	DecimalFormat formatter = new DecimalFormat(getPattern());
    	return formatter.format(iInteger);
    }

    /**
     * This method converts a number to a integer using the numberPattern
     *
     * @param number the number to convert
     * @return a integer object
     */
    public static Integer convertNumberToInteger(String number) {
    	if (number == null) {
            return new Integer(0);
        }
    	DecimalFormat formatter = new DecimalFormat(getPattern());
    	try {
    	    Number num = formatter.parse(number);
    	    return num.intValue();
    	} catch (ParseException pe) {
            return new Integer(0);
        }
    }
}
