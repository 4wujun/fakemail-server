Ext.define('Fakemail.model.Email', {
	extend: 'Ext.data.Model',
	fields: [
		{ name: 'id', type: 'int' },
		{ name: 'sender', type: 'string' },
		{ name: 'recipient', type: 'string', useNull: true },
		{ name: 'dateSent', type: 'date' },
		{ name: 'subject', type: 'string' }
	]
});
