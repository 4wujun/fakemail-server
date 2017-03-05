Ext.define('Fakemail.view.RechercheListe', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.rechercheListe',
	columns: [{
		text: 'Sender',
		dataIndex: 'sender'
	}, {
		text: 'Recipient',
		dataIndex: 'recipient'
	}, {
		text: 'Date d\'envoi',
		dataIndex: 'dateSent',
		xtype: 'datecolumn'
	}, {
		text: 'Subject',
		dataIndex: 'subject'
	}],
	initComponent: function () {
		var app = Fakemail.getApplication();
		this.store = app.getEmailStore();
		this.callParent();
	}
});
