package com.report.generator.settings;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.util.IOUtils;

public class ReportStyling {
	
	private ReportLogo logo = null;
	private Color headerColor;
	private int rowIndent = 0;
	private int colIndent = 0;
	
	public boolean hasLogo() {
		return !(logo==null);
	}
	
	public ReportStyling setLogo(String path, int pictureType, int height, int width) throws FileNotFoundException {
		if(logo == null) {
			logo = new ReportLogo();
		}
		logo.image = new FileInputStream(new File(path));
		logo.pictureType = pictureType;
		logo.height = height;
		logo.width = width;
		logo.logoBackgroundColor = new Color(0, 0, 0);
		return this;
	}
	
	public ReportStyling setLogoBackground(Color backgroundColor) throws FileNotFoundException {
		logo.logoBackgroundColor = backgroundColor;
		return this;
	}
	
	public Color getLogoBackgroundColor() {
		return logo.logoBackgroundColor;
	}

	public byte[] getLogoImage() throws IOException {
		return IOUtils.toByteArray(logo.image);		
	}
	
	public int getLogoPictureType() {
		return logo.pictureType;
	}
	
	public int getHeaderRow() {
		int headerRow = rowIndent;
		if(hasLogo()) {
			headerRow++;
		}
		return headerRow;
	}
	
	public int getStartColumn() {
		return colIndent;
	}
	
	public int getLogoHeight() {
		return logo.height;
	}
	
	public int getLogoWidth() {
		return logo.width;
	}
	
	public Color getHeaderColor() {
		return headerColor;
	}

	public void setHeaderColor(Color headerColor) {
		this.headerColor = headerColor;
	}

	public class ReportLogo {
		private FileInputStream image = null;
		private int pictureType;
		private int height;
		private int width;	
		private Color logoBackgroundColor;
	}
}
