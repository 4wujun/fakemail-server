Ext.define('Fakemail.store.Email', {
	extend: 'Ext.data.Store',
	requires: [ 'Fakemail.model.Email' ],
	model: 'Fakemail.model.Email',
	proxy: {
		type: 'ajax',
		url: 'api/mail',
		actionMethods: {
			read: 'POST'
		},
		reader: {
			rootProperty: 'data',
			totalProperty: 'total'
		}
	},
	pageSize: 5
});
