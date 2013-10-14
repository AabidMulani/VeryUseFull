package in.abmulani.xmlbackup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.xmlpull.v1.XmlSerializer;

import android.util.Base64;
import android.util.Log;
import android.util.Xml;

public class CreatBackUpFile {
	private String path,fileName;

	public CreatBackUpFile(String path,String fileName) {
		this.path = path;
		this.fileName = fileName;
		String xmlDataStr=GetXMLText();
		String EncryptxmlStr=new SimpleEncryption().encryptString(xmlDataStr);
		String md5str=makeMD5(EncryptxmlStr);
		if(writeTofile(md5str+EncryptxmlStr)){
			Log.d("File Write: ", "Done..!");
		}else{
			Log.d("File Write: ", "Failed..!");
		}
	}
	
	private boolean writeTofile(String data){
		 try
		    {
		        File root = new File(path);
		            root.mkdirs();
		        File gpxfile = new File(root, fileName);
		        FileWriter writer = new FileWriter(gpxfile);
		        writer.append(data);
		        writer.flush();
		        writer.close();
		        Log.d("File Write: ", "Done..!");
		        return true;
		    }
		    catch(IOException e)
		    {
		         e.printStackTrace();
		    }
		 Log.d("File Write: ", "Failed..!");
		return false;
	}
	
	private String makeMD5(String encryptedStr){
		try{
		  String key = "d6fc4a3a06ed66d35fecde299aaa0272";
		    Mac mac = Mac.getInstance("HmacSHA1");
		    SecretKeySpec sk = new SecretKeySpec(key.getBytes(),mac.getAlgorithm());  
		    mac.init(sk);
		    byte[] result = mac.doFinal(encryptedStr.getBytes());
		    return Base64.encodeToString(result ,Base64.URL_SAFE);
		}catch(Exception ex){
			return null;
		}
	}
	
	private String GetXMLText() {
		try {
			XmlSerializer xmlSerializer = Xml.newSerializer();
			StringWriter writer = new StringWriter();

			xmlSerializer.setOutput(writer);
			xmlSerializer.startDocument("UTF-8", true);
			xmlSerializer.startTag("", "MyList");
			for (int i = 0; i < MainActivity.idList.size(); i++) {
				xmlSerializer.startTag("", "values");

				xmlSerializer.startTag("", "id");
				xmlSerializer.text(MainActivity.idList.get(i));
				xmlSerializer.endTag("", "id");

				xmlSerializer.startTag("", "name");
				xmlSerializer.text(MainActivity.nameList.get(i));
				xmlSerializer.endTag("", "name");
				
				xmlSerializer.startTag("", "nearby");
				xmlSerializer.text(MainActivity.nearbyList.get(i));
				xmlSerializer.endTag("", "nearby");
				
				xmlSerializer.startTag("", "time");
				xmlSerializer.text(MainActivity.timeList.get(i));
				xmlSerializer.endTag("", "time");
				
				xmlSerializer.startTag("", "latitude");
				xmlSerializer.text(MainActivity.latitudeList.get(i));
				xmlSerializer.endTag("", "latitude");
				
				xmlSerializer.startTag("", "longitude");
				xmlSerializer.text(MainActivity.longitudeList.get(i));
				xmlSerializer.endTag("", "longitude");

				xmlSerializer.endTag("", "values");
			}
			xmlSerializer.endTag("", "MyList");
			xmlSerializer.endDocument();
			return writer.toString();
		} catch (Exception pce) {
			pce.printStackTrace();
			return null;
		}
	}
}