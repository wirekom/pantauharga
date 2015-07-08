/*

created by Unknow

modified by introtik

Mohammed Ahmed: maa@intro.ps

22/May/2012
*/

CKEDITOR.dialog.add('mp3player', function(editor){
	return{
	title:'Embed Mp3',
	minWidth:350,
	minHeight:240,
	onShow:function(){
		this.getContentElement('general','content').getInputElement().setValue('')
		},
	onOk:function(){
			//val = this.getContentElement('general','content').getInputElement().getValue();
			//CKEDITOR.dom.selection.scrollIntoView = true;
			var dialog = this;
			editor.insertHtml('<object type="application/x-shockwave-flash" data="/Scripts/ckeditor/plugins/mp3player/player_mp3.swf" width="200" height="20">'
					+'	<param name="movie" value="player_mp3.swf" />'
					+'	<param name="bgcolor" value="#ffffff" />'
					+'	<param name="FlashVars" value="mp3='+dialog.getValueOf( 'general', 'content' )+'&amp;autoplay=0" />'
					+'</object>');
					/*	editor.insertHtml('<audio src="'+dialog.getValueOf( 'general', 'content' )+'" controls loop>'
						+'<p>Your browser does not support the <code>audio</code> element </p>'
						+'</audio>'
					);*/
				/*	
			editor.insertHtml('<audio controls>'
				
				+ '<source src="'+dialog.getValueOf( 'general', 'content' )+'" type="audio/mpeg">'
				+ 'Your browser does not support the audio element.'
				+'</audio>');
				CKEDITOR.dialog.getCurrent().hide()*/

		},
	contents:[
		{
			label:'Basic Setting',
			id:'general',
			elements:[{
					type:'html',
					id:'pasteMsg',
					html:'<div style="white-space:normal;width:500px;text-align:center;"><img style="margin:5px auto;" src="'
						+CKEDITOR.getUrl(CKEDITOR.plugins.getPath('mp3player')
						+'images/mp3_large.png')
						+'"><br /> Please copy and paste the Mp3 URL here'
						+'</div>'},
						{
						type:'html',
						id:'content',
						style:'width:340px;height:90px',
						html:'<input type="text" class="cke_dialog_ui_input_text" style="'+'border:1px solid black;'+'background:white">',
						focus:function(){this.getElement().focus()}
						},
							{
								type: 'button',
								id: 'browse',
								// v-align with the 'txtUrl' field.
								// TODO: We need something better than a fixed size here.
								style: 'display:inline-block;margin-top:14px;',
								align: 'center',
								label: editor.lang.common.browseServer,
								hidden: false, // di default true
								filebrowser: 'general:content'
							} ]
		},
		{
					id: 'Upload',
					hidden: false,// di default true
					filebrowser: 'uploadButton',
					label: editor.lang.image.upload,
					elements: [ {
						type: 'file',
						id: 'upload',
						label: editor.lang.image.btnUpload,
						style: 'height:40px',
						size: 38
					},
					{
						type: 'fileButton',
						id: 'uploadButton',
						filebrowser: 'info:txtUrl',
						label: editor.lang.image.btnUpload,
						'for': [ 'Upload', 'upload' ]
					} ]
				}]
		}
	});
