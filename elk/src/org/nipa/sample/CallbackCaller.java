package org.nipa.sample;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CallbackCaller extends Thread {

	public static void main(String args[]) {
		
		String result = null;
		// 충전소 정보
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		
		try {
			
			HttpUrl.Builder urlBuilder = HttpUrl.parse("http://apis.data.go.kr/B552584/EvCharger/getChargerStatus").newBuilder();
			
			// decoding...
			urlBuilder.addQueryParameter("serviceKey","YWRncUmVrCMs8Nm7xSfh8Q2j0ao587l3WP2M/l4uSYyNrN16+Lo65V66u+IP1QUCWNki6e+6ejjwEaUJ+Iyaew==");
			
			
			urlBuilder.addQueryParameter("pageNo", "1");
			urlBuilder.addQueryParameter("numOfRows", "10");
			urlBuilder.addQueryParameter("period", "5");
			urlBuilder.addQueryParameter("zcode", "11");
			String url = urlBuilder.build().toString();
			System.out.println(url);
			//*/
			
			Request request = new Request.Builder()
					.url(url)
					.method("GET", null)
					.build();
			
			Response response = client.newCall(request).execute();
		
			
			result = response.body().string();
			System.out.println(result);
			Document doc = convertStringToXMLDocument(result);
			
			NodeList items = doc.getElementsByTagName("items");
			Node node = items.item(0);
			items = node.getChildNodes();
			NodeList itemList = null;
			node = null;
			for(int i = 0 ; i < items.getLength() ; i++) {
				node = items.item(i);
				itemList = node.getChildNodes();
				for(int j = 0 ; j < itemList.getLength() ; j++) {
					node = itemList.item(j);
					System.out.print(node.getNodeName() + " : "+ node.getTextContent() + "\t");
				}
				System.out.println();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static Document convertStringToXMLDocument(String xmlString) {
		// Parser that produces DOM object trees from XML content
		DocumentBuilder db = null;
		Document doc = null;
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlString));
			doc = (Document) db.parse(is);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
	
	public int executeUpdate(String busiIds,String statId,String chgerId,String stat,String statUpdDt,String lastTsdt,String lastTedt,String nowTsdt) {
		int updateCnt = 0;

		
		Connection con = null;
		PreparedStatement pStmt =null;
		ResultSet rset = null;
		
		try {
			con = DriverManager.getConnection("","","");
			String query = "insert into ???";
			pStmt = con.prepareStatement(query);
			//pStmt.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return updateCnt;
	}

}
