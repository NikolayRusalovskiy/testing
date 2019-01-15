package com.gl.utils;

import org.openqa.selenium.WebDriver;

public class AbstractContainer {
	
	protected static WebDriver driver;

	public AbstractContainer(WebDriver driver) {
		this.driver = driver;
	}

}
