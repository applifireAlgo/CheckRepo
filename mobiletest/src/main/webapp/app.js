/*
 * This file is generated and updated by Sencha Cmd. You can edit this file as
 * needed for your application, but these edits will have to be merged by
 * Sencha Cmd when upgrading.
 */
Ext.application({
    name: 'Mobiletest',

    extend: 'Mobiletest.Application',
    
/**AppPathDetails**/autoCreateViewport: (Ext.os.deviceType=='Desktop')?'Mobiletest.view.mainleftmenutree.MainPanel':'Mobiletest.view.mobileview.main.MainPanel',
	
    //-------------------------------------------------------------------------
    // Most customizations should be made to Mobiletest.Application. If you need to
    // customize this file, doing so below this section reduces the likelihood
    // of merge conflicts when upgrading to new versions of Sencha Cmd.
    //-------------------------------------------------------------------------
});
