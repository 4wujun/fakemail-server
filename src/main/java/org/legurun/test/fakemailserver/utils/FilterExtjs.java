package org.legurun.test.fakemailserver.utils;

public class FilterExtjs {
	private String property;
	private String value;

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {		
		return "FilterExtjs [" + this.property + "=" + this.value + "]";
	}
}
