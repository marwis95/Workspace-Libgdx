import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class Main {


	public static void main(String[] args) throws IOException {
		Response res  = Jsoup.connect("https://bezpiecznyautobus.gov.pl/strona-glowna").method(Method.GET).execute();
		Map<String, String> cookies = res.cookies();
		 
		Document doc = res.parse();
		
		Element code = doc.getElementById("javax.faces.ViewState");
		String codeString = code.attr("value");
		
		Element pauth = doc.getElementById("_bezpiecznyautobusportlet_WAR_bezpiecznyautobusportlet_:formularzWyszukiwania");
		String pauthStr = pauth.attr("action");
		
		System.out.println(pauthStr);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("javax.faces.ViewState", codeString);
		params.put("_bezpiecznyautobusportlet_WAR_bezpiecznyautobusportlet_:formularzWyszukiwania", "_bezpiecznyautobusportlet_WAR_bezpiecznyautobusportlet_:formularzWyszukiwania");
		params.put("javax.faces.encodedURL", "https://bezpiecznyautobus.gov.pl/strona-glowna?p_p_id=bezpiecznyautobusportlet_WAR_bezpiecznyautobusportlet&p_p_lifecycle=2&p_p_state=normal&p_p_mode=view&p_p_cacheability=cacheLevelPage&p_p_col_id=column-1&p_p_col_count=1&_bezpiecznyautobusportlet_WAR_bezpiecznyautobusportlet__jsfBridgeAjax=true&_bezpiecznyautobusportlet_WAR_bezpiecznyautobusportlet__facesViewIdResource=%2Fviews%2Findex.xhtml");
		params.put("_bezpiecznyautobusportlet_WAR_bezpiecznyautobusportlet_:nrRejestracyjny", "WR1407F");
		params.put("_bezpiecznyautobusportlet_WAR_bezpiecznyautobusportlet_:wyszukajPojazd", "Sprawdü pojazd ª");
		Response resResult = Jsoup.connect(pauthStr).method(Method.POST).cookies(cookies).data(params).execute();
		
		Document docResult = resResult.parse();
		
		System.out.println(docResult.getElementsByClass("VIN").toString());
		System.out.println(docResult.getElementsByClass("Stan licznika").toString());

	}
	
}
