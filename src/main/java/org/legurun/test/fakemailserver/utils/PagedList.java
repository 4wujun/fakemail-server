package org.legurun.test.fakemailserver.utils;

import java.util.List;

public class PagedList<E> {
	private List<E> data;
	private Number total = 0;


	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

	public Number getTotal() {
		return total;
	}

	public void setTotal(Number total) {
		this.total = total;
	}
}
