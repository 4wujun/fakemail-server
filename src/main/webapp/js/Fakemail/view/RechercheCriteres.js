Ext.define('Fakemail.view.RechercheCriteres', {
	extend: 'Ext.form.Panel',
	alias: 'widget.rechercheCriteres',
	requires: [
		'Fakemail.controller.Search',
		'Fakemail.field.SenderCombo' ],
	items: {
		xtype: 'senderCombo',
		fieldLabel: 'Sender',
		name: 'senderId'
	},
	buttons: [{
		text: 'Submit',
		formBind: true,
		handler: 'onSearch'
	}, {
		text: 'Reset',
		handler: 'onResetForm'
	}]
});
