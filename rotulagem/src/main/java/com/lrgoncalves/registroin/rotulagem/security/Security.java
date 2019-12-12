/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author 928069
 *
 */
public class Security {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String token = null;
		try {

			//The JWT signature algorithm we will be using to sign the token
		    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
			
			long nowMillis = System.currentTimeMillis();
			Date now = new Date(nowMillis);

			long expMillis = nowMillis + 15000;
			Date exp = new Date(expMillis);

			final String SECRET_KEY = "jsdfhaldjfhalsdkjfhowerutywoeirutywoeriuty";
			
			// We will sign our JWT with our ApiKey secret
			byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
			Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
			
			token = Jwts.builder()
					.setSubject("1234567890")
					.setId("c75b9511-fa12-4e7d-878f-2f37148bf907")
					.setIssuedAt(now)// Date.from(Instant.ofEpochSecond(1576155825)))
					.setExpiration(exp)// Date.from(Instant.ofEpochSecond(1576159425)))
					.claim("name", "John Doe")
					.claim("admin", true)
					//HEADER
					.signWith(signatureAlgorithm,SECRET_KEY.getBytes("UTF-8"))//signingKey)
					.setHeaderParam("typ", "JWT")
					.compact();//SignatureAlgorithm.HS256, "secret".getBytes("UTF-8")).compact();

			System.out.println(token);

			System.out
					.println(Jwts.parser()
								.setSigningKey(SECRET_KEY.getBytes("UTF-8"))//signingKey)
								.parseClaimsJws(token)
								.toString());
							//"secret".getBytes("UTF-8")).parseClaimsJws(token).toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
