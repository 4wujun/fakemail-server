Ext.define('Fakemail.view.RechercheListe', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.rechercheListe',
	requires: [ 'Ext.ux.DataTip' ],
	defaults: {
		enableTextSelection: true
	},
	columns: [{
		xtype: 'rownumberer'
	}, {
		text: 'Sender',
		dataIndex: 'sender',
		width: 200,
		filter: {
			type: 'string'
		}
	}, {
		text: 'Recipient',
		dataIndex: 'recipient',
		width: 200,
		filter: {
			type: 'string'
		}
	}, {
		text: 'Sent date',
		dataIndex: 'dateSent',
		xtype: 'datecolumn',
		format: 'd/m/Y G:i:s',
		width: 150,
		filter: {
			type: 'date'
		}
	}, {
		text: 'Subject',
		dataIndex: 'subject',
		flex: 1,
		filter: {
			type: 'string'
		}
	}],
	dockedItems: [{
		xtype: 'pagingtoolbar',
		displayInfo: true
	}],
	plugins: [{
		ptype: 'gridfilters'
	}],
	initComponent: function () {
		var app = Fakemail.getApplication();
		this.store = app.getEmailStore();
		this.callParent();
	}
});
