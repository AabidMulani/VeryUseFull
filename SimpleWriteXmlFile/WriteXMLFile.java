package in.abmulani.xmlbackup;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteXMLFile {
	String path;

	public WriteXMLFile(String path) {
		this.path = path;
	}

	public void WriteFile() {
		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentFactory
					.newDocumentBuilder();
			Document document = documentBuilder.newDocument();

			// root element
			Element root = document.createElement("values");
			document.appendChild(root);

			for (int i = 0; i < MainActivity.idList.size(); i++) {
				Element employee = document.createElement("entry");
				root.appendChild(employee);

				Attr attr = document.createAttribute("id");
				attr.setValue(MainActivity.idList.get(i));
				employee.setAttributeNode(attr);

				Element name = document.createElement("name");
				name.appendChild(document.createTextNode(MainActivity.nameList.get(i)));
				employee.appendChild(name);

				Element nearby = document.createElement("nearby");
				nearby.appendChild(document.createTextNode(MainActivity.nearbyList.get(i)));
				employee.appendChild(nearby);

				Element time = document.createElement("time");
				time.appendChild(document.createTextNode(MainActivity.timeList.get(i)));
				employee.appendChild(time);

				Element latitude = document.createElement("latitude");
				latitude.appendChild(document.createTextNode(MainActivity.latitudeList.get(i)));
				employee.appendChild(latitude);

				Element longitude = document.createElement("longitude");
				longitude.appendChild(document
						.createTextNode(MainActivity.longitudeList.get(i)));
				employee.appendChild(longitude);
			}

			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(path));
			transformer.transform(domSource, streamResult);
			System.out.println("Done creating XML File");
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
}