Ext.define('Mobproj.view.databrowsercalendar.DBCalendar', {
	extend : 'Mobproj.view.databrowsercalendar.DBCalendarView',
	requires : [ 'Mobproj.view.databrowsercalendar.DBCalendarController',
	             'Mobproj.view.databrowsercalendar.DBCalendarView','Ext.layout.container.Card',
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
