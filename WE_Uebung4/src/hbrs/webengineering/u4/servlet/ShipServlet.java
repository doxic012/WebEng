package hbrs.webengineering.u4.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.pbrsystems.ais.Ship;
import de.pbrsystems.ais.ShipReceiver;

@WebServlet(urlPatterns = {"/ships"})
public class ShipServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// Aktuelle Ship-List
		List<Ship> results = ShipReceiver.loadShipsFromServer();
		StringBuffer sb = new StringBuffer();
		int i = 0;
		
		sb.append("[");
		for(Ship ship : results) {
			
			sb.append(i++ > 0 ? "," : ""); // Komma
			sb.append("{\"mmsi\": "+ship.getMmsi());
			sb.append(", \"latitude\": "+ship.getLatitude());
			sb.append(", \"longitude\": "+ship.getLongitude());
			sb.append(", \"courseOverGround\": "+ship.getCourseOverGround()+"}");
		}
		sb.append("]");
		
		resp.setContentType("application/json");
		PrintWriter write = resp.getWriter();
		write.print(sb.toString()); //print json	
	}
}
