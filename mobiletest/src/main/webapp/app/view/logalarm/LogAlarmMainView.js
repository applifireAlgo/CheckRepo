/**
 * 
 */
Ext.define('Mobiletest.view.logalarm.LogAlarmMainView', {
	extend : 'Ext.form.Panel',
	xtype : 'logAlarmMainView',
	requires : [ 'Mobiletest.view.logalarm.LogAlarmMainViewController',
			'Mobiletest.view.logalarm.tree.LogAlarmTreePanel',
			'Mobiletest.view.logalarm.mainscreen.LogAlarmMainViewTabPanel',
			'Ext.layout.container.Column','Ext.form.field.Hidden' ],

	controller : 'logAlarmMainViewController',

	layout : 'column',
	autoScroll : true,
	defaults : {
		margin : 10,
	},
	items : [ {
		columnWidth : '0.3',
		xtype : 'logAlarmTreePanel',
		itemId : 'logAlarmTreePanel',
	}, {
		columnWidth : '0.7',
		xtype :  'logAlarmMainViewTabPanel',
		itemId : 'logAlarmMainViewTabPanel'
	}],
	buttons : [ {
		text : 'Clear',
		itemId : 'clearButton',
		handler : 'onClearButton',
	}, {
		text : 'Update Module',
		itemId : 'saveButton',
		formBind : true,
		handler : 'onSaveButton',
	} ]
});
