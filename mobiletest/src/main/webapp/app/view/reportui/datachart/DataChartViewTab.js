Ext.define('Mobiletest.view.reportui.datachart.DataChartViewTab', {
	extend : 'Ext.tab.Panel',
	requires : [ 'Mobiletest.view.reportui.datachart.DataChartTController',
	             'Mobiletest.view.reportui.datachart.datagrid.DataGridView',
	             'Mobiletest.view.reportui.datachart.chart.ChartTabView',
	             'Mobiletest.view.reportui.datachart.ChartPointView' ],
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