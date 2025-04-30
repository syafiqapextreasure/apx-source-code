	var loginuser=""
		
			var tableBody = ""
		        
			document.addEventListener("DOMContentLoaded", () => {
			    const plusButton = document.querySelector(".plusSign");
			    const formSectionContainer = document.querySelector("#fieldset-design");
			     tableBody = document.getElementById('tanggunganTableBody');

			    // Function to check if the required fields are filled
			    function isFormValid(formSection) {
			        const idPengguna = formSection.querySelector('.idpengguna').value;
			        const noKPTanggungan = formSection.querySelector('.nokptanggungan').value;
			        const statusOKU = formSection.querySelector('.statusUser:checked') ? formSection.querySelector('.statusUser:checked').value : '';
			        
			        return idPengguna && noKPTanggungan && statusOKU;  // Ensure all required fields are filled
			    }

			    // Function to check if the row for a specific ID Pengguna already exists
			    function rowExists(idPengguna) {
			        const rows = tableBody.querySelectorAll('tr');
			        for (let row of rows) {
			            if (row.querySelector('.idPenggunaCell') && row.querySelector('.idPenggunaCell').textContent === idPengguna) {
			                return row;
			            }
			        }
			        return null; // No existing row
			    }

			    // Function to create Edit and Delete buttons
			   /*  function createActionButtons() {
			       const editButton = document.createElement('button');
			        editButton.textContent = 'Edit';
			        editButton.className = 'edit-btn btn btn-warning btn-sm';
			      
			     //   const deleteButton = document.createElement('button');
			        deleteButton.textContent = 'Delete';
			        deleteButton.className = 'delete-btn btn btn-danger btn-sm';

			        return { deleteButton };
			    } */

			    // Function to update the table based on form input
			    function updateTable() {
			        const formSections = document.querySelectorAll('.formSection');
			        
			        formSections.forEach((formSection) => {
			            if (!isFormValid(formSection)) return; // Only add if the form is valid

			            // Get form values
			            const idPengguna = formSection.querySelector('.idpengguna').value;
			            const hubungan = formSection.querySelector('.hubunganselect').value;
			            const statusOKU = formSection.querySelector('.statusUser:checked').value;
			            var harga = Math.floor(Math.random() * 100) + 1;  // Random value for Harga (RM)

			            // Check if the row already exists
			            const existingRow = rowExists(idPengguna);

			            if (existingRow) {
			                // If row exists, update the row content
			                existingRow.querySelector('.hubunganCell').textContent = hubungan;
			                existingRow.querySelector('.statusOKUCell').textContent = statusOKU === 'ya' ? 'Ya' : 'Tidak';
			                var desability=  $('input[name="radioPasanganStaff"]:checked').val=='ya'?true:false;
					     var isStaf=      $('input[name="Adakah_ahli_keluarga_anda_merupakan"]:checked').val()=='ya'?true:false;
			                 const noKPTanggungan = formSection.querySelector('.nokptanggungan').value;
			                harga= getMembershipdAmount(noKPTanggungan,desability,isStaf);//statusOKU === 'ya' ? '6' : '16';
			                existingRow.querySelector('.hargaCell').textContent = harga;
			            } else {
			                // If no existing row, create a new row
			                const newRow = document.createElement('tr');
			                
			                // Checkbox for each row
			                const checkBoxCell = document.createElement('td');
			                const checkBox = document.createElement('input');
			                checkBox.type = 'checkbox'; 
			                checkBox.className = 'form-check-input';
			                
			                checkBox.addEventListener('change', calculateTotalHarga);
			                checkBoxCell.appendChild(checkBox);

			                // ID Pengguna Cell
			                const idPenggunaCell = document.createElement('td');
			                idPenggunaCell.className = 'idPenggunaCell'; // Add class for reference
			                idPenggunaCell.textContent = idPengguna;

			                // Hubungan Cell
			                const hubunganCell = document.createElement('td');
			                hubunganCell.className = 'hubunganCell';
			                hubunganCell.textContent = hubungan;

			                // Status OKU Cell
			                const statusOKUCell = document.createElement('td');
			                statusOKUCell.className = 'statusOKUCell';
			                statusOKUCell.textContent = statusOKU === 'ya' ? 'Ya' : 'Tidak';

			                // Harga Cell
			                const hargaCell = document.createElement('td');
			                hargaCell.className = 'hargaCell';
			                var desability=  $('input[name="radioPasanganStaff"]:checked').val=='ya'?true:false;
						     var isStaf=      $('input[name="Adakah_ahli_keluarga_anda_merupakan"]:checked').val()=='ya'?true:false;
				               
			                harga=getMembershipdAmount(null,desability,isStaf);//statusOKU === 'ya' ? '6' : '16';
			              
			                hargaCell.textContent = harga;

			                // Action Buttons Cell (Edit and Delete)
			                const actionCell = document.createElement('td');
			               

			                // Append all cells to the row
			                newRow.appendChild(checkBoxCell);
			                newRow.appendChild(idPenggunaCell);
			                newRow.appendChild(hubunganCell);
			                newRow.appendChild(statusOKUCell);
			                newRow.appendChild(hargaCell);
			                newRow.appendChild(actionCell);

			                // Append the row to the table
			                tableBody.appendChild(newRow);
			            }
			        });
			        resetnotification();
			    }

			    // Listen for button click to add or update row
			    plusButton.addEventListener("click", updateTable);
			    
			    function calculateTotalHarga() {
			        let total = 0;
			        
			        // Loop through all checked checkboxes
			        document.querySelectorAll('.form-check-input:checked').forEach(function (checkbox) {
			            let row = checkbox.closest('tr'); // Find the parent row
			            if (row) {
			                let harga = parseFloat(row.querySelector('.hargaCell').textContent) || 0;
			                total += harga;
			            }
			            
			        });
			        
			        document.getElementById('mySpan-sum').textContent = `RM ${total}`;
			    }
			    
			    
			    document.getElementById('tanggunganTableBody').addEventListener('change', function (event) {
			        if (event.target.classList.contains('form-check-input')) {
			            let checkbox = event.target;
			            let row = checkbox.closest('tr');
			            console.log("Checkbox:", checkbox, "Row:", row); // Debug log
			            calculateTotalHarga();
			        }
			    });

			    $("input[name='radioPasanganStaff']").change(function () {
			        if ($("#ya4").is(":checked")) {
			        	var isStaff=$('input[name="Adakah_ahli_keluarga_anda_merupakan"]:checked').val()=='ya'?true:false;
			        	 var desability=  $('input[name="radioPasanganStaff"]:checked').val=='ya'?true:false;
					         
			        	$("input[name='radioPasanganStaff']").val=='ya'?getMembershipdAmount(null,desability,isStaff):getMembershipdAmount(null,desability,isStaff);	
			            $("#lampiranId2").prop("required", true);
			            $("#errorFile3").show();
			        } else {
			            $("#lampiranId2").prop("required", false);
			            $("#errorFile3").hide();
			        }
			    });

			    $("#lampiranId2").change(function () {
			        if ($(this).val()) {
			            $("#errorFile3").hide();
			        }
			    });
			   
			});
		 function generateSelectedList() {
		        const selectedList = [];
		        const rows = tableBody.querySelectorAll('tr');

		        rows.forEach(row => {
		            const checkBox = row.querySelector('input[type="checkbox"]');
		            if (checkBox && checkBox.checked) {
		                const idPengguna = row.querySelector('.idPenggunaCell').textContent;
		                const hubungan = row.querySelector('.hubunganCell').textContent;
		                const statusOKU = row.querySelector('.statusOKUCell').textContent;
		                const harga = row.querySelector('.hargaCell').textContent;
		                
		                selectedList.push({ idPengguna, hubungan, statusOKU, harga });
		            }
		        });

		        // Display the selected list
		     console.log("sELECTED LIST IS ",selectedList);
		     let selectedItemsJson = JSON.stringify(selectedList);
		     console.log("selectedItemsJson is ",selectedItemsJson);
		     				    // Assign the JSON string to the hidden input field
		      document.getElementById('selectedItems').value = selectedItemsJson;
		     				
		    } 
		   
		 
			window.addEventListener("load", () => {
				 var loginuser="sysadmin"
			   /*    const iframeElement = window.frameElement; // Reference to the iframe
			        const parentName =iframeElement.getAttribute("name");
			       loginuser=parentName.split(",")[0]; */
			        document.getElementById("portalId").value=loginuser; 
			    	if((loginuser =="") || (loginuser !="" && (loginuser.indexOf("default@kmlink.com.my")>-1 || loginuser.indexOf("20103")>-1))){
			    		 console.log("loginuser is ",loginuser);
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
			    	}
					
		/* 	});		 */
			
		
		var messageResponse=document.getElementById("messageResponse").value;
	         if (messageResponse !=undefined && messageResponse=="Success") {
	        	 Swal.fire({
			            title: 'Success!',
			            text: 'Membership Registration successful.',
			            icon: 'success',
			            confirmButtonText: 'OK'
			        }).then((result) => {
			            // Optional: If you want to redirect or perform an action after closing the alert, you can add it here
			            if (result.isConfirmed) {
			             
			             //   window.location.href = '/eforms/'; 
			            }
			        });
	        }
  
				// Add event listener for the change event
				var element = document.getElementById("errorportalId");
				var elementName = document.getElementById("errornameId")
				var noKP= document.getElementById("noKPerror");
				var myDateErrorVal=document.getElementById("myDateError");
				var branchError=document.getElementById("branchError");
				var warGanError=document.getElementById("warGanError");
				var noTelefonError=document.getElementById("noTelefonError");
				var idpengguna=document.getElementById("idErrorPengguna");
				 var errorAlamat= document.getElementById("AlamatError");
				var errorPoskod= document.getElementById("PoskodError");
				var errorneger=document.getElementById("negeriError");
				var errorFile= document.getElementById("errorFile1");
				var errorFile1= document.getElementById("errorFile2");
				var errorFile2= document.getElementById("errorFile3");
				 // Get the value of the phone input field
			    var phoneValue = $("#phone").val().trim();
/* 				warGan
 */

 $("#lampiranId").change(function() { 
		if ($("#lampiranId").val()) {
			errorFile.style.display = "none"; // Hide element
		} else {
			errorFile.style.display = "block";

		}
	});
	
 $("#branchLocation").change(function() { 
 if ($("#branchLocation").val().trim() != "") {
		branchError.style.display = "none"; // Hide element
	} else {
		branchError.style.display = "block";

	}
			});
			
	
 $("#lampiranId1").change(function() { 
		if ($("#lampiranId1").val()) {
			errorFile1.style.display = "none"; // Hide element
		} else {
			errorFile1.style.display = "block";

		}
	});
 
 $("#lampiranId2").change(function() { 
		if ($("#lampiranId2").val()) {
			errorFile2.style.display = "none"; // Hide element
		} else {
			errorFile2.style.display = "block";

		}
	});
 
 $("#portalId").change(function() { 
					if ($("#portalId").val().trim() != "") {
						element.style.display = "none"; // Hide element
					} else {
						element.style.display = "block";

					}
				});
				
				$("#nama").change(function() { 
					if ($("#nama").val().trim() != "") {
						elementName.style.display = "none"; // Hide element
					} else {
						elementName.style.display = "block";

					}
				});
				
				$("#noKP").change(function() { 
					if ($("#noKP").val().trim() != "") {
						noKP.style.display = "none"; // Hide element
					} else {
						noKP.style.display = "block";

					}
				});
				
				$("#myDate").change(function() { 
					if ($("#myDate").val().trim() != "") {
						myDateErrorVal.style.display = "none"; // Hide element
					} else {
						myDateErrorVal.style.display = "block";

					}
				});
				
				
				/* $("#myDate").change(function() { 
					if ($("#myDate").val().trim() != "") {
						errorneger.style.display = "none"; // Hide element
					} else {
						errorneger.style.display = "block";

					}
				});
				 */
				
				$("#myDate").change(function() { 
					if ($("#myDate").val().trim() != "") {
						myDateErrorVal.style.display = "none"; // Hide element
					} else {
						myDateErrorVal.style.display = "block";

					}
				});
				
				
				$("#negeriId").change(function() { 
					if ($("#negeriId").val().trim() != "") {
						branchError.style.display = "none"; // Hide element
					} else {
						branchError.style.display = "block";

					}
					
				});
			
				 $(".statusUser").change(function () {
		             console.log("required added "); 
					 if ($(this).val() === "ya") { 
		                    $("input[name='statusUser']:checked").val()=='ya'?  $("#fileUploadfirst").attr("required", "required"):"";
		               
		                
				        	 var desability=  $("input[name='statusUser']:checked").val()=='ya'?true:false;
						      
                            getMembershipdAmount(null,desability,false);
				       
		                } else { 
		                    $("#fileUploadfirst").removeAttr("required");
		                }
		            });
				/*  $("#ya2, #tidak2").change(function() { 
				        console.log("tidak2 is ");
				        if ($("input[name='statusUser']:checked").val()) {
				            // Hide the error message by setting display to 'none'
				            document.getElementById("statusUserError").style.display = "none";
				           console.log("New updated changes ",$("input[name='statusUser']:checked").val());
				            $("input[name='statusUser']:checked").val()=='ya'? $("fileUploadfirst").attr("required", "required"):"";
				        } else {
				            // Show the error message by setting display to 'block'
				            document.getElementById("statusUserError").style.display = "block";
				        }
				    });
				  */
				 $("#warGanYa, #warGanTidak").change(function() {
				        // Check if any radio button is checked
				        if ($("input[name='agecate']:checked").val()) {
				            // Hide the error message by setting display to 'none'
				            document.getElementById("warGanError").style.display = "none";
				        } else {
				            // Show the error message by setting display to 'block'
				            document.getElementById("warGanError").style.display = "block";
				        }
				    });
				 
				 $('input[name="Adakah_ahli_keluarga_anda_merupakan"]').change(function() {
				        // Check if any radio button is selected
				        if ($('input[name="Adakah_ahli_keluarga_anda_merupakan"]:checked').val()) {
				            // Hide the error message if a radio button is selected
				         // var desability=  $("input[name='radioPasanganStaff']").val=='ya'?true:false;
				          var isStaff=$('input[name="Adakah_ahli_keluarga_anda_merupakan"]:checked').val()=='ya'?true:false;
				        	 var desability=  $('input[name="radioPasanganStaff"]:checked').val=='ya'?true:false;
						    
				            
				            
				           $('input[name="Adakah_ahli_keluarga_anda_merupakan"]:checked').val()=='ya'? getMembershipdAmount(null,desability,isStaff):getMembershipdAmount(null,desability,false);
				            document.querySelector(".error-message").style.display = "none";
				        } else {
				            // Show the error message if no radio button is selected
				            document.querySelector(".error-message").style.display = "block";
				        }
				    });

				$("#phone").change(function() {
				   
				    
				    // Check if the phone number is not empty and matches the pattern
				    if (phoneValue !== "" && phoneValue.length <= 15 && /^\d{1,15}$/.test(phoneValue)) {
				    	noTelefonError.style.display = "none" // Hide the error message if valid
				    } else {
				    	noTelefonError.style.display = "block"; // Show the error message if invalid
				    }
				});
				
				
				$("#idpengguna").change(function() { 
					if ($("#idpengguna").val().trim() != "") {
						idpengguna.style.display = "none"; // Hide element
					} else {
						branchError.style.display = "block";

					}
				});
				
			
				
				
				$("#Alamat").change(function() { 
					if ($("#Alamat").val().trim() != "") {
						errorAlamat.style.display = "none"; // Hide element
					} else {
						errorAlamat.style.display = "block";

					}
				});
				
				$("#poskodphones").change(function() { 
					if ($("#poskodphones").val().trim() != "") {
						errorPoskod.style.display = "none"; // Hide element
					} else {
						errorPoskod.style.display = "block";

					}
				});
			    /* if ($("#poskodphones").val().trim() != "") {
					errorPoskod.style.display = "none"; // Hide element
				} else {
					errorPoskod.style.display = "block";

				} */
			    
				

			    $("#negeriId").change(function() { 
			    if ($("#negeriId").val().trim() != "") {
			    	errorneger.style.display = "none"; // Hide element
				} else {
					errorneger.style.display = "block";

				}
			    })

				$("#submit-form").click(function() { 
				
				setTimeout(() => {
				
					if((loginuser =="") || (loginuser !="" && (loginuser.indexOf("default@kmlink.com.my")>-1 || loginuser.indexOf("20103")>-1))){
			    		 console.log("loginuser is ",loginuser);
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
			    	}else{
					
			    		generateSelectedList();
					// Convert the JavaScript list into a JSON string
				       /*]]>*/
			 
					if ($("#nama").val().trim() != "") {
						elementName.style.display = "none"; // Hide element
					}else{
						elementName.style.display = "block";
						return;
					}
				
						if ($("#poskodphones").val().trim() != "") {
							errorPoskod.style.display = "none"; // Hide element
						} else {
							errorPoskod.style.display = "block";
							return;
						}
				
					if ($("#portalId").val().trim() != "") {
						element.style.display = "none"; // Hide element
					}else{
						element.style.display = "block";
						return;
					}
					
					if ($("#noKP").val().trim() != "") {
						noKP.style.display = "none"; // Hide element
					}else{
						noKP.style.display = "block";
						return;
					}
					
					if ($("#myDate").val().trim() != "") {
						myDateErrorVal.style.display = "none"; // Hide element
					}else{
						myDateErrorVal.style.display = "block";
						return;
					}
					
					if ($("#branchLocation").val().trim() != "") {
						branchError.style.display = "none"; // Hide element
					} else {
						branchError.style.display = "block";
						return;
					}
					
					if ($("#Alamat").val().trim() != "") {
						errorAlamat.style.display = "none"; // Hide element
					} else {
						errorAlamat.style.display = "block";
						return;
					}
					
					/* if ($("#lampiranId").val()) {
						errorFile.style.display = "none"; // Hide element
					} else {
						errorFile.style.display = "block";
						return;
					} */
					
				/* 	if ($("#lampiranId1").val()) {
						errorFile1.style.display = "none"; // Hide element
					} else {
						errorFile1.style.display = "block";
						return;
					} */
					
					if ($("#lampiranId2").val()) {
						errorFile2.style.display = "none"; // Hide element
					} else {
						errorFile2.style.display = "block";
						return;
					}
					
					if ($("#negeriId").val().trim() != "") {
						branchError.style.display = "none"; // Hide element
					} else {
						branchError.style.display = "block";
						return;
					}
					
				    if ($("input[name='Warganegara']:checked").val()) {
			            document.getElementById("statusUserError").style.display = "none";
					}else{
			            document.getElementById("statusUserError").style.display = "block";
			            return;
					}
				    
				 // Check if any radio button is checked
			        if ($("input[name='agecate']:checked").val()) {
			            // Hide the error message by setting display to 'none'
			            document.getElementById("warGanError").style.display = "none";
			        } else {
			            // Show the error message by setting display to 'block'
			            document.getElementById("warGanError").style.display = "block";
			            return;
			        }
				    
				    if ($("input[name='statusUser']:checked").val()) {
			            // Hide the error message by setting display to 'none'
			            document.getElementById("statusUserError").style.display = "none";
			        } else {
			            // Show the error message by setting display to 'block'
			            document.getElementById("statusUserError").style.display = "block";
			            return;
			        }
				    
				    if (phoneValue !== "" && phoneValue.length <= 15 && /^\d{1,15}$/.test(phoneValue)) {
				    	noTelefonError.style.display = "none" // Hide the error message if valid
				    } else {
				    	noTelefonError.style.display = "block"; 
				    	return;
				    	// Show the error message if invalid
				    }
		 	 
			    	}	
				}, 1200);
				})
				

			});
			

			
			
			
		
		    function showSuccessAlert() {
		        // Triggering SweetAlert2 success alert
		        Swal.fire({
		            title: 'Success!',
		            text: 'Membership Registration successful.',
		            icon: 'success',
		            confirmButtonText: 'OK'
		        }).then((result) => {
		            // Optional: If you want to redirect or perform an action after closing the alert, you can add it here
		            if (result.isConfirmed) {
		             
		             //   window.location.href = '/eforms/'; 
		            }
		        });
		    }
		    function resetnotification() {
		        // Triggering SweetAlert2 success alert
		        Swal.fire({
		            title: 'Success!',
		            text: 'Maklumat tanggungan anda telah ditambah.',
		            icon: 'success',
		            confirmButtonText: 'OK'
		        }).then((result) => {
		            // Optional: If you want to redirect or perform an action after closing the alert, you can add it here
		            if (result.isConfirmed) {
		             $("#Tanggungan_Id_Pengguna_Tanggungan").val("")
		            	 $("#nokptanggungan").val("")
		            			 $("#hubungan-id").prop("selectedIndex", 0).trigger("change");
		            				 $("input[name='statusUser']").prop("checked", false);
		             
		            				 getMembershipdAmount(null,false, false);
		            				 //   window.location.href = '/eforms/'; 
		            }
		        });
		    }
	
		    function getMembershipdAmount(noKPTanggungan,isDisabled, isStaff){ 
		    if(isStaff){
		    	return 0;
		    }else{
		    var ic =noKPTanggungan==null? $("#noKP").val().trim():noKPTanggungan;
            
            if (ic.length < 6 || isNaN(ic)) {
                alert("Invalid IC number. Please enter at least 6 digits.");
                return;
            }

            var year = parseInt(ic.substring(0, 2), 10);
            var month = parseInt(ic.substring(2, 4), 10);
            var day = parseInt(ic.substring(4, 6), 10);

            // Determine full year
            var currentYear = new Date().getFullYear();
            var fullYear = (year > 30) ? 1900 + year : 2000 + year; 

            var birthDate = new Date(fullYear, month - 1, day);
            var today = new Date();
            var age = today.getFullYear() - birthDate.getFullYear();

           /*  // Adjust if birthday hasn't occurred yet this year
            if (today < new Date(today.getFullYear(), birthDate.getMonth(), birthDate.getDate())) {
                age--;
            } */

       console.log(isDisabled,"Date of Birth: " + birthDate.toLocaleDateString() + " | Age: " + age);
         
         // Determine Fee Category
            var fee = 0;
            var category = "";

            if (age >= 60) {
                fee = 7.50;
                category = "Senior Citizen";
            } else if (age >= 21) {
                fee = isDisabled ? 7.50 : 16;
                category = isDisabled ? "Adult with Disability" : "Adult";
            } else if (age >= 13) {
                fee = isDisabled ? 5 : 11;
                category = isDisabled ? "Teenager with Disability" : "Teenager";
            } else if (age >= 5) {
                fee = isDisabled ? 2.50 : 6;
                category = isDisabled ? "Child with Disability" : "Child";
            } else {
                fee = 0; // Assuming under 5 is free
                category = "Below 5 (Free)";
            }
return fee;
		    }   
            
		    }   
		  