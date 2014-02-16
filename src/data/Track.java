package data;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Track implements Iterable<TrackPoint>{ 

	private List<TrackPoint> points;
	
	public Track() {
		points = new ArrayList<TrackPoint>();
	}
	
	public void add(TrackPoint point) {
		points.add(point);
	}
	
	public void filter(Date fromDate) {
		Iterator<TrackPoint> it = points.iterator();
		while (it.hasNext()) {
			TrackPoint point = it.next();
			if (point.getDate().before(fromDate)) {
				it.remove();
			}
		}		
	}
	
	public void filterByValidity(String valid) {
		Iterator<TrackPoint> it = points.iterator();
		while (it.hasNext()) {
			TrackPoint point = it.next();
			if (point.getGpsSignal().equals(valid)) {
				it.remove();
			}
		}				
	}
	
	public String toXml() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("  <trk>\n");
		sb.append("    <trkseg>\n");
		for (TrackPoint point:this) {
			sb.append(point.toXml());
		}
		sb.append("    </trkseg>\n");
		sb.append("  </trk>\n");
		return sb.toString();
	}
	
	public int size() {
		return points.size();
	}

	@Override
	public Iterator<TrackPoint> iterator() {
		return points.iterator();
	}
}
