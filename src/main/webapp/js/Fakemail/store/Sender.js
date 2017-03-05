Ext.define('Fakemail.store.Sender', {
	extend: 'Ext.data.Store',
	storeId: 'senderStore',
	requires: [ 'Fakemail.model.Sender' ],
	model: 'Fakemail.model.Sender',
	proxy: {
		type: 'ajax',
		url: 'api/sender'
	},
	sorters: [ 'address' ],
	autoLoad: true,
	pageSize: 0
});
