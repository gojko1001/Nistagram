package com.xws.nistagrammonolith.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

public class Base64Utility {
	 public static String encode(byte[] data){
		 BASE64Encoder encoder = new BASE64Encoder();
		 return encoder.encode(data);
	 }
	 
	 public static byte[] decode(String base64Data) throws IOException{
		 BASE64Decoder decoder = new BASE64Decoder();
		 return decoder.decodeBuffer(base64Data);
	 }
}
