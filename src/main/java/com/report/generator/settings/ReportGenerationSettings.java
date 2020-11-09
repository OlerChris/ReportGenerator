package com.report.generator.settings;

public class ReportGenerationSettings {
	
	public static final int DEFAULT_SEARCH_RADIUS = 50;
	public static final int MAX_ROW_NUMBER = Integer.MAX_VALUE;

	private boolean preserveNodes = true;
	private int searchRadius = DEFAULT_SEARCH_RADIUS;
	private int maxRowNum = MAX_ROW_NUMBER;
	private boolean optimisticParsing = false;
	private boolean filtersEnabled = true;

	public boolean isPreserveNodes() {
		return preserveNodes;
	}

	public void setPreserveNodes(boolean preserveNodes) {
		this.preserveNodes = preserveNodes;
	}

	public int getSearchRadius() {
		return searchRadius;
	}

	public void setSearchRadius(int searchRadius) {
		this.searchRadius = searchRadius;
	}

	public int getMaxRowNum() {
		return maxRowNum;
	}

	public void setMaxRowNum(int maxRowNum) {
		this.maxRowNum = maxRowNum;
	}

	public boolean isOptimisticParsing() {
		return optimisticParsing;
	}

	public void setOptimisticParsing(boolean optimisticParsing) {
		this.optimisticParsing = optimisticParsing;
	}

	public boolean isFiltersEnabled() {
		return filtersEnabled;
	}

	public void setFiltersEnabled(boolean filtersEnabled) {
		this.filtersEnabled = filtersEnabled;
	}
	
}
