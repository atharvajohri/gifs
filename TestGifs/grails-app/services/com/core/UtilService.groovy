package com.core

import org.apache.commons.codec.binary.Base64
import org.apache.commons.codec.binary.Base64OutputStream
import sun.misc.BASE64Decoder
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.awt.Image
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

class UtilService {

    def extractUrlImageData(url) {
		log.info "calculating size for $url"
		url = new URL(url);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream is = url.openStream();
		byte[] b = new byte[2^16];
		int read = is.read(b);
		while (read>-1) {
			log.info "read ${baos.toByteArray().length} bytes of data"
			baos.write(b,0,read);
			read = is.read(b);
		}
		log.info "finished reading image... now beginning to encode"
		int countInBytes = baos.toByteArray().length;
		
		baos.flush();
		def imageInByte = baos.toByteArray();
		baos.close();
		
		InputStream inputStream = new ByteArrayInputStream(imageInByte);
		BufferedImage img = ImageIO.read(inputStream);
		
		BufferedImage newImg;
		String imgstr = encodeImage(img, "gif");
		log.info "\n encoded string: $imgstr"
		log.info "\n encoded image string size: ${imgstr.size()}"
		
		try{
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] imgBytes = decoder.decodeBuffer(imgstr);
			BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(imgBytes));
			File imgOutFile = new File("C:/newLabel.gif");
			ImageIO.write(bufImg, "png", imgOutFile);
		}catch(err){
			log.info "there be an error"
			log.info err.printStackTrace()
		}
		log.info "\n decoded is: "+ decodeImage(imgstr)
		def returnData = [:]
		returnData.imageSize = countInBytes
		returnData.encodedImage = imgstr
		
		return returnData
    }
	
	
	def decodeImage(imageString){
		BufferedImage image = null;
		byte[] imageByte;
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			imageByte = decoder.decodeBuffer(imageString);
			ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
			image = ImageIO.read(bis);
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
	
	def encodeImage(BufferedImage image, String type) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		def result = null
		try {
			OutputStream b64 = new Base64OutputStream(bos);
			ImageIO.write(image, "png", b64);
			result = bos.toString("UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
