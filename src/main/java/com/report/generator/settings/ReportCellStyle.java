package com.report.generator.settings;

import java.awt.Color;

import org.apache.poi.ss.usermodel.FillPatternType;

public class ReportCellStyle {

	private Color color;
	private Color backgroundColor;
	private FillPatternType fillType = FillPatternType.SOLID_FOREGROUND;
	public Color getColor() {
		return color;
	}
	public ReportCellStyle setColor(Color color) {
		this.color = color;
		return this;
	}
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public ReportCellStyle setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		return this;
	}
	public FillPatternType getFillType() {
		return fillType;
	}
	public ReportCellStyle setFillType(FillPatternType fillType) {
		this.fillType = fillType;
		return this;
	}
	
}
