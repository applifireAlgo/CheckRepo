Ext.define('Mobiletest.view.databrowsercalendar.DBCalendar', {
	extend : 'Mobiletest.view.databrowsercalendar.DBCalendarView',
	requires : [ 'Mobiletest.view.databrowsercalendar.DBCalendarController',
	             'Mobiletest.view.databrowsercalendar.DBCalendarView','Ext.layout.container.Card',
	             'Ext.calendar.view.Day', 'Ext.calendar.view.Week',
	             'Ext.calendar.view.Month',
	             'Ext.calendar.form.EventDetails',
	             'Ext.calendar.data.EventMappings'],
	controller : 'databrowsercalendar',
	items : [],
	listeners : {
		afterrender : 'loadData',
		scope : "controller"
	}
});
