package io;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import data.Track;

public class CsvToGpx {

	private static final String GPXHEADER = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
			"<gpx xmlns=\"http://www.topografix.com/GPX/1/1\" " +
			"creator=\"Daniel Schlosser\" " +
			"version=\"v1.1.5\" " +
			"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
			"xsi:schemaLocation=\"http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd\">";
	
	public static void main(String[] args) throws Exception {
		
		if (args.length<2) {
			System.err.println("Usage: CsvToGpx <in csv file> <out gpx file> [filter from date yyyy-mm-dd]");
			System.exit(1);
		}
		
		File file = new File(args[0]);
		File gpxOutFile = new File(args[1]);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Date fromDate = null;
		
		if (args.length>2) {
			fromDate = formatter.parse(args[2]);
		}
		
		CsvReader reader = new CsvReader();
		
		reader.read(file);
		
		Track track = reader.getTrack();
		if (fromDate!=null) {
			int sizeBefore = track.size();
			track.filter(fromDate);
			int sizeAfter = track.size();
			System.out.println("Filtered out "+(sizeBefore-sizeAfter)+" track points before "+fromDate);
		}

		int sizeBefore = track.size();
		track.filterByValidity("No fix");
		int sizeAfter = track.size();
		System.out.println("Filtered out "+(sizeBefore-sizeAfter)+" track points with 'No fix'");
		
		System.out.println("Writing out "+gpxOutFile+" with "+sizeAfter+" track points");
		PrintWriter pw = new PrintWriter(gpxOutFile);
		pw.println(GPXHEADER);
		pw.print(track.toXml());
		pw.println("</gpx>");
		pw.close();

	}

}
