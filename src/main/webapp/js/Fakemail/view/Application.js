Ext.define('Fakemail.view.Application', {
	extend: 'Ext.container.Viewport',
	layout: 'border',
	requires: [
		'Fakemail.view.RechercheCriteres',
		'Fakemail.view.RechercheListe',
		'Fakemail.controller.Search' ],
	items: [{
		xtype: 'rechercheCriteres',
		region: 'north',
		reference: 'criteres'
	}, {
		xtype: 'rechercheListe',
		region: 'center',
		reference: 'liste'
	}],
	controller: 'search'
});
