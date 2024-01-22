package com.bumdori.spring.generator;

import com.bumdori.util.ClassUtils;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExceptionGenerator {
	
	private static final String PACKAGE = "kr.ant.kpa.pharmcrew" + ".validation";
	
	public static void main(String[] args) throws IOException {
		for (VALIDATION validation: VALIDATION.values()) {
			if (validation == VALIDATION.OK) {
				continue;
			}
			
			String name = "";
			String[] nameTokens = validation.name().split("_");
			for (String nameToken: nameTokens) {
//				System.out.println("nameToken: " + nameToken);
				name += ClassUtils.capitalize(nameToken.toLowerCase());
//				System.out.println("name: " + name);
			}
			name += "Exception";
//			System.out.println("name: " + name);
			
			File file = new File(String.format("src/main/java/%s/%s.java", PACKAGE.replaceAll("\\.", "/") + "/exception/gen", name));
			System.out.println("file: " + file.getAbsolutePath());
			file.createNewFile();
			
			FileWriter writer = new FileWriter(file);
			writer.append("package ");	writer.append(PACKAGE.replaceAll("/", "."));	writer.append(".exception.gen;\r\n\r\n");
			writer.append("import ");	writer.append(PACKAGE);	writer.append(".VALIDATION;\r\n");
			writer.append("import ");	writer.append(PACKAGE);	writer.append(".exception.AbsValidationException;\r\n\r\n");
			writer.append("public class ");	writer.append(name);	writer.append(" extends AbsValidationException {\r\n\r\n");
			writer.append("	public ");	writer.append(name);	writer.append("() {\r\n");
			writer.append("		super(VALIDATION.");	writer.append(validation.name());	writer.append(");\r\n");
			writer.append("	}\r\n\r\n");
			writer.append("}\r\n\r\n");
			writer.flush();
			writer.close();
		}
	}

}
