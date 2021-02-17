package com.report.generator.settings;


public class ReportGeneratorSettings {
	
	public static final int DEFAULT_SEARCH_RADIUS = 50;
	public static final int MAX_ROW_NUMBER = Integer.MAX_VALUE;

	private boolean preserveNodes = true;
	private int searchRadius = DEFAULT_SEARCH_RADIUS;
	private int maxRowNum = MAX_ROW_NUMBER;
	private boolean optimisticParsing = false;
	private boolean filtersEnabled = true;
	
	private final ReportStyling styling = new ReportStyling();

	public boolean isPreserveNodes() {
		return preserveNodes;
	}

	public ReportGeneratorSettings setPreserveNodes(boolean preserveNodes) {
		this.preserveNodes = preserveNodes;
		return this;
	}

	public int getSearchRadius() {
		return searchRadius;
	}

	public ReportGeneratorSettings setSearchRadius(int searchRadius) {
		this.searchRadius = searchRadius;
		return this;
	}

	public int getMaxRowNum() {
		return maxRowNum;
	}

	public ReportGeneratorSettings setMaxRowNum(int maxRowNum) {
		this.maxRowNum = maxRowNum;
		return this;
	}

	public boolean isOptimisticParsing() {
		return optimisticParsing;
	}

	public ReportGeneratorSettings setOptimisticParsing(boolean optimisticParsing) {
		this.optimisticParsing = optimisticParsing;
		return this;
	}

	public boolean isFiltersEnabled() {
		return filtersEnabled;
	}

	public ReportGeneratorSettings setFiltersEnabled(boolean filtersEnabled) {
		this.filtersEnabled = filtersEnabled;
		return this;
	}

	public ReportStyling getStyling() {
		return styling;
	}
	
}
