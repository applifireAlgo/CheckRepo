Ext.define('Mobiletest.view.mobileview.main.TopPanel.TopMenu', { 
	extend: 'Ext.toolbar.Toolbar',
    xtype: 'topMenu',
    requires:['Mobiletest.view.mobileview.main.TopPanel.TopMenuController',/*'Mobiletest.view.mobileview.main.ResourceWindow'*/],
    controller :'topMenuController',
    border:0,
    itemId:'topMenuPanel',
    autoScroll :true,
    items:[]
});