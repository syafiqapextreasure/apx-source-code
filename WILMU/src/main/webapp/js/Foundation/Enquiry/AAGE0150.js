$(document).ready(function() {
	
	$('#Reterive').click(function(){
		var doc = new jsPDF('p', 'pt', 'a4'); 
		var specialElementHandlers = { 
		    '#editor': function (element, renderer) { 
		        return true; 
		    } 
		};
		var margin = {
	      top: 15,
	      bottom: 15,
	      left: 15,
	      width: 190,

	    };
		doc.fromHTML($('#hhh').html() , margin.top, margin.left, { 
		     'width': margin.width,
			 'bottom': margin.bottom,
		     'elementHandlers': specialElementHandlers 
		});    
		doc.save('ilmu 	Circulatoin Statistics.pdf'); 
	})
	
	$.get('getPatronDatabase', function (data) {
		if(data.TotalNumberPatron.length < 1){
			$('.displayNumberPatronDatabase').text("0")
		}else {
			$('.displayNumberPatronDatabase').text(data.TotalNumberPatron)
		}
		
		if(data.ActiveNumberPatron.length < 1){
			$('.displayNumberActivePatron').text("0")
		}else {
			$('.displayNumberActivePatron').text(data.ActiveNumberPatron)
		}
		
		if(data.ExpiredNumberPatron.length < 1){
			$('.displayNumberExpiredPatron').text("0")
		}else {
			$('.displayNumberExpiredPatron').text(data.ExpiredNumberPatron)
		}
	});

	$.get('getCatalogueDatabase', function (data) {
		if(data[0].TotalCatalogueRecordMain.length < 1){
			$('#displayTotalCatalogueDatabase').text("0")
		}else {
			$('#displayTotalCatalogueDatabase').text(data[0].TotalCatalogueRecordMain)
		}
		
		if(data[1].Total.length < 1){
			$('#displayTotalCatalogueRec').text("0")
		}else {
			$('#displayTotalCatalogueRec').text(data[1].Total)
		}
		
		if(data[1].TotalIndex.length < 1){
			$('#displayTotalIdxCatalogueRec').text("0")
		}else {
			$('#displayTotalIdxCatalogueRec').text(data[1].TotalIndex)
		}
		
		if(data[1].TotalUnindex.length < 1){
			$('#displayTotalUnindxCatalogueRec').text("0")
		}else {
			$('#displayTotalUnindxCatalogueRec').text(data[1].TotalUnindex)
		}
		
		if(data[2].Total.length < 1){
			$('#displayTotalAcquisitionsRec').text("0")
		}else {
			$('#displayTotalAcquisitionsRec').text(data[2].Total)
		}
		
		if(data[2].TotalIndex.length < 1){
			$('#displayTotalIdxAcquisitionsRec').text("0")
		}else {
			$('#displayTotalIdxAcquisitionsRec').text(data[2].TotalIndex)
		}
		
		if(data[2].TotalUnindex.length < 1){
			$('#displayTotalUnindxAcquisitionsRec').text("0")
		}else {
			$('#displayTotalUnindxAcquisitionsRec').text(data[2].TotalUnindex)
		}
		
		if(data[3].Total.length < 1){
			$('#displayTotalSerialsRec').text("0")
		}else {
			$('#displayTotalSerialsRec').text(data[3].Total)
		}
		
		if(data[3].TotalIndex.length < 1){
			$('#displayTotalIdxSerialsRec').text("0")
		}else {
			$('#displayTotalIdxSerialsRec').text(data[3].TotalIndex)
		}
		
		if(data[3].TotalUnindex.length < 1){
			$('#displayTotalUnindxSerialsRec').text("0")
		}else {
			$('#displayTotalUnindxSerialsRec').text(data[3].TotalUnindex)
		}
		
		if(data[4].TotalAccessionRecordMain.length < 1){
			$('.displayTotalAccessionNumber').text("0")
		}else {
			$('.displayTotalAccessionNumber').text(data[4].TotalAccessionRecordMain)
		}
	});

	$.get('getAccessionNumber', function (data) {
		$('#displayFirstUsedAccNum').text(data[0])
		$('#displayLastUsedAccNum').text(data[1])
	});

	$.get('getPatronStatus',{ userStatus: "1" }, function(data) {
		var k = ""
		$.each(data, function(idx, el){
			if(data[idx].Description.length < 1){
				k+='<li><div class="listree-submenu-heading">Undefined Status (Null) = '+data[idx].Count+'</div><ul class="listree-submenu-items"><li id="activePatronStatus'+data[idx].CodeDesc+'"></li></ul></li>'
			}else if(data[idx].Description.length > 1){
				k+='<li><div class="listree-submenu-heading">'+data[idx].Description+' ('+data[idx].CodeDesc+') = '+data[idx].Count+'</div><ul class="listree-submenu-items"><li id="activePatronStatus'+data[idx].CodeDesc+'"></li></ul></li>'
			}
		});
		$("#activeMemberPatronStatus").empty();
		$("#activeMemberPatronStatus").append(k);
	})
	
	$.get('getPatronStatus',{ userStatus: "0" }, function(data) {
		var k = ""
		$.each(data, function(idx, el){
			if(data[idx].Description.length < 1){
				k+='<li><div class="listree-submenu-heading">Undefined Status (Null) = '+data[idx].Count+'</div><ul class="listree-submenu-items"><li id="expiredPatronStatus'+data[idx].CodeDesc+'"></li></ul></li>'
			}else if(data[idx].Description.length > 1){
				k+='<li><div class="listree-submenu-heading">'+data[idx].Description+' ('+data[idx].CodeDesc+') = '+data[idx].Count+'</div><ul class="listree-submenu-items"><li id="expiredPatronStatus'+data[idx].CodeDesc+'"></li></ul></li>'
			}
		});
		$("#expiredMemberPatronStatus").empty();
		$("#expiredMemberPatronStatus").append(k);
	})

	$.get('getPatronBranch',{ userStatus: "1" }, function(data) {
		var k = ""
		$.each(data, function(idx, el){
			if(data[idx].Description.length < 1){
				k+='<li><div class="listree-submenu-heading">Undefined Status (Null) = '+data[idx].Count+'</div><ul class="listree-submenu-items"><li id="activePatronBranch'+data[idx].CodeDesc+'"></li></ul></li>'
			}else if(data[idx].Description.length > 1){
				k+='<li><div class="listree-submenu-heading">'+data[idx].Description+' ('+data[idx].CodeDesc+') = '+data[idx].Count+'</div><ul class="listree-submenu-items"><li id="activePatronBranch'+data[idx].CodeDesc+'"></li></ul></li>'
			}
		});
		$("#activeMemberPatronBranch").empty();
		$("#activeMemberPatronBranch").append(k);
	})

	$.get('getPatronBranch',{ userStatus: "0" }, function(data) {
			var k = ""
			$.each(data, function(idx, el){
				if(data[idx].Description.length < 1){
					k+='<li><div class="listree-submenu-heading">Undefined Status (Null) = '+data[idx].Count+'</div><ul class="listree-submenu-items"><li id="expiredPatronBranch'+data[idx].CodeDesc+'"></li></ul></li>';
				}else if(data[idx].Description.length > 1){
					k+='<li><div class="listree-submenu-heading">'+data[idx].Description+' ('+data[idx].CodeDesc+') = '+data[idx].Count+'</div><ul class="listree-submenu-items"><li id="expiredPatronBranch'+data[idx].CodeDesc+'"></li></ul></li>'
				}
			});
			$("#expiredMemberPatronBranch").empty();
			$("#expiredMemberPatronBranch").append(k);
	})
	
	$.get('getPatronDetails',{ userStatus: "1" }, function(data) {
			var k = ""
			$.each(data, function(idx, el){
				k='<div>'+data[idx].Description+' = '+data[idx].Count+'</div>'
				$("#activePatronStatus"+data[idx].CodeDesc+"").append(k);
			});
	})
	
	$.get('getPatronDetails',{ userStatus: "0" }, function(data) {
			var k = ""
			$.each(data, function(idx, el){
				k='<div>'+data[idx].Description+' = '+data[idx].Count+'</div>'
				$("#expiredPatronStatus"+data[idx].CodeDesc+"").append(k);
			});
	})
	
	$.get('getBranchDetails',{ userStatus: "1" }, function(data) {
			var k = ""
			$.each(data, function(idx, el){
				k='<div>'+data[idx].Description+' = '+data[idx].Count+'</div>'
				$("#activePatronBranch"+data[idx].CodeDesc+"").append(k);
			});
	})
	
	$.get('getBranchDetails',{ userStatus: "0" }, function(data) {
			var k = ""
			$.each(data, function(idx, el){
				k='<div>'+data[idx].Description+' = '+data[idx].Count+'</div>'
				$("#expiredPatronBranch"+data[idx].CodeDesc+"").append(k);
			});
	})
	
	$.get('getTotalbyItemCategoryAccession', function(data) {
			var k = ""
			
			$.each(data, function(idx, el){
				k='<li><div class="listree-submenu-heading">'+data[idx].Description+' ('+data[idx].CodeDesc+') = '+data[idx].Count+'</div><ul class="listree-submenu-items"><li id="itemCate'+data[idx].CodeDesc+'"></li></ul></li>';
				$("#totalbyItemCategoryAccession").append(k);
			});
	})
	
	var kk
	$.get('getTotalbyItemStatusAccession', function(data) {
			var k = ""
			kk = data
			$.each(data, function(idx, el){
				k='<div>'+data[idx].Description+' = '+data[idx].Count+'</div>'
				$("#totalbyItemStatusAccession").append(k);
			});
	})
	
	$.get('getTotalBySMD', function(data) {
			var k = "";
			$.each(data, function(idx, el){
				k='<li><div class="listree-submenu-heading">'+data[idx].Description+' ('+data[idx].CodeDesc+') = '+data[idx].Count+'</div><ul class="listree-submenu-items"><li id="smd'+data[idx].CodeDesc+'"></li></ul></li>';
				$("#totalBySMD").append(k);
			});
	})
	
	$.get('getTotalByItemLocation', function(data) {
			var k = "";
			$.each(data, function(idx, el){
				k='<li><div class="listree-submenu-heading">'+data[idx].Description+' = '+data[idx].Count+'</div><ul class="listree-submenu-items" id="totalByItemLocation'+idx+'"></ul></li>';
				$("#totalByItemLocation").append(k);
			});
	});
	
	$.get('getTotalByItemLocationDetails', function(data) {
			var k = "";
			var i = 0;
			$.each(data, function(idx, el){
				k='<li><div class="listree-submenu-heading">'+data[idx].Description+' ('+data[idx].CodeDesc+') = '+data[idx].Count+'</div><ul class="listree-submenu-items"><li id="location'+data[idx].Location+'"></li></ul></li>';
				$('#totalByItemLocation' + i).append(k);
				if(data[idx]?.CodeDesc != data[idx+1]?.CodeDesc){
					i++;
				}
			});
	});
	
	$.get('getTotalByItemLocationDetailsFinal', function(data) {
			var k = ""
			$.each(data, function(idx, el){
				if($('#location'+data[idx].Location+'').length){
					if(data[idx].Count.length < 1){
						k='<div>'+data[idx].CodeDesc+' = 0</div>'
					} else if(data[idx].Count.length > 0){
						k='<div>'+data[idx].CodeDesc+' = '+data[idx].Count+'</div>'
					}
					$('#location'+data[idx].Location+'').append(k);
				}
			});
	})
	
	$.get('getTotalBySMDDetailsFinal', function(data) {
			var k = ""
			$.each(data, function(idx, el){
				if($('#smd'+data[idx].Location+'').length){
					if(data[idx].Count.length < 1){
						k='<div>'+data[idx].CodeDesc+' = 0</div>'
					} else if(data[idx].Count.length > 0){
						k='<div>'+data[idx].CodeDesc+' = '+data[idx].Count+'</div>'
					}
					$('#smd'+data[idx].Location+'').append(k);
				}
			});
	})
	
	
	$.get('getTotalbyItemCategoryAccessionDetailsFinal', function(data) {
			var k = ""
			$.each(data, function(idx, el){
				if($('#itemCate'+data[idx].Location+'').length){
					if(data[idx].Count.length < 1){
						k='<div>'+data[idx].CodeDesc+' = 0</div>'
					} else if(data[idx].Count.length > 0){
						k='<div>'+data[idx].CodeDesc+' = '+data[idx].Count+'</div>'
					}
					$('#itemCate'+data[idx].Location+'').append(k);
				}
			});
			listree()
	})
});