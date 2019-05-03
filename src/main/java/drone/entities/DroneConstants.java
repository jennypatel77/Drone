package drone.entities
;

import java.text.SimpleDateFormat;

public class DroneConstants {
	
	public static final long PROMOTER_THRESHOLD_IN_SECS = 7199; // 1hr 59 mins
	public static final long NEUTRAL_THRESHOLD_IN_SECS = 14339; // 3hr 59 mins
	public static final int ONE_WAY = 1;
	public static final int ROUND_TRIP = 2;
	public static final SimpleDateFormat SDF = new SimpleDateFormat("HH:mm:ss");

}
