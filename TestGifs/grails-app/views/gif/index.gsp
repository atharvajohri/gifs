<!doctype html>
<html>
	<head>
		<title>Testing Gifs</title>
		<g:javascript library="jquery" plugin="jquery"></g:javascript>
		<style>
			body {font-size:13px;color:#5d5d5d;margin:0;padding:0;font-family:"arial"}
			#main-container {width:800px;margin:auto;border-left:1px solid #ddd; border-right:1px solid #ddd; padding:10px;}
			#gif-viewer {width:200px;height:200px;}
			
			.server-message {border:1px solid #d5d5d5; background:#fafafa;color:#888; padding:5px;}
		</style>
		<script>
		function preloadGif(gifs) {
		    $(gifs).each(function(){
		        $('<img/>')[0].src = this;
		    });
		}
		
		$(document).ready(function(){
			
		});
		</script>
	</head>
	<body>
		<div id="main-container">
			<g:form name="gifUploadForm" controller="Gif" action="add">
				<table>
					<tr>
						<td>Title</td><td><g:textField name="title"/></td>
					</tr>
					<tr>
						<td>Link</td><td><g:textField name="link"/></td>
					</tr>
					<tr>
						<td colspan=2 align="center">
							<g:submitButton name="submitGif" value="Submit"/>
						</td>
					</tr>
				</table>
			</g:form>
			
			<div id="gif-viewer">
			
			</div>
			
			<g:if test="${message }">
				<div class="server-message">
					${message }
				</div>
			</g:if>
		</div>
	</body>
</html>