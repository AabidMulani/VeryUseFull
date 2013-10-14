package in.abmulani.xmlbackup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import android.content.Context;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

public class ReadBackUpFile {
Context contxt;
	public ReadBackUpFile(Context contxt) {
		this.contxt=contxt;
		try {
			File dir = Environment.getExternalStorageDirectory();
			File myFile = new File(dir, "GPS_APP/BackUp.xsm");
			String EncryptedStr = getEncryptedString(myFile);
			String PrevMD5Code = EncryptedStr.substring(0, 29);
			System.out.println("PrevMD5Code : "+PrevMD5Code);
			String NewMD5Code = makeMD5(EncryptedStr.substring(30));
			System.out.println("NewMD5Code : "+NewMD5Code);
			if(NewMD5Code.equals(PrevMD5Code)){
				Toast.makeText(contxt, "SAME", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(contxt, "NOT SAME", Toast.LENGTH_SHORT).show();
			}
		} catch (Exception ex) {
			Toast.makeText(contxt, "UN-FOUND22", Toast.LENGTH_SHORT).show();
			Log.e("No File:	", "No File Found");
			Log.e("Exception:", ex.toString());
		}
	}

	private String makeMD5(String encryptedStr) {
		try {
			String key = "d6fc4a3a06ed66d35fecde299aaa0272";
			Mac mac = Mac.getInstance("HmacSHA1");
			SecretKeySpec sk = new SecretKeySpec(key.getBytes(),
					mac.getAlgorithm());
			mac.init(sk);
			byte[] result = mac.doFinal(encryptedStr.getBytes());
			return Base64.encodeToString(result, Base64.URL_SAFE);
		} catch (Exception ex) {
			Toast.makeText(contxt, "UN-FOUND", Toast.LENGTH_SHORT).show();
			return null;
		}
	}

	private String getEncryptedString(File file) {
		StringBuilder data = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				data.append(line);
//				data.append('\n');
			}
		} catch (Exception e) {
			Log.e("Exception:", e.toString());
		}
		return data.toString();
	}

}
