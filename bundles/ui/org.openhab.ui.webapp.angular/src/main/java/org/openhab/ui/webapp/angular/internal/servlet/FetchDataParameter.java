package org.openhab.ui.webapp.angular.internal.servlet;

import java.util.Calendar;

import org.openhab.core.persistence.FilterCriteria;
import org.openhab.core.persistence.QueryablePersistenceService;

public class FetchDataParameter {
	public QueryablePersistenceService queryablePersistenceService;
	public String itemName;
	public FilterCriteria filter;
	public Calendar end;
	public Calendar begin;

	public FetchDataParameter(QueryablePersistenceService queryablePersistenceService, String itemName, FilterCriteria filter,
			Calendar end, Calendar begin) {
		this.queryablePersistenceService = queryablePersistenceService;
		this.itemName = itemName;
		this.filter = filter;
		this.end = end;
		this.begin = begin;
	}
}