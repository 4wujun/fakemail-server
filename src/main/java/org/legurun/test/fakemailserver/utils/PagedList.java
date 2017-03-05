package org.legurun.test.fakemailserver.utils;

import java.util.List;

public class PagedList<E> {
	private List<E> data;
	private Integer total = 0;


	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
