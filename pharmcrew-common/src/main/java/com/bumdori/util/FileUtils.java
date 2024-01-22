package com.bumdori.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	
	private static String tmpdir = System.getProperty("java.io.tmpdir");

	public static File save(MultipartFile multipartFile, boolean rename) throws IllegalStateException, IOException {
		File file = checkDuplicate(multipartFile.getOriginalFilename(), rename);
		multipartFile.transferTo(file);
		return file;
	}
	
	public static File checkDuplicate(String orgFilename, boolean rename) {
		String name = FileUtils.getName(orgFilename);
		String extention = FileUtils.getExtension(orgFilename);
		
		File file;
		int idx = 0;
		String filename = rename ? FileUtils.makeFilename() : name;
		while ((file = new File(tmpdir + "/" + (filename + (idx == 0 ? "" : "_" + idx)) + "." + extention)).exists()) {
			idx++;
		}
		return file;
	}
	
	public static String makePathString(long id) {
		String tmp = String.format("%019d", id);
		tmp = tmp.substring(0, 3) + "/" + tmp.substring(3, 6) + "/" + tmp.substring(6, 9) + "/"
				+ tmp.substring(9, 12) + "/" + tmp.substring(12, 13) + "/" + tmp.substring(13, 15) + "/"
				+ tmp.substring(15, 17) + "/" + tmp.substring(17, 19);
		
		return tmp;
	}
	
	/**
	 * 시간에 따른 경로 지정하기
	 * yyyy/MM/dd/HH/mm/id
	 * @param id
	 * @return
	 */
	public static String makeDatePathString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH/mm");
		String path = sdf.format(new Date());	
		
		return path;
	}
	
	public static void move(File src, String dst, String filename) {
//		log.debug("move from: {}, to: {}", src, dst + filename);
//		System.out.println("move from: " + src + ", to: " + dst + filename);
		File dir = new File(dst);
		if (dir.exists() == false) {
			dir.mkdirs();
		}
		File file = new File(dst + "/" + filename);
		
		if (src.renameTo(file) == false) {
//			log.warn("cannot move");
//			System.out.println("cannot move");
			moveFromReadWrite(src, dst, filename);
		}
	}
	
	public static void deletes(File file) {
		if (file.exists()) {
			file.delete();
			
			File parent = file.getParentFile();
			if (parent.listFiles().length == 0) {
				deletes(parent);
			}
		}
	}
	
	public static File makeTmpFile(String extension) {
		return new File(tmpdir + "/" + makeFilename() + "." + extension);
	}
	
	public static String makeFilename() {
		long current = System.currentTimeMillis();
		return String.valueOf(current);
	}
	
	public static String getName(String file) {
		int idx = file.lastIndexOf('.');
		if (idx != -1) {
			return file.substring(0, idx);
		}
		
		return "";
	}
	
	public static String getExtension(String file) {
		int idx = file.lastIndexOf('.');
		if (idx != -1) {
			return file.substring(idx + 1);
		}
		
		return "";
	}
	
	public static boolean isImageFile(File file) {
		try {
			if (ImageIO.read(file) == null) {
				return false;
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * 파일 내용을 가져오는 함수
	 * @param path
	 * @return
	 */
	public static String getFileBody(String path) {
		String body = null;

		FileInputStream fis = null;
		try {
			File file = new ClassPathResource(path).getFile();
			fis = new FileInputStream(file);
			body = StringUtils.valueOf(fis);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return body;
	}

	private static void moveFromReadWrite(File src, String dst, String filename) {
//		System.out.println("moveFromReadWrite from: " + src + ", to: " + dst + filename);
		File dir = new File(dst);
		if (dir.exists() == false) {
			dir.mkdirs();
		}
		File file = new File(dst + "/" + filename);

		FileInputStream sis = null;
		FileOutputStream dos = null;
		try {
			sis = new FileInputStream(src);
			dos = new FileOutputStream(file);

			byte[] buffer = new byte[1024];
			int read;
			while ((read = sis.read(buffer)) != -1) {
				dos.write(buffer, 0, read);
			}

			deletes(src);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sis != null) {
				try {
					sis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
