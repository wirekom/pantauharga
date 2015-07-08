/*

created by Unknow

modified by introtik

Mohammed Ahmed: maa@intro.ps

22/May/2012
*/
// (function(){var mp3playerCmd={exec:function(editor){editor.openDialog('mp3player');return}};
// CKEDITOR.plugins.add('mp3player',{
	// lang:['en','ar'],
	// requires:['dialog'],
	// init:function(editor){
		// var commandName='mp3player';
		// editor.addCommand(commandName,mp3playerCmd);
		// editor.ui.addButton('Mp3Player',{
			// label:editor.lang.mp3player.button,
			// command:commandName,
			// icon:this.path+"images/mp3.png"
			// });
		// CKEDITOR.dialog.add(commandName,CKEDITOR.getUrl(this.path+'dialogs/mp3player.js'))}})})();

CKEDITOR.plugins.add( 'mp3player', {
    init: function( editor ) {
		CKEDITOR.dialog.add( 'mp3player', this.path + 'dialogs/mp3player.js' );
        editor.addCommand( 'mp3player', new CKEDITOR.dialogCommand( 'mp3player' ) );
		editor.ui.addButton( 'Mp3Player', {
			label: 'Insert Mp3',
			command: 'mp3player',
    icon: this.path+"images/mp3.png",
			toolbar: 'insert,100'
		});
    }
});
