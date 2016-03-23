Ext.define('Mobiletest.view.reportui.ReportView', {
	extend : 'Ext.panel.Panel',
	requires : [ 'Mobiletest.view.reportui.querycriteria.QueryCriteriaView',
			'Mobiletest.view.reportui.datachart.DataChartViewTab',
			'Mobiletest.view.reportui.datachart.DataChartViewPanel',
			'Mobiletest.view.reportui.ReportViewController' ,
			'Mobiletest.view.fw.MainDataPointPanel',
			'Mobiletest.view.googlemaps.map.MapPanel'
			],
	xtype : 'reportview',
	controller : 'reportviewController',
	layout : 'border',
	isCustomReport:false,
	reportWidgets :["1","2","3","4"],
	//autoScroll : true,
	//margin : '3 0 5 0',
	height:500,
	width:"100%",
	listeners : {
		scope : 'controller',
		afterrender : 'renderReport'
	}
});
