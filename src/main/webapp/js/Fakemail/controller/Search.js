Ext.define('Fakemail.controller.Search', {
	extend: 'Ext.app.ViewController',
	alias: 'controller.search',

	onSearch: function() {
		var form = this.lookupReference('criteres').getForm();
		if (form.isValid()) {
			var values = form.getFieldValues();
			var list = this.lookupReference('liste');
			var store = list.getStore();
			store.filter([{
				property: 'senderId',
				value: values['senderId']
			}]);
		}
	},
	onResetForm: function() {
		var form = this.lookupReference('criteres').getForm();
		form.reset();
	}
});
