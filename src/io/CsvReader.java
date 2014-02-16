package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import data.Track;
import data.TrackPoint;

public class CsvReader {

	private Track track;
	
	public CsvReader() {
		track = new Track();
	}
	
	public void read(File file) throws IOException, ParseException {
		// 1,T,2012/06/08,12:19:28,No fix,47.480505,N,8.207974,E,410.279 m,0.000 km/h,		
		
		BufferedReader br = new BufferedReader(new FileReader(file));		
		String line;
		while ((line=br.readLine())!=null) {
			if (line.isEmpty()) continue;
			if (!Character.isDigit(line.charAt(0))) continue;
			
			String[] tokens = line.split(",");
			int id = Integer.parseInt(tokens[0]);
			
			String day = tokens[2];
			String time = tokens[3];
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/ddHH:mm:ss");
	        Date date = formatter.parse(day+time);
			
			String valid = tokens[4];
			String ns = tokens[6];
			String ew = tokens[8];
			
			double latitude = Double.parseDouble(tokens[5]);
			if (ns.equals("S")) latitude = -latitude;
			double longitude = Double.parseDouble(tokens[7]);
			if (ew.equals("W")) longitude = -longitude;
			
			double altitude = Double.parseDouble(tokens[9].replace(" m", "")); 
			
			double speed = Double.parseDouble(tokens[10].replace(" km/h", ""));
			
			TrackPoint point = new TrackPoint(id, date, valid, latitude, longitude, altitude, speed);
			track.add(point);
		}
		
		br.close();
	}
	
	public Track getTrack() {
		return track;
	}
}
