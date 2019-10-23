package com.cnsugar;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import com.cnsugar.ai.face.FaceHelper;
import com.cnsugar.ai.face.SeetafaceBuilder;
import com.cnsugar.ai.face.bean.Result;

public class Test1 {

	@org.junit.Test
	public void testtcsb() throws IOException {
		String img1 = "D:\\abc\\a\\111.jpg";
		File img12 = new File(img1);
		BufferedImage image1 = ImageIO.read(img12);
		System.out.println("result111:" + FaceHelper.detectLandmark(image1));
	}

	/**
	 * 人脸比较
	 * 
	 * @throws Exception
	 */
	@org.junit.Test
	public void testCompare() throws Exception {
		String img1 = "D:\\abc\\1.jpg";
		String img2 = "D:\\abc\\5.jpg";
		System.out.println("result:" + FaceHelper.compare(new File(img1), new File(img2)));
	}

	/**
	 * 指定目录注册人脸
	 * 
	 * @throws IOException
	 */
	@org.junit.Test
	public void testRegister() throws IOException {
		// 将F:\ai\star目录下的jpg、png图片都注册到人脸库中，以文件名为key
		Collection<File> files = FileUtils.listFiles(new File("D:\\abc\\a"), new String[] { "jpg", "png" }, false);
		for (File file : files) {
			String key = file.getName();
			try {
				FaceHelper.register(key, FileUtils.readFileToByteArray(file));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 人脸搜索
	 * 
	 * @throws IOException
	 */
	@org.junit.Test
	public void testSearch() throws IOException {
		SeetafaceBuilder.build();// 系统启动时先调用初始化方法

		// 等待初始化完成
		while (SeetafaceBuilder.getFaceDbStatus() == SeetafaceBuilder.FacedbStatus.LOADING
				|| SeetafaceBuilder.getFaceDbStatus() == SeetafaceBuilder.FacedbStatus.READY) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		long l = System.currentTimeMillis();
		Result result = FaceHelper.search(FileUtils.readFileToByteArray(new File("D:\\abc\\a\\11.jpg")));
		System.out.println("搜索结果：" + result + "， 耗时：" + (System.currentTimeMillis() - l));
	}

}
