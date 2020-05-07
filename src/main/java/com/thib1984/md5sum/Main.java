package com.thib1984.md5sum;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.RandomStringUtils;

public class Main {
	static int record = 0;
	final static char[] caracteres = {'a', 'b', 'c', 'd', 'e', 'f', '0', '1', '2', '3', '4',
			'5', '6', '7', '8', '9'}; 
	final static int longueur = 32;
	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		while (true) {
			// generation chaine
			String identique = "********************************";
			String generatedString = RandomStringUtils.random(longueur, caracteres);
			// calcul md5
			String hashtext = new BigInteger(1,
					MessageDigest.getInstance("MD5").digest(generatedString.getBytes("UTF-8"))).toString(16);
			while (hashtext.length() < longueur) {
				hashtext = "0" + hashtext;
			}
			// comparaison
			int compteur = 0;
			for (int i = 0; i < longueur; i++) {
				if (generatedString.charAt(i) == hashtext.charAt(i)) {
					compteur++;
					StringBuilder builder = new StringBuilder(identique);
					builder.setCharAt(i, generatedString.charAt(i));
					identique = builder.toString();
				}

			}
			// log
			if (compteur > record) {
				record = compteur;
				System.out.println("Nouveau record! Nombre de caracteres : " + compteur);
				System.out.println("Chaine : " + generatedString);
				System.out.println("MD5    : " + hashtext);
				System.out.println("Commun : " + identique);
			}
		}
	}
}
