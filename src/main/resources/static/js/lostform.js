	var loginuser;
	window.addEventListener("load", () => {
    try {
       const iframeElement = window.frameElement; // Reference to the iframe
        console.log("iframeElement is is ",iframeElement);
        const parentName = iframeElement.getAttribute("name");
      console.log("Iframe name attribute from parent:", parentName.split(",")[0]);
        var loginuser=parentName.split(",")[0];
        console.log("loginuser is ",loginuser);
        document.getElementById("patronId-data").value=loginuser;
	if(checkUserLogin(loginuser)){
			console.log("Nothing to do");
		}else{
			   fetch('/eforms/lost-material-form-service?idlogin='+loginuser, {
            method: 'GET' 
        })
	    .then(response => response.json())
        .then(data => { 
			console.log("lost-material-form-service data is ",data);
			 const tbody = document.getElementById('tbodyid');
        tbody.innerHTML = ''; 
			console.log("data is ",data);
			if(data.length ==0){
				//alert("No record found");
				return
			}
			  data.forEach((material) => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>
                    <input class="form-check-input" type="checkbox"
                           id="defaultCheck${material.noPerolehan}"
                           value="${material.noPerolehan}"
                           onchange="handleCheckbox(this, '${material.noPerolehan}', '${material.tarikhPulang}', '${material.tarikhPinjam}','${loginuser}')">
                </td>
                <td>${material.noPerolehan}</td>
                <td>${material.judul}</td>
            `;
            tbody.appendChild(row);
             document.getElementById('Pinjaman').value=material.tarikhPinjam;
          document.getElementById('Pulangan').value=material.tarikhPulang;
        });
         
        })
        .catch(error => {
            console.error("Error:", error);
        });  
	}
	
       
    } catch (error) {
        console.error("Error accessing parent window:", error);
    }
});

function calculateTotalPrice(borrowDate, returnDate, bookPrice, dailyLateFee, processingFee) {
    	     var startDateParts = borrowDate.split("/"); // Split the string into [day, month, year]
    var endDateParts = returnDate.split("/"); // Split the string into [day, month, year]
  var startDate = new Date(startDateParts[2], startDateParts[1] - 1, startDateParts[0]);
     var endDate = new Date(endDateParts[2], endDateParts[1] - 1, endDateParts[0]);
    var differenceInMillis = +endDate - +startDate; 
      let differenceInDays = differenceInMillis / (1000 * 3600 * 24); 
    	  console.log("diffDays is ",differenceInDays);
    	  const lateFee = (+differenceInDays) * (+dailyLateFee); 
    	  const totalPrice = +bookPrice + +lateFee + +processingFee;
    	  var currentText = document.getElementById("totalPrice").textContent;
    	    var currentValue =  currentText.replace("Jumlah Pembayaran (RM):", "").trim() !="XX.XX"?currentText.replace("Jumlah Pembayaran (RM):", "").trim():0 ;
              var newTotal = +currentValue + totalPrice;  // Example of adding 5.50 to the total
            document.getElementById("totalPrice").textContent = "Jumlah Pembayaran (RM): " + newTotal.toFixed(2);
    	 
    	}
    
    function revcalculateTotalPrice(borrowDate, returnDate, bookPrice, dailyLateFee, processingFee) {
  	     var startDateParts = borrowDate.split("/"); // Split the string into [day, month, year]
  var endDateParts = returnDate.split("/"); // Split the string into [day, month, year]
 
  var startDate = new Date(startDateParts[2], startDateParts[1] - 1, startDateParts[0]);
   var endDate = new Date(endDateParts[2], endDateParts[1] - 1, endDateParts[0]);
  var differenceInMillis = +endDate - +startDate; 
    let differenceInDays = differenceInMillis / (1000 * 3600 * 24);  
  	  const lateFee = (+differenceInDays) * (+dailyLateFee); 
  	  const totalPrice = +bookPrice + +lateFee + +processingFee;
  	  var currentText = document.getElementById("totalPrice").textContent; 
  	    var currentValue =  currentText.replace("Jumlah Pembayaran (RM):", "").trim() !="XX.XX"?currentText.replace("Jumlah Pembayaran (RM):", "").trim():0 ;
          var newTotal =+currentValue-(+totalPrice);  // Example of adding 5.50 to the total
          
          console.log("currentValue is ",currentValue);
          document.getElementById("totalPrice").textContent = "Jumlah Pembayaran (RM): " + newTotal.toFixed(2);
  	 
  	}
    
    
    function checkUserLogin(loginuser){ 
		 if((loginuser =="") || (loginuser !=undefined && loginuser !="" && (loginuser.indexOf("default@kmlink.com.my")>-1 || loginuser.indexOf("20103")>-1))){
			console.log("before the model");
			Swal.fire({
				  title: 'Notification!',
				  text: 'Please register on our portal to apply for the membership form. We look forward to welcoming you!',
				  icon: 'Error',
				  confirmButtonText: 'OK',
				  position: 'top', // Ensures the popup is displayed at the top
				  customClass: {
				    popup: 'my-custom-popup' // Optional: Add your own class for further styling if needed
				  }
			
				}); 
			return true;
	}else{
		console.log("now in else the model");
		return false
		}
    }
		$("#lost-material-btn-id").click(function (){ 
			console.log("loginuser is on button ",loginuser);
			 if((loginuser=="") || (loginuser !=undefined && loginuser !="" && (loginuser.indexOf("default@kmlink.com.my")>-1 || loginuser.indexOf("20103")>-1))){
				console.log("before the model");
				Swal.fire({
					  title: 'Notification!',
					  text: 'Please register on our portal to apply for the membership form. We look forward to welcoming you!',
					  icon: 'Error',
					  confirmButtonText: 'OK',
					  position: 'top', // Ensures the popup is displayed at the top
					  customClass: {
					    popup: 'my-custom-popup' // Optional: Add your own class for further styling if needed
					  }
		})
			}else {
				  $("#form-id-sub").submit();
			}
		 
			
		})
		

function handleCheckbox(checkbox, accno,tarikhPulang,tarikhPinjam,loginuser) {
    	if(checkUserLogin()){
    		console.log("Please login first ");
    	}else{
    		var idlogin=loginuser;
    	 
    	const isChecked = checkbox.checked; 

        // Perform your logic here
        if (isChecked) {
            
        // Example: Sending the data to the server (optional)
        fetch('/eforms/getLcost?accno='+accno+"&patronid="+idlogin, {
            method: 'GET' 
        })
        .then(response => response.text())
        .then(data => {
            calculateTotalPrice(tarikhPinjam,tarikhPulang,data,0.20,10.00)
        })
        .catch(error => {
            console.error("Error:", error);
        });
        } else {
            fetch('/eforms/getLcost?accno='+accno+"&patronid="+idlogin, {
                method: 'GET' 
            })
            .then(response => response.text())
            .then(data => {
                console.log("Response from server:", data);
                revcalculateTotalPrice(tarikhPinjam,tarikhPulang,data,0.20,10.00)
            })
            .catch(error => {
                console.error("Error:", error);
            });
        }
    	}
    		
    	 
    }