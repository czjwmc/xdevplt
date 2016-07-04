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
 * Currency Utility Class used to convert string to currency
 *
 */
public final class CurrencyUtil {

    /**
     * Checkstyle rule: utility classes should not have public constructor
     */
    private CurrencyUtil() {
    }

    /**
     * Return default currencyPattern
     *
     * @return a string representing the currency pattern on the UI
     */
    public static String getCurrencyPattern() {
        Locale locale = LocaleContextHolder.getLocale();
        String defaultCurrencyPattern;
        try {
            defaultCurrencyPattern = ResourceBundle.getBundle(Constants.BUNDLE_KEY, locale)
                    .getString("currency.format");
        } catch (MissingResourceException mse) {
            defaultCurrencyPattern = "###,##0.00";
        }

        return defaultCurrencyPattern;
    }

    /**
     * This method generates a string representation of a currency based
     * on the System Property 'currency.Format'
     * in the format you specify on input
     *
     * @param dDouble A double to convert
     * @return a string representation of the currency
     */
    public static String convertDoubleToCurrency(Double dDouble) {
    	if (dDouble == null) {
            return "";
        }
    	DecimalFormat formatter = new DecimalFormat(getCurrencyPattern());
    	return formatter.format(dDouble);
    }

    /**
     * This method converts a currency to a double using the currencyPattern
     *
     * @param currency the currency to convert
     * @return a Double object
     */
    public static Double convertCurrencyToDouble(String currency) {
    	if (currency == null) {
            return new Double(0);
        }
    	DecimalFormat formatter = new DecimalFormat(getCurrencyPattern());
    	try {
    	    Number num = formatter.parse(currency);
    	    return num.doubleValue();
    	} catch (ParseException pe) {
            return new Double(0);
        }
    }
}
