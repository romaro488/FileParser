package com.example.demo.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

public class ResourceFactory implements FactoryBean<File>, InitializingBean {
	private File file;
	private String fileName;

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public File getObject() {
		return file;
	}

	@Override
	public Class<?> getObjectType() {
		return File.class;
	}

	@Override
	public void afterPropertiesSet() throws FileNotFoundException {
		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource(fileName);
		if (resource == null) {
			throw new FileNotFoundException("File not found");
		}
		String filePath = resource.getFile();
		file = new File(filePath);

	}
}
