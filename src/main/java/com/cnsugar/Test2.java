package com.cnsugar;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.cnsugar.ai.face.FaceHelper;
import com.seetaface2.model.SeetaRect;

public class Test2 {

	
	

	/**
	 * 人脸比较
	 * 
	 * @throws Exception
	 */
	@org.junit.Test
	public void testCompare() throws Exception {
		String img1 = "D:\\abc\\a\\1.jpg";
		String img2 = "D:\\abc\\a\\5.jpg";
		System.out.println("result:" + FaceHelper.compare(new File(img1), new File(img2)));
	}
	

}
