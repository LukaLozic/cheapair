package com.llit.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Helper {

	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DATE_FORMAT_CRO = "dd.MM.yyyy.";
    public static String formatCurrencyValueToCroatianStandard(String number) throws Exception{

        StringBuilder sb = new StringBuilder();
        String numberFormated = number;

        String prefix = null;
        
        if (number.startsWith("-")) {
        	
            prefix = number.substring(0, 1);
            numberFormated = number.substring(1, number.length());
        }

        if (numberFormated.contains(".")) {
        	
            numberFormated = numberFormated.replaceAll("\\.", ",");
        }

        String numberDecimalPart;

        if (numberFormated.length() < 4 && !numberFormated.contains(".")) {
        	
            numberFormated = numberFormated.concat(".00");
            numberDecimalPart = numberFormated.substring(numberFormated.length() - 3, numberFormated.length());
        } else {
        	
            numberDecimalPart = numberFormated.substring(numberFormated.length() - 3, numberFormated.length());

            if (!numberDecimalPart.startsWith(",")) {
            	
                numberFormated = numberFormated.concat(",00");
                numberDecimalPart = numberFormated.substring(numberFormated.length() - 3, numberFormated.length());
            }
        }

        String numberWholeNumbersPart = numberFormated.substring(0, numberFormated.indexOf(numberDecimalPart));

        numberWholeNumbersPart = numberWholeNumbersPart.replaceAll(",", "");

        int size = numberWholeNumbersPart.length();

        if (size > 3) {

            List<String> listThousands = new ArrayList<>();
            List<String> listWholeNumbersReverted = new ArrayList<>();

            List<String> listNumberWhole = stringToStringList(numberWholeNumbersPart);
            listStringRevert(listNumberWhole);

            for (String str : listNumberWhole) {

                listThousands.add(str);

                if (listThousands.size() == 3) {
                	
                    for (String strThousand : listThousands) {
                    	
                        listWholeNumbersReverted.add(strThousand);
                    }
                    
                    listWholeNumbersReverted.add(".");
                    listThousands.clear();
                }

            }

            if (!listThousands.isEmpty()) {
            	
                for (String strThousand : listThousands) {
                	
                    listWholeNumbersReverted.add(strThousand);
                }
            }

            listStringRevert(listWholeNumbersReverted);

            for (String numberr : listWholeNumbersReverted) {
            	
                sb.append(numberr);
            }
            sb.append(numberDecimalPart);

            String numberFormatedFinal = sb.toString();
            
            if (numberFormatedFinal.startsWith(".")) {
            	
                numberFormatedFinal = numberFormatedFinal.substring(1);
            }
            if (prefix != null && !prefix.isEmpty()) {
            	
                numberFormatedFinal = prefix.concat(numberFormatedFinal);
            }
            
            return numberFormatedFinal;
            
        	} 
        	else {

	        if (prefix != null && !prefix.isEmpty()) {
	        	
	                numberFormated = prefix.concat(numberFormated);
	        }
	        
            return numberFormated;
        }

    }

    public static void listStringRevert(List<String> list) {

        for (int k = 0, j = list.size() - 1; k < j; k++) {
            list.add(k, list.remove(j));
        }
    }
    
    public static List<String> stringToStringList(String str) {
        
    	String[] strSplit = str.split("");
    	
        ArrayList<String> strList = new ArrayList<String>(
                Arrays.asList(strSplit));

        return strList;
    }
    
    public static String convertDateToString(Date date, String format) {
    	
    	DateFormat dateFormat = new SimpleDateFormat(format);  
    	String strDate = dateFormat.format(date);  
    	
    	return strDate;	
    }

	
}
