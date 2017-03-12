(function($) {
	'use strict';

	$.extend($.fn.bootstrapTable.defaults, {
		silentSort: true,
    	dataField: 'data',
    	totalField: 'total',
    	sidePagination: 'server',
    	showColumns: true,
    	showRefresh: true,
    	idField: 'id',
    	uniqueId: 'id',
    	escape: true,
    	cache: true
	});
	$.extend($.fn.bootstrapTable.columnDefaults, {
		sortable: true
	});
})(jQuery);