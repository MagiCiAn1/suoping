package RSA;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.mysql.jdbc.util.Base64Decoder;

public class RsaDecrypt {
	private String modulus="93510190618404480112456739142683947685791350996039849678517366015080163828283405155424588176883145348843573201615106423089973665581659426816833547797523442618687757080539327278020807455600664612345042310302559375391808991419933663227866659016228806380539601877601845271672806579915036360111363980696331519027";
	private String privateE="78726799785786435062406168102548628980747113577940625079907139283886401870536722200523476467456618822463539603160277583661326990434924402002618487774480864286941321871077692093923843736023462627083338585997220389518684802432425621879884480128093089109426904636074753770945587226261317480197763323878316348353";
	public String decrypt(String enc){
		try {
			String encoding=enc.replace(" ", "+");
			byte[] temp=Base64.getDecoder().decode(encoding);
			BigInteger m=new BigInteger(modulus);
			BigInteger e=new BigInteger(privateE);
			RSAPrivateKeySpec keyspec=new RSAPrivateKeySpec(m, e);
			KeyFactory keyFactory=KeyFactory.getInstance("RSA");
			PrivateKey privateKey=keyFactory.generatePrivate(keyspec);
			Cipher cipher=Cipher.getInstance("RSA/ECB/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] pass=cipher.doFinal(temp);
			return new String(pass).trim();
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidKeySpecException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalBlockSizeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (BadPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
}
