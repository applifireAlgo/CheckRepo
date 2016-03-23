Ext.define('Mobiletest.mobiletest.web.com.view.defaultcontext.defaultdomain.PersonalInfoMain', {
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "PersonalInfoMainController",
     "restURL": "/PersonalInfo",
     "defaults": {
          "split": true
     },
     "requires": ["Mobiletest.mobiletest.shared.com.model.defaultcontext.defaultdomain.PersonalInfoModel", "Mobiletest.mobiletest.web.com.controller.defaultcontext.defaultdomain.PersonalInfoMainController", "Mobiletest.view.fw.component.DateTimeField", "Mobiletest.view.fw.component.DateTimePicker", "Mobiletest.mobiletest.shared.com.viewmodel.defaultcontext.defaultdomain.PersonalInfoViewModel"],
     "communicationLog": [],
     "tabPosition": "bottom",
     "items": [{
          "title": "Data Browser",
          "layout": "border",
          "defaults": {
               "split": true
          },
          "autoScroll": false,
          "customWidgetType": "vdBorderLayout",
          "items": [{
               "xtype": "tabpanel",
               "customWidgetType": "vdTabLayout",
               "margin": "5 0 5 5",
               "border": 1,
               "style": {
                    "borderColor": "#f6f6f6",
                    "borderStyle": "solid",
                    "borderWidth": "1px"
               },
               "displayName": "PersonalInfo",
               "name": "PersonalInfoTreeContainer",
               "itemId": "PersonalInfoTreeContainer",
               "restURL": "/PersonalInfo",
               "autoScroll": false,
               "collapsible": true,
               "titleCollapse": true,
               "collapseMode": "header",
               "collapsed": false,
               "items": [{
                    "xtype": "treepanel",
                    "customWidgetType": "vdTree",
                    "title": "Browse",
                    "name": "entityTreePanel",
                    "useArrows": true,
                    "rootVisible": false,
                    "itemId": "PersonalInfoTree",
                    "listeners": {
                         "select": "treeClick"
                    },
                    "tbar": [{
                         "xtype": "triggerfield",
                         "customWidgetType": "vdTriggerField",
                         "emptyText": "Search",
                         "triggerCls": "",
                         "listeners": {
                              "change": "onTriggerfieldChange",
                              "buffer": 250
                         }
                    }, "->", {
                         "xtype": "tool",
                         "type": "refresh",
                         "tooltip": "Refresh Tree Data",
                         "handler": "onTreeRefreshClick"
                    }]
               }, {
                    "title": "Advance Search",
                    "xtype": "form",
                    "customWidgetType": "vdFormpanel",
                    "itemId": "queryPanel",
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "isDockedItem": true,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill"
                         }, {
                              "xtype": "button",
                              "customWidgetType": "vdButton",
                              "text": "Filter",
                              "name": "filterButton",
                              "handler": "onFilterClick"
                         }]
                    }],
                    "items": []
               }],
               "region": "west",
               "width": "20%"
          }, {
               "region": "center",
               "layout": "border",
               "defaults": {
                    "split": true
               },
               "customWidgetType": "vdBorderLayout",
               "items": [{
                    "customWidgetType": "vdFormpanel",
                    "xtype": "form",
                    "displayName": "PersonalInfo",
                    "title": "PersonalInfo",
                    "name": "PersonalInfo",
                    "itemId": "PersonalInfoForm",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "pk",
                         "itemId": "pk",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "pk",
                         "margin": "5 5 5 5",
                         "fieldLabel": "pk<font color='red'> *<\/font>",
                         "fieldId": "B4404DEF-3A58-49D0-8BB0-29E3238933ED",
                         "hidden": true,
                         "value": "",
                         "bindable": "pk"
                    }, {
                         "name": "fName",
                         "itemId": "fName",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "fName",
                         "margin": "5 5 5 5",
                         "fieldLabel": "fName<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "30A57FB0-7F40-4264-82A1-3829AF6C8FAD",
                         "minLength": "1",
                         "maxLength": "256",
                         "bindable": "fName",
                         "columnWidth": 0.5
                    }, {
                         "name": "age",
                         "itemId": "age",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "age",
                         "margin": "5 5 5 5",
                         "fieldLabel": "age<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "5DD6234B-9949-466D-92F7-0E38C779F3DB",
                         "minValue": "-2147483648",
                         "maxValue": "2147483647",
                         "bindable": "age",
                         "columnWidth": 0.5
                    }, {
                         "name": "dateOfBirth",
                         "itemId": "dateOfBirth",
                         "xtype": "customdatetimefield",
                         "customWidgetType": "vdCustomDateTime",
                         "displayName": "DateOfBirth",
                         "margin": "5 5 5 5",
                         "submitFormat": "d-m-Y H:i:s",
                         "format": "d-m-Y H:i:s",
                         "fieldLabel": "DateOfBirth<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "A85AFCCD-1592-43DB-90F2-842D928F61CB",
                         "bindable": "dateOfBirth",
                         "columnWidth": 0.5
                    }, {
                         "name": "versionId",
                         "itemId": "versionId",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "versionId",
                         "margin": "5 5 5 5",
                         "value": "-1",
                         "fieldLabel": "versionId",
                         "fieldId": "49F00D1C-B4AD-424C-8EA4-09AAD15A3A73",
                         "bindable": "versionId",
                         "hidden": true
                    }],
                    "layout": "column",
                    "defaults": {
                         "columnWidth": 0.5,
                         "labelAlign": "left",
                         "labelWidth": 200
                    },
                    "autoScroll": true,
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "ui": "footer",
                         "isDockedItem": true,
                         "parentId": 1,
                         "customId": 915,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 915,
                              "customId": 459
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "saveFormButton",
                              "margin": "0 5 0 5",
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 915,
                              "customId": 460,
                              "listeners": {
                                   "click": "saveForm"
                              }
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "resetFormButton",
                              "margin": "0 5 0 5",
                              "text": "Reset",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "resetFormButton",
                              "parentId": 915,
                              "customId": 461,
                              "listeners": {
                                   "click": "resetForm"
                              }
                         }],
                         "defaults": {}
                    }],
                    "listeners": {
                         "scope": "controller"
                    },
                    "tools": [{
                         "type": "help",
                         "tooltip": "Console",
                         "handler": "onConsoleClick"
                    }, {
                         "type": "refresh",
                         "tooltip": "Refresh Tab",
                         "handler": "init"
                    }],
                    "extend": "Ext.form.Panel",
                    "region": "center"
               }, {
                    "xtype": "gridpanel",
                    "customWidgetType": "vdGrid",
                    "displayName": "PersonalInfo",
                    "title": "Details Grid",
                    "name": "PersonalInfoGrid",
                    "itemId": "PersonalInfoGrid",
                    "restURL": "/PersonalInfo",
                    "store": [],
                    "bodyPadding": 10,
                    "columns": [{
                         "header": "pk",
                         "dataIndex": "pk",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryDisplay",
                         "dataIndex": "primaryDisplay",
                         "hidden": true
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "fName",
                         "dataIndex": "fName",
                         "flex": 1
                    }, {
                         "header": "age",
                         "dataIndex": "age",
                         "flex": 1
                    }, {
                         "header": "DateOfBirth",
                         "dataIndex": "dateOfBirth",
                         "flex": 1
                    }, {
                         "header": "createdBy",
                         "dataIndex": "createdBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "createdDate",
                         "dataIndex": "createdDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedBy",
                         "dataIndex": "updatedBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedDate",
                         "dataIndex": "updatedDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "versionId",
                         "dataIndex": "versionId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "activeStatus",
                         "dataIndex": "activeStatus",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "txnAccessCode",
                         "dataIndex": "txnAccessCode",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "xtype": "actioncolumn",
                         "customWidgetType": "vdActionColumn",
                         "width": 30,
                         "sortable": false,
                         "menuDisable": true,
                         "items": [{
                              "icon": "images/delete.gif",
                              "tooltip": "Delete Record",
                              "handler": "onDeleteActionColumnClickMainGrid"
                         }]
                    }],
                    "listeners": {
                         "itemclick": "onGridItemClick"
                    },
                    "tools": [{
                         "type": "refresh",
                         "tooltip": "Refresh Grid Data",
                         "handler": "onGridRefreshClick"
                    }],
                    "collapsible": true,
                    "titleCollapse": true,
                    "collapseMode": "header",
                    "region": "south",
                    "height": "40%"
               }]
          }]
     }, {
          "title": "Add New",
          "itemId": "addNewForm",
          "layout": "border",
          "customWidgetType": "vdBorderLayout",
          "autoScroll": false,
          "items": [{
               "customWidgetType": "vdFormpanel",
               "xtype": "form",
               "displayName": "PersonalInfo",
               "title": "PersonalInfo",
               "name": "PersonalInfo",
               "itemId": "PersonalInfoForm",
               "bodyPadding": 10,
               "items": [{
                    "name": "pk",
                    "itemId": "pk",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "pk",
                    "margin": "5 5 5 5",
                    "fieldLabel": "pk<font color='red'> *<\/font>",
                    "fieldId": "B4404DEF-3A58-49D0-8BB0-29E3238933ED",
                    "hidden": true,
                    "value": "",
                    "bindable": "pk"
               }, {
                    "name": "fName",
                    "itemId": "fName",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "fName",
                    "margin": "5 5 5 5",
                    "fieldLabel": "fName<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "30A57FB0-7F40-4264-82A1-3829AF6C8FAD",
                    "minLength": "1",
                    "maxLength": "256",
                    "bindable": "fName",
                    "columnWidth": 0.5
               }, {
                    "name": "age",
                    "itemId": "age",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "age",
                    "margin": "5 5 5 5",
                    "fieldLabel": "age<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "5DD6234B-9949-466D-92F7-0E38C779F3DB",
                    "minValue": "-2147483648",
                    "maxValue": "2147483647",
                    "bindable": "age",
                    "columnWidth": 0.5
               }, {
                    "name": "dateOfBirth",
                    "itemId": "dateOfBirth",
                    "xtype": "customdatetimefield",
                    "customWidgetType": "vdCustomDateTime",
                    "displayName": "DateOfBirth",
                    "margin": "5 5 5 5",
                    "submitFormat": "d-m-Y H:i:s",
                    "format": "d-m-Y H:i:s",
                    "fieldLabel": "DateOfBirth<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "A85AFCCD-1592-43DB-90F2-842D928F61CB",
                    "bindable": "dateOfBirth",
                    "columnWidth": 0.5
               }, {
                    "name": "versionId",
                    "itemId": "versionId",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "versionId",
                    "margin": "5 5 5 5",
                    "value": "-1",
                    "fieldLabel": "versionId",
                    "fieldId": "49F00D1C-B4AD-424C-8EA4-09AAD15A3A73",
                    "bindable": "versionId",
                    "hidden": true
               }],
               "layout": "column",
               "defaults": {
                    "columnWidth": 0.5,
                    "labelAlign": "left",
                    "labelWidth": 200
               },
               "autoScroll": true,
               "dockedItems": [{
                    "xtype ": "toolbar",
                    "customWidgetType": "vdBBar",
                    "dock": "bottom",
                    "ui": "footer",
                    "isDockedItem": true,
                    "parentId": 1,
                    "customId": 915,
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill",
                         "parentId": 915,
                         "customId": 459
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "saveFormButton",
                         "margin": "0 5 0 5",
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "parentId": 915,
                         "customId": 460,
                         "listeners": {
                              "click": "saveForm"
                         }
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "resetFormButton",
                         "margin": "0 5 0 5",
                         "text": "Reset",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "resetFormButton",
                         "parentId": 915,
                         "customId": 461,
                         "listeners": {
                              "click": "resetForm"
                         }
                    }],
                    "defaults": {}
               }],
               "listeners": {
                    "scope": "controller"
               },
               "tools": [{
                    "type": "help",
                    "tooltip": "Console",
                    "handler": "onConsoleClick"
               }, {
                    "type": "refresh",
                    "tooltip": "Refresh Tab",
                    "handler": "init"
               }],
               "extend": "Ext.form.Panel",
               "region": "center"
          }]
     }]
});