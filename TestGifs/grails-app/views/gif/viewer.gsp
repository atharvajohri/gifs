<!doctype html>
<html>
	<head>
		<title>Testing Gifs</title>
		<g:javascript library="jquery" plugin="jquery"></g:javascript>
		<style>
			body {font-size:13px;color:#5d5d5d;margin:0;padding:0;font-family:"arial"}
			#main-container {width:800px;margin:auto;border-left:1px solid #ddd; border-right:1px solid #ddd; padding:10px;}
			#gif-viewer {width:750px;height:200px;}
			#gif-viewer .gifs {float:left;border:1px solid #dedede;margin-left:10px}
			.server-message {border:1px solid #d5d5d5; background:#fafafa;color:#888; padding:5px;}
		</style>
		<script>
		function generateEncodedImageSrc(encoded){
			return "data:image/gif;base64,"+encoded;
		}
		
		function loadGif(gifData){
			$("#gif-direct").attr("src", gifData.link)
			$("#gif-indirect").attr("src", generateEncodedImageSrc(gifData.link))
		}
		
		function getNextGif(id){
			$.ajax({
				url : "/gif/getGif",
				data: {id:id},
				type: "GET",
				success: function(gifData){
					if (gifData.id){
						//bind it
						$("#mc-title-field").val(gifData.title);
						loadGif(gifData);
					}else{
						console.log("Can't understand response");
					}
				},
				error: function(data){
					console.log(data);
				}
			});
		}
		
		$(document).ready(function(){
			var gifData = getNextGif();			
		});
		</script>
	</head>
	<body>
		<div id="main-container">
			<table>
				<tr>
					<td>Title</td><td><g:textField name="title" id="mc-title-field" readonly="readonly" /></td>
				</tr>
			</table>
			<div id="gif-viewer">
				<img id="gif-direct" class="gifs" />
				<img id="gif-indirect" class="gifs" />
			</div>
		</div>
	</body>
</html>