Ext.define('Mobproj.view.reportui.datachart.DataChartViewTab', {
	extend : 'Ext.tab.Panel',
	requires : [ 'Mobproj.view.reportui.datachart.DataChartTController',
	             'Mobproj.view.reportui.datachart.datagrid.DataGridView',
	             'Mobproj.view.reportui.datachart.chart.ChartTabView',
	             'Mobproj.view.reportui.datachart.ChartPointView' ],
	controller : 'datacharttController',
	xtype : 'datachart-tabpanel',
	tabPosition : 'bottom',
	bodyStyle : 'background:#D8D8D8',
	listeners : {
		scope : "controller",
		tabchange : 'tabchange',
		afterrender:'afterTabPanelRender'
	}
});