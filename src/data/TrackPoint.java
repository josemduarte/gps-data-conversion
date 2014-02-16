package data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TrackPoint {
	//18628,T,2012/12/02,22:40:06,SPS,25.668867,N,100.318409,W,524.808 m,16.754 km/h,
	
	private int id;
	
	private Date date;
	
	private String gpsSignal;
	
	private double latitude;
	private double longitude;
	private double altitude;
	
	private double speed;

	public TrackPoint(int id, Date date, String gpsSignal, double latitude, double longitude, double altitude, double speed) {
		this.id = id;
		this.date = date;
		this.gpsSignal = gpsSignal;
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.speed = speed;
	}
	
	public String toXml() {
		SimpleDateFormat dayformatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm:ss");
		StringBuffer sb = new StringBuffer();
		sb.append("      <trkpt lat=\""+latitude+"\" lon=\""+longitude+"\">\n");
		sb.append("        <ele>"+altitude+"</ele>\n");
		sb.append("        <time>"+dayformatter.format(date)+"T"+timeformatter.format(date)+"Z"+"</time>\n");
		sb.append("      </trkpt>\n");
		return sb.toString();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getGpsSignal() {
		return gpsSignal;
	}

	public void setGpsSignal(String gpsSignal) {
		this.gpsSignal = gpsSignal;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
}
