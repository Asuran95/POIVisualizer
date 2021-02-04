package piper.poivisualizer.application;

import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;


public class TestMain {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException, CsvException, ParseException {

		String dataGmt = "Wed Dec 12 2018 04:04:29 GMT-0200 (Hora oficial do Brasil)";
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss", Locale.ENGLISH);
		
		Date dataParsed = sdf.parse(dataGmt);
        
        
        
        System.out.println(TimeZone.getDefault().getID());
		
        System.out.println(dataParsed.toString());

	}

}
