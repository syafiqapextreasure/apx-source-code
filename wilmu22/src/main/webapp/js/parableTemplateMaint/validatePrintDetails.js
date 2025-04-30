
$(document).ready(
		function() {
			

			var value="1000122800";
			var btype="code128";
			 var renderer = "svg";

			        var settings = {
			            output: renderer,
			            bgColor: $("#bgColor").val(),
			            color: $("#color").val(),
			            barWidth: 2,
			            barHeight: $("#barHeight").val(),
			            moduleSize: $("#moduleSize").val(),
			            fontSize: 18,
			            posX: $("#posX").val(),
			            posY: $("#posY").val(),
			            addQuietZone: $("#quietZoneSize").val()
			        };

			        $(".Barcode_v").html("").show().barcode(value, btype, settings);
			        
			        
			/*	      $(".Barcode_v").text('aaaaaaaaaaaa');*/
			        
			        
//			        $("#canvasTarget").html("").show().barcode(value, btype, settings);
			        
			  /*      $("#testing").text('sdfsdfsa');*/
			    
			    /*    $("#canvasTarget").val('cvxccc');*/
			        
			
			
			$('#printMeBtn').click(function(){
				
				var w = window.print();
			
			});
			
//			var html=$('.book-label');
			
		

	});