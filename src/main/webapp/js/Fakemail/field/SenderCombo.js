Ext.define('Fakemail.field.SenderCombo', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.senderCombo',
	requires: [ 'Fakemail.model.Sender' ],
	store: {
		model: 'Fakemail.model.Sender',
		proxy: {
			type: 'ajax',
			url: 'api/sender'
		},
		sorters: [ 'address' ],
		autoLoad: true,
		pageSize: 0
	},
	valueField: 'id',
	displayField: 'address',
	forceSelection: true,
	queryMode: 'local'
});
