package piper.poivisualizer.utils;

public class CoordinatesUtil {
	
	public static double getDistanceBetweenTwoPointsInMeters(double lat1, double lon1, double lat2, double lon2) {
		
		double r = 6371d;
		
		double dLat = degToRad(lat2-lat1);
		double dLon = degToRad(lon2-lon1);
		
		double a = 
				Math.sin(dLat/2) * Math.sin(dLat/2) +
	    		Math.cos(degToRad(lat1)) * Math.cos(degToRad(lat2)) * 
	    		Math.sin(dLon/2) * Math.sin(dLon/2);
		
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		double d = r * c;
		
		return d*1000d;
		
	}
	
	public static double degToRad(double deg) {	
		return deg * (Math.PI/180);
	}

}
