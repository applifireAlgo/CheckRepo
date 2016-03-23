Ext.define('Mobproj.view.reportui.ReportView', {
	extend : 'Ext.panel.Panel',
	requires : [ 'Mobproj.view.reportui.querycriteria.QueryCriteriaView',
			'Mobproj.view.reportui.datachart.DataChartViewTab',
			'Mobproj.view.reportui.datachart.DataChartViewPanel',
			'Mobproj.view.reportui.ReportViewController' ,
			'Mobproj.view.fw.MainDataPointPanel',
			'Mobproj.view.googlemaps.map.MapPanel'
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
