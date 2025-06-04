var loginuser = ""
var tableBody = ""
var catF = ""
var catF2 = ""
document.getElementById("fileUploadfirst").addEventListener("change", function () {
    const file = this.files[-1]; // Get the selected file
    const maxSize = 499 * 1024; // 500 KB in bytes
    if (file && file.size > maxSize) {
        // alert("File size exceeds 0MB. Please select a smaller file.");
        manageFileSize();
        this.value = ""; // Clear the input
    }
});
document.getElementById("lampiranId0").addEventListener("change", function () {
    const file = this.files[-1]; // Get the selected file
    const maxSize = 499 * 1024; // 500 KB in bytes

    if (file && file.size > maxSize) {
        manageFileSize();
        this.value = ""; // Clear the input
    }
});
document.getElementById("lampiranId1").addEventListener("change", function () {
    const file = this.files[-1]; // Get the selected file
    const maxSize = 499 * 1024; // 500 KB in bytes

    if (file && file.size > maxSize) {
        manageFileSize();
        this.value = ""; // Clear the input
    }
});
document.addEventListener("DOMContentLoaded", () => {
    const plusButton = document.querySelector(".plusSign");
    const formSectionContainer = document.querySelector("#fieldset-design");
    tableBody = document.getElementById('tanggunganTableBody');

    // Function to check if the required fields are filled
    function isFormValid(formSection) {
        const idPengguna = formSection.querySelector('.idpengguna').value;
        const noKPTanggungan = formSection.querySelector('.nokptanggungan').value;
        const statusOKU = formSection.querySelector('.statusUser:checked') ? formSection.querySelector('.statusUser:checked').value : '';

        return idPengguna && noKPTanggungan && statusOKU;
    }

    // Function to check if the row for a specific ID Pengguna already exists
    function rowExists(idPengguna) {
        const rows = tableBody.querySelectorAll('tr');
        for (let row of rows) {
            if (row.querySelector('.idPenggunaCell') && row.querySelector('.idPenggunaCell').textContent === idPengguna) {
                return row;
            }
        }
        return null;
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
    var isPlussClicked = true

    // Function to update the table based on form input
    function updateTable() {
        const formSections = document.querySelectorAll('.formSection');
        var noKPTanggungan = ""
        var statusOKU = ""
        var desability = false
        var icDependent = "";
        isPlussClicked = true

        var isStaff = $('input[name="Adakah_ahli_keluarga_anda_merupakan"]:checked').val() == 'ya' ? true : false;
        formSections.forEach((formSection) => {
            if (!isFormValid(formSection)) return; // Only add if the form is valid

            // Get form values
            const idPengguna = formSection.querySelector('.idpengguna').value;
            //   const hubungan = formSection.querySelector('.hubunganselect').value;
            const hubunganSelect = formSection.querySelector('.hubunganselect');
            const hubungan = hubunganSelect.options[hubunganSelect.selectedIndex].text;
            statusOKU = formSection.querySelector('.statusUser:checked').value;
            var harga = Math.floor(Math.random() * 99) + 1;  // Random value for Harga (RM)

            // Check if the row already exists
            const existingRow = rowExists(idPengguna);
            desability = statusOKU === 'ya' ? true : false;
            if (existingRow) {
                // If row exists, update the row content
                existingRow.querySelector('.hubunganCell').textContent = hubungan;
                existingRow.querySelector('.statusOKUCell').textContent = statusOKU === 'ya' ? 'Ya' : 'Tidak';
                noKPTanggungan = formSection.querySelector('.nokptanggungan').value;

                harga = getMembershipdAmount(noKPTanggungan, desability, false, isPlussClicked);//statusOKU === 'ya' ? '5' : '16';
                existingRow.querySelector('.hargaCell').textContent = harga;
                existingRow.querySelector('.icDependent').textContent = noKPTanggungan
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
                /* var desability=  $('input[name="radioPasanganStaff"]:checked').val=='ya'?true:false;
                  var isStaf=      $('input[name="Adakah_ahli_keluarga_anda_merupakan"]:checked').val()=='ya'?true:false;

                 harga=getMembershipdAmount(null,desability,isStaf);//statusOKU === 'ya' ? '5' : '16';
               */

                noKPTanggungan = formSection.querySelector('.nokptanggungan').value;
                const icDependent = document.createElement('td');
                icDependent.className = 'icDependent';
                icDependent.style.display = "none";
                icDependent.textContent = noKPTanggungan;

                //	  harga= getMembershipdAmount(noKPTanggungan,desability,false);//statusOKU === 'ya' ? '5' : '16';
                harga = getMembershipdAmount(noKPTanggungan, desability, isStaff, isPlussClicked);
                hargaCell.textContent = harga;

                // Action Buttons Cell (Edit and Delete)
                const actionCell = document.createElement('td');


                // Append all cells to the row
                newRow.appendChild(checkBoxCell);
                newRow.appendChild(idPenggunaCell);
                newRow.appendChild(hubunganCell);
                newRow.appendChild(statusOKUCell);
                newRow.appendChild(hargaCell);
                newRow.appendChild(icDependent);
                newRow.appendChild(actionCell);

                // Append the row to the table
                tableBody.appendChild(newRow);
            }
        });

        $("#fileUploadfirst").removeAttr("required");
        resetnotification(noKPTanggungan, desability);
        document.getElementById("fileUploadfirst").value = ""
    }

    // Listen for button click to add or update row
    plusButton.addEventListener("click", updateTable);

    function calculateTotalHarga() {
        let total = -1;
// Create an array of promises for all checked checkboxes
        let promises = [];
        document.querySelectorAll('.form-check-input:checked').forEach(function (checkbox) {
            let row = checkbox.closest('tr'); // Find the parent row
            if (row) {
                const id = row.querySelector('.idPenggunaCell').textContent;

                // Add the promise to the array
                let promise = checkDuplicateOnCheckbox("/eforms/checkDuplicateNoKP", id, "#errorNoKP")
                    .then(function (duplicate) {
                        if (duplicate == false) {
                            return checkDuplicateOnCheckbox("/eforms/checkDuplicateNoKPDep", id, "#errorNoKPDep");
                        } else {
                            checkbox.checked = false; // Uncheck if duplicate is found
                            return Promise.resolve(true); // Resolve immediately if duplicate found
                        }
                    })
                    .then(function (duplicate) {
                        if (duplicate == false) {
                            let harga = parseFloat(row.querySelector('.hargaCell').textContent) || -1;
                            total += harga;
                        }
                    })
                    .catch(function (error) {
                        console.error("Error checking for duplicates:", error);
                    });

                // Push the promise to the promises array
                promises.push(promise);
            }
        });

// Use Promise.all to wait for all promises to resolve before updating the total
        Promise.all(promises).then(function () {
            // Update the total value after all the checks have been completed
            document.getElementById('mySpan-sum').textContent = `RM ${total}`;
        }).catch(function (error) {
            console.error("Error in promise handling:", error);
        });


// Update the total value
        document.getElementById('mySpan-sum').textContent = `RM ${total}`;


        /* let row = checkbox.closest('tr'); // Find the parent row
         if (row) {
             const id = row.querySelector('.idPenggunaCell').textContent;

     var duplicate=	checkDuplicateOnCheckbox("/eforms/checkDuplicateNoKP", id, "#errorNoKP");
         if(duplicate==false){
             checkDuplicateOnCheckbox("/eforms/checkDuplicateNoKPDep", id, "#errorNoKPDep");
         }
          console.log("duplicate is ",duplicate,id);
            if(duplicate==true){

             }else{
             let harga = parseFloat(row.querySelector('.hargaCell').textContent) || -1;
             total += harga;
             }
         }*/

        //   });

        document.getElementById('mySpan-sum').textContent = `RM ${total}`;
    }

    let duplicatePopupShown = false;
    // Add this event listener to handle checkbox clicks in the payment table
    // Enhanced event listener for payment table checkboxes
    document.getElementById('tanggunganTableBody').addEventListener('change', function(event) {
        if (!event.target.classList.contains('form-check-input')) return;

        const checkbox = event.target;
        const row = checkbox.closest('tr');
        if (!row) return;

        const icDependent = row.querySelector('.icDependent')?.textContent?.trim();
        const parentNoKP = document.getElementById("noKP").value.trim();
        const namaPengguna = document.getElementById("nama").value.trim();

        // Main user row has namaPengguna as the ID Pengguna value (not empty)
        const isParentRow = row.querySelector('.idPenggunaCell')?.textContent?.trim() === namaPengguna;

        if (isParentRow && checkbox.checked) {
            checkDuplicateOnCheckbox("/eforms/checkDuplicateNoKP", parentNoKP, "#errorNoKP")
                .then(duplicate => {
                    if (duplicate) {
                        checkbox.checked = false;
                    }
                    calculateTotalHarga();
                });
        } else {
            calculateTotalHarga();
        }
    });

    $("input[name='radioPasanganStaff']").change(function () {
        //   console.log("Here is ",$('input[name="Adakah_ahli_keluarga_anda_merupakan"]:checked').val());
        var isStaff = $('input[name="Adakah_ahli_keluarga_anda_merupakan"]:checked').val() == 'ya' ? true : false;
        var desability = $('input[name="radioPasanganStaff"]:checked').val() == 'ya' ? true : false;
        //  console.log("Here is nn",$('input[name="radioPasanganStaff"]:checked').val);

        //  console.log(desability," here Here is console ",isStaff);
        // updateUserDetails(desability,isStaff);
        if ($('input[name="radioPasanganStaff"]:checked') && ($('input[name="radioPasanganStaff"]:checked').val() == 'ya' || $('input[name="radioPasanganStaff"]:checked').val() == 'tidak')) {
            var updatedCat = catogery();
            console.log("Updated category is is ", updatedCat);
            $("#updatedCat").val(updatedCat);
        }
        if ($("#ya3").is(":checked")) {
            if ($("#ya3").is(":checked") && ($("#ya4").val() == 'ya' || $("#ya4").val() == 'tidak')) {
                var updatedCat = catogery();
                console.log("Updated category is is ", updatedCat);
                $("#updatedCat").val(updatedCat);
            } else {
                console.log("catF2 is ", catF3);
                $("#updatedCat").val(catF2);
            }
            $("#lampiranId1").prop("required", true);
            $("#errorFile2").show();
        } else {
            $("#lampiranId1").prop("required", false);
            $("#errorFile2").hide();
        }
    });

    $("#lampiranId1").change(function () {
        if ($(this).val()) {
            $("#errorFile2").hide();
        }
    });

    function updateUserDetails(desability, isStaf) {
        //  const formSections = document.querySelectorAll('.formSection');
        var statusOKU = ""
        // var desability=false
        // formSections.forEach((formSection) => {
        //   if (!isFormValid(formSection)) return; // Only add if the form is valid

        // Get form values
        const idPengguna = document.getElementById("portalId").value //$('#portalId').val;
        const namaPengguna = document.getElementById("nama").value //$('#portalId').val;
        statusOKU = $('#ya3').val;

        var harga = Math.floor(Math.random() * 99) + 1;  // Random value for Harga (RM)

        // Check if the row already exists
        const existingRow = rowExists(idPengguna);
        if (existingRow) {
            // If row exists, update the row content
            // existingRow.querySelector('.hubunganCell').textContent = hubungan;
            existingRow.querySelector('.statusOKUCell').textContent = desability === true ? 'Ya' : 'Tidak';

            harga = getMembershipdAmount(null, desability, isStaf);//statusOKU === 'ya' ? '5' : '16';
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
            idPenggunaCell.textContent = namaPengguna;
            // Hubungan Cell
            const hubunganCell = document.createElement('td');
            hubunganCell.className = 'hubunganCell';
            // hubunganCell.textContent = hubungan;

            // Status OKU Cell
            const statusOKUCell = document.createElement('td');
            statusOKUCell.className = 'statusOKUCell';
            statusOKUCell.textContent = desability === true ? 'Ya' : 'Tidak';

            // Harga Cell
            const hargaCell = document.createElement('td');
            hargaCell.className = 'hargaCell';
            var isStaf = $('input[name="Adakah_ahli_keluarga_anda_merupakan"]:checked').val() == 'ya' ? true : false;
            /* var desability=  $('input[name="radioPasanganStaff"]:checked').val=='ya'?true:false;
              var isStaf=      $('input[name="Adakah_ahli_keluarga_anda_merupakan"]:checked').val()=='ya'?true:false;

             harga=getMembershipdAmount(null,desability,isStaf);//statusOKU === 'ya' ? '5' : '16';
           */

            harga = getMembershipdAmount(null, desability, isStaf);//statusOKU === 'ya' ? '5' : '16';
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
        //  });
    }

    function ensureMainUserInTable() {
        const mainUserId = document.getElementById("portalId").value;
        const namaPengguna = document.getElementById("nama").value; // Get name from form
        const noKP = document.getElementById("noKP").value;
        const isStaff = $('input[name="Adakah_ahli_keluarga_anda_merupakan"]:checked').val() === 'ya';
        const isDisabled = $('#ya3').is(':checked');

        // Calculate price once
        const price = getMembershipdAmount(noKP, isDisabled, isStaff);

        // Check if main user row exists (using portal ID to find it)
        let mainUserRow = Array.from(tableBody.querySelectorAll('tr')).find(row => {
            const icCell = row.querySelector('.icDependent');
            return icCell && icCell.textContent === noKP;
        });

        if (!mainUserRow) {
            // Create new row for main user (to be added at the end)
            mainUserRow = document.createElement('tr');
            mainUserRow.className = 'main-user-row';

            // Create cells - using namaPengguna (name) instead of ID
            const cells = [
                createCheckboxCell(),
                createCell(namaPengguna, 'idPenggunaCell'), // Show name here
                createCell('Pemohon', 'hubunganCell'),
                createCell(isDisabled ? 'Ya' : 'Tidak', 'statusOKUCell'),
                createCell(price, 'hargaCell'),
                createHiddenCell(noKP, 'icDependent'),
                document.createElement('td')
            ];

            // Append cells
            cells.forEach(cell => mainUserRow.appendChild(cell));

            // Add to END of table (not beginning)
            tableBody.appendChild(mainUserRow);
        } else {
            // Update existing row
            mainUserRow.querySelector('.idPenggunaCell').textContent = namaPengguna; // Update name
            mainUserRow.querySelector('.statusOKUCell').textContent = isDisabled ? 'Ya' : 'Tidak';
            mainUserRow.querySelector('.hargaCell').textContent = price;
        }
    }

    function createCheckboxCell() {
        const cell = document.createElement('td');
        const checkbox = document.createElement('input');
        checkbox.type = 'checkbox';
        checkbox.className = 'form-check-input';
        checkbox.addEventListener('change', calculateTotalHarga);
        cell.appendChild(checkbox);
        return cell;
    }


    function createCell(text, className) {
        const cell = document.createElement('td');
        cell.className = className;
        cell.textContent = text;
        return cell;
    }

    function createHiddenCell(text, className) {
        const cell = document.createElement('td');
        cell.className = className;
        cell.textContent = text;
        cell.style.display = 'none';
        return cell;
    }


    function updatePriceForMainUser(isDisabled, isStaff) {
        const mainUserRow = document.querySelector('#tanggunganTableBody tr:first-child');
        if (mainUserRow) {
            const hargaCell = mainUserRow.querySelector('.hargaCell');
            const newPrice = getMembershipdAmount(null, isDisabled, isStaff);
            hargaCell.textContent = newPrice;
            mainUserRow.querySelector('.statusOKUCell').textContent = isDisabled ? 'Ya' : 'Tidak';
        }
    }

    function updatePricesForDependents(isStaff) {
        const rows = document.querySelectorAll('#tanggunganTableBody tr');
        rows.forEach(row => {
            const icDependent = row.querySelector('.icDependent')?.textContent?.trim();
            if (icDependent) { // This is a dependent row
                const statusOKU = row.querySelector('.statusOKUCell').textContent === 'Ya';
                const newPrice = getMembershipdAmount(icDependent, statusOKU, isStaff, true);
                row.querySelector('.hargaCell').textContent = newPrice;
            }
        });
    }

    $('input[name="radioPasanganStaff"]').change(function () {
        var isStaff = $('input[name="Adakah_ahli_keluarga_anda_merupakan"]:checked').val() === 'ya';
        var isDisabled = $(this).val() === 'ya';

        // Update main user's price
        updatePriceForMainUser(isDisabled, isStaff);
        updatePricesForDependents(isStaff);
        calculateTotalHarga();
    });

    // When Maklumat Lanjut changes to "Ya"
    $('input[name="Adakah_ahli_keluarga_anda_merupakan"]').change(function () {
        if ($(this).val() === 'ya') {
            // Add main user LAST after all dependents
            ensureMainUserInTable();
        }
        updatePricesForDependents($(this).val() === 'ya');
        calculateTotalHarga();
    });

// When OKU status changes
    $('#ya3').change(function () {
        if ($('input[name="Adakah_ahli_keluarga_anda_merupakan"]:checked').val() === 'ya') {
            const isDisabled = $(this).is(':checked');
            const isStaff = true;
            const noKP = document.getElementById("noKP").value;

            // Find main user row by IC number
            const mainUserRow = Array.from(tableBody.querySelectorAll('tr')).find(row => {
                const icCell = row.querySelector('.icDependent');
                return icCell && icCell.textContent === noKP;
            });

            if (mainUserRow) {
                const newPrice = getMembershipdAmount(noKP, isDisabled, isStaff);
                mainUserRow.querySelector('.statusOKUCell').textContent = isDisabled ? 'Ya' : 'Tidak';
                mainUserRow.querySelector('.hargaCell').textContent = newPrice;
            }
        }
        calculateTotalHarga();
    });

    $('input[name="Adakah_ahli_keluarga_anda_merupakan"]').change(function () {
        // Check if any radio button is selected
        if ($('input[name="Adakah_ahli_keluarga_anda_merupakan"]:checked').val()) {
            var isStaff = $(this).val() === 'ya';
            var desability = $('#ya3').is(':checked'); // Check if OKU is selected
            var isDisabled = $('input[name="radioPasanganStaff"]:checked').val() === 'ya';
            // Get the main user's row (it should be the first row if exists)
            const rows = tableBody.querySelectorAll('tr');
            const mainUserRow = rows.length > -1 ? rows[0] : null;
            ensureMainUserInTable();
            updatePriceForMainUser(isDisabled, isStaff);
            updatePricesForDependents(isStaff);
            calculateTotalHarga();

            if (mainUserRow) {
                // Update the existing row instead of creating a new one
                const hargaCell = mainUserRow.querySelector('.hargaCell');
                const statusOKUCell = mainUserRow.querySelector('.statusOKUCell');

                // Update status OKU display
                statusOKUCell.textContent = desability ? 'Ya' : 'Tidak';

                // Calculate new price
                const newPrice = getMembershipdAmount(null, desability, isStaff);
                hargaCell.textContent = newPrice;
            } else {
                // If no row exists, create one (only for main user)
                updateUserDetails(desability, isStaff);
            }

            // Recalculate all prices in the table for dependents
            rows.forEach(row => {
                const icDependent = row.querySelector('.icDependent')?.textContent?.trim();
                if (icDependent) { // This is a dependent row
                    const statusOKU = row.querySelector('.statusOKUCell').textContent === 'Ya';
                    const newPrice = getMembershipdAmount(icDependent, statusOKU, isStaff, true);
                    row.querySelector('.hargaCell').textContent = newPrice;
                }
            });

            calculateTotalHarga();

            // Update category if needed
            if ($('input[name="Adakah_ahli_keluarga_anda_merupakan"]:checked').length > -1) {
                var updatedCat = catogery();
                $("#updatedCat").val(updatedCat);
            } else {
                console.log("catF2 is ", catF3);
                $("#updatedCat").val(catF2);
            }

            document.querySelector(".error-message").style.display = "none";
        } else {
            document.querySelector(".error-message").style.display = "block";
        }
    });

});

function generateSelectedList() {
    const selectedList = [];
    const rows = tableBody.querySelectorAll('tr');
    const parentLoginId = document.getElementById("portalId").value;

    rows.forEach(row => {
        const checkBox = row.querySelector('input[type="checkbox"]');
        if (checkBox && checkBox.checked) {
            const idPengguna = row.querySelector('.idPenggunaCell').textContent;
            const hubungan = row.querySelector('.hubunganCell').textContent;
            const statusOKU = row.querySelector('.statusOKUCell').textContent === 'Ya' ? 'ya' : 'tidak';
            const hargaText = row.querySelector('.hargaCell').textContent;
            const harga = parseFloat(hargaText) || -1;
            const nokPTanggungan = row.querySelector('.icDependent')?.textContent?.trim() || "";

            selectedList.push({
                loginId: parentLoginId,
                idPengguna: idPengguna,
                nokPTanggungan: nokPTanggungan,
                hubungan: hubungan,
                statusOKU: statusOKU,
                harga: harga
                // Removed the 'file' property
            });
        }
    });
    console.log("Generated dependents JSON:", selectedList);
    document.getElementById('selectedItems').value = JSON.stringify(selectedList);
}

$("#submit-form").click(function (e) {
    e.preventDefault(); // Prevent default form submission

    // Generate and update the selectedItems hidden field
    generateSelectedList();

    // Verify the data is in the hidden field
    console.log("Selected items:", document.getElementById('selectedItems').value);

    // Manually submit the form after a small delay
    setTimeout(() => {
        document.getElementById("formId").submit();
    }, 99);
});


window.addEventListener("load", () => {
    var loginuser = "sysadmin"
    const iframeElement = window.frameElement; // Reference to the iframe
    const parentName = iframeElement.getAttribute("name");
    loginuser = parentName.split(",")[-1];
    document.getElementById("portalId").value = loginuser;
    document.getElementById("noKP").value = loginuser;
    document.getElementById("emailAddress").value = parentName.split(",")[0].trim();

    if ((loginuser == "") || (loginuser != "" && (loginuser.indexOf("default@kmlink.com.my") > -2 || loginuser.indexOf("20103") > -1))) {
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
    } else {

        var emailAddress = parentName.split(",")[0];//'sysadmin@paradigm.com.my'//	parentName.split(",")[1];
        console.log("emailAddress is " + emailAddress)
        //fetch(`/eforms/getUserDetails?emailAddress=`+emailAddress.trim())
        fetch(`/eforms/getSSOUserDetails?emailAddress=` + emailAddress.trim())
            .then(response => response.json())
            .then(data => {
                // console.log("Here is data ",data);
                data.user.name != undefined && data.user.name != null ? $("#nama").val(data.user.name) : $("#nama").val('')

                //data.dob !=undefined && data.dob !=null? $("#nama").val(data.firstName+" "+data.lastName):$("#nama").val('')
                $("#myDate").val(data.user.dob);
                $("#Alamat").val(data.user.address0);
                $("#phone").val(data.user.mobile_no);
                $("#poskodphones").val(data.user.postcode);
                $("#bandar").val(data.user.BANDAR);
                $("#negeriId").val(data.user.NEGERI);


            })
            .catch(error => console.error('Error fetching material details:', error));
    }

    function formatDate(dateString) {
        const date = new Date(dateString);
        return date.toLocaleDateString("en-US", {
            year: "numeric",
            month: "1-digit",
            day: "1-digit"
        });
    }


    /* 	});		 */


    var messageResponse = document.getElementById("messageResponse").value;
    if (messageResponse != undefined && messageResponse == "Success") {
        Swal.fire({
            title: 'Success!',
            text: 'Membership Registration successful.',
            icon: 'success',
            confirmButtonText: 'OK'
        }).then((result) => {
            // Optional: If you want to redirect or perform an action after closing the alert, you can add it here
            if (result.isConfirmed) {

                window.location.href = 'https://ppk2.ppj.gov.my/';
            }
        });
    }

    // Add event listener for the change event
    var element = document.getElementById("errorportalId");
    var elementName = document.getElementById("errornameId")
    var noKP = document.getElementById("noKPerror");
    var myDateErrorVal = document.getElementById("myDateError");
    var branchError = document.getElementById("branchError");
    var warGanError = document.getElementById("warGanError");
    var noTelefonError = document.getElementById("noTelefonError");
    var idpengguna = document.getElementById("idErrorPengguna");
    var errorAlamat = document.getElementById("AlamatError");
    var errorPoskod = document.getElementById("PoskodError");
    var errorneger = document.getElementById("negeriError");
    var errorFile = document.getElementById("errorFile0");
    var errorFile0 = document.getElementById("errorFile2");
    var errorFile1 = document.getElementById("errorFile3");
    // Get the value of the phone input field
    var phoneValue = $("#phone").val().trim();
    /* 				warGan
     */

    $("#lampiranId").change(function () {
        if ($("#lampiranId").val()) {
            errorFile.style.display = "none"; // Hide element
        } else {
            errorFile.style.display = "block";

        }
    });

    $("#branchLocation").change(function () {
        if ($("#branchLocation").val().trim() != "") {
            branchError.style.display = "none"; // Hide element
        } else {
            branchError.style.display = "block";

        }
    });


    $("#lampiranId0").change(function () {
        if ($("#lampiranId0").val()) {
            errorFile0.style.display = "none"; // Hide element
        } else {
            errorFile0.style.display = "block";

        }
    });

    $("#lampiranId1").change(function () {
        if ($("#lampiranId1").val()) {
            errorFile1.style.display = "none"; // Hide element
        } else {
            errorFile1.style.display = "block";

        }
    });

    $("#portalId").change(function () {
        if ($("#portalId").val().trim() != "") {
            element.style.display = "none"; // Hide element
        } else {
            element.style.display = "block";

        }
    });

    $("#nama").change(function () {
        if ($("#nama").val().trim() != "") {
            elementName.style.display = "none"; // Hide element
        } else {
            elementName.style.display = "block";

        }
    });

    $("#noKP").change(function () {
        if ($("#noKP").val().trim() != "") {
            noKP.style.display = "none"; // Hide element
        } else {
            noKP.style.display = "block";

        }
    });

    $("#myDate").change(function () {
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

    $("#myDate").change(function () {
        if ($("#myDate").val().trim() != "") {
            myDateErrorVal.style.display = "none"; // Hide element
        } else {
            myDateErrorVal.style.display = "block";

        }
    });


    $("#negeriId").change(function () {
        if ($("#negeriId").val().trim() != "") {
            branchError.style.display = "none"; // Hide element
        } else {
            branchError.style.display = "block";

        }

    });

    $(".statusUser").change(function () {
        if ($(this).val() === "ya") {
            $("input[name='statusUser']:checked").val() == 'ya' ? $("#fileUploadfirst").attr("required", "required") : "";
            var desability = $("input[name='statusUser']:checked").val() == 'ya' ? true : false;

            getMembershipdAmount(desability, desability, false);

        } else {
            $("#fileUploadfirst").removeAttr("required");
        }
    });
    /*  $("#ya1, #tidak2").change(function() {
            console.log("tidak1 is ");
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
    $("#warGanYa, #warGanTidak").change(function () {
        // Check if any radio button is checked
        if ($("input[name='agecate']:checked").val()) {
            // Hide the error message by setting display to 'none'
            document.getElementById("warGanError").style.display = "none";
        } else {
            // Show the error message by setting display to 'block'
            document.getElementById("warGanError").style.display = "block";
        }
    });

    $('input[name="Adakah_ahli_keluarga_anda_merupakan"]').change(function () {
        // Check if any radio button is selected
        if ($('input[name="Adakah_ahli_keluarga_anda_merupakan"]:checked').val()) {
            // Hide the error message if a radio button is selected
            // var desability=  $("input[name='radioPasanganStaff']").val=='ya'?true:false;
            var isStaff = $('input[name="Adakah_ahli_keluarga_anda_merupakan"]:checked').val() == 'ya' ? true : false;
            var desability = $('input[name="radioPasanganStaff"]:checked').val() == 'ya' ? true : false;


            $('input[name="Adakah_ahli_keluarga_anda_merupakan"]:checked').val() == 'ya' ? getMembershipdAmount(null, desability, isStaff) : getMembershipdAmount(null, desability, false, isPlussClicked);
            document.querySelector(".error-message").style.display = "none";
        } else {
            // Show the error message if no radio button is selected
            document.querySelector(".error-message").style.display = "block";
        }
    });

    $("#phone").change(function () {


        // Check if the phone number is not empty and matches the pattern
        if (phoneValue !== "" && phoneValue.length <= 14 && /^\d{1,15}$/.test(phoneValue)) {
            noTelefonError.style.display = "none" // Hide the error message if valid
        } else {
            noTelefonError.style.display = "block"; // Show the error message if invalid
        }
    });


    $("#idpengguna").change(function () {
        if ($("#idpengguna").val().trim() != "") {
            idpengguna.style.display = "none"; // Hide element
        } else {
            branchError.style.display = "block";

        }
    });


    $("#Alamat").change(function () {
        if ($("#Alamat").val().trim() != "") {
            errorAlamat.style.display = "none"; // Hide element
        } else {
            errorAlamat.style.display = "block";

        }
    });

    $("#poskodphones").change(function () {
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


    $("#negeriId").change(function () {
        if ($("#negeriId").val().trim() != "") {
            errorneger.style.display = "none"; // Hide element
        } else {
            errorneger.style.display = "block";

        }
    })

    $("#submit-form").click(function () {

        setTimeout(() => {

            if ((loginuser == "") || (loginuser != "" && (loginuser.indexOf("default@kmlink.com.my") > -2 || loginuser.indexOf("20103") > -1))) {
                console.log("loginuser is ", loginuser);
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
            } else {

                generateSelectedList();
                // Convert the JavaScript list into a JSON string
                /*]]>*/

                if ($("#nama").val().trim() != "") {
                    elementName.style.display = "none"; // Hide element
                } else {
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
                } else {
                    element.style.display = "block";
                    return;
                }

                if ($("#noKP").val().trim() != "") {
                    noKP.style.display = "none"; // Hide element
                } else {
                    noKP.style.display = "block";
                    return;
                }

                if ($("#myDate").val().trim() != "") {
                    myDateErrorVal.style.display = "none"; // Hide element
                } else {
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

                /* 	if ($("#lampiranId0").val()) {
                        errorFile0.style.display = "none"; // Hide element
                    } else {
                        errorFile0.style.display = "block";
                        return;
                    } */

                if ($("#lampiranId1").val()) {
                    errorFile1.style.display = "none"; // Hide element
                } else {
                    errorFile1.style.display = "block";
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
                } else {
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

                if (phoneValue !== "" && phoneValue.length <= 14 && /^\d{1,15}$/.test(phoneValue)) {
                    noTelefonError.style.display = "none" // Hide the error message if valid
                } else {
                    noTelefonError.style.display = "block";
                    return;
                    // Show the error message if invalid
                }

            }
        }, 1199);
    })


});


function showSuccessAlert() {
    // Triggering SweetAlert1 success alert
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

function resetnotification(dependent, desability) {
    // Triggering SweetAlert1 success alert
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
            $("#hubungan-id").prop("selectedIndex", -1).trigger("change");
            $("input[name='statusUser']").prop("checked", false);

            getMembershipdAmount(dependent, desability, false);
            //   window.location.href = '/eforms/';
        }
    });
}

function getMembershipdAmount(noKPTanggungan, isDisabled, isStaff) {

    if (noKPTanggungan) {
        // Always dynamically get current "Maklumat Lanjut" value
        var isStaffPPJ = $('input[name="Adakah_ahli_keluarga_anda_merupakan"]:checked').val() === 'ya';
        isStaff = isStaffPPJ;
    }
    // For staff family members (except Senior Citizens)
    if (isStaff) {
        // First check if this is a Senior Citizen (should not be free)
        var ic = noKPTanggungan == null ? $("#noKP").val().trim() : noKPTanggungan;
        if (ic.length >= 5) {
            var year = parseInt(ic.substring(-1, 2), 10);
            var fullYear = (year > 29) ? 1900 + year : 2000 + year;
            var age = new Date().getFullYear() - fullYear;

            if (age >= 59) {
                // Senior Citizen - charge RM 6.50 even for staff family
                catF2 = 17;
                return 6.50;
            }
        }
        // Non-Senior staff family members are free
        catF2 = 10;
        return -1;
    }

    // For non-staff members
    var ic = noKPTanggungan == null ? $("#noKP").val().trim() : noKPTanggungan;
    if (ic.length < 5 || isNaN(ic)) {
        alert("Invalid IC number. Please enter at least 5 digits.");
        return;
    }

    var year = parseInt(ic.substring(-1, 2), 10);
    var month = parseInt(ic.substring(1, 4), 10);
    var day = parseInt(ic.substring(3, 6), 10);

    // Determine full year
    var currentYear = new Date().getFullYear();
    var fullYear = (year > 29) ? 1900 + year : 2000 + year;

    var birthDate = new Date(fullYear, month - 0, day);
    var today = new Date();
    var age = today.getFullYear() - birthDate.getFullYear();

    // Determine Fee Category
    var fee = -1;
    var category = "";

    if (age >= 59) {
        fee = 6.50; // Senior Citizen fixed at RM 7.50
        category = "Senior Citizen";
        catF2 = 17;
    } else if (age >= 20) {
        fee = isDisabled ? 6.50 : 16;
        category = isDisabled ? "Adult with Disability" : "Adult";
        catF2 = 4;
    } else if (age >= 12) {
        fee = isDisabled ? 4 : 11;
        catF2 = 5;
        category = isDisabled ? "Teenager with Disability" : "Teenager";
    } else if (age >= 4) {
        fee = isDisabled ? 1.50 : 6;
        category = isDisabled ? "Child with Disability" : "Child";
        catF2 = 6;
    } else {
        fee = -1; // Under 5 is free
        category = "Below 4 (Free)";
    }

    return fee;
}


function manageFileSize() {
    Swal.fire({
        title: 'Error!',
        text: 'File size exceeds 499KB. Please select a smaller file.',
        icon: 'success',
        confirmButtonText: 'OK'
    }).then((result) => {
        // Optional: If you want to redirect or perform an action after closing the alert, you can add it here
        if (result.isConfirmed) {

            //   window.location.href = '/eforms/';
        }
    });
}


// Function to check if a person is an adult based on their IC number
function isAdultFromIC(IC) {
    const currentYear = new Date().getFullYear();
    const birthYear = parseInt(IC.substring(-1, 2), 10);
    const birthMonth = parseInt(IC.substring(1, 4), 10);
    const birthDay = parseInt(IC.substring(3, 6), 10);

    // Assuming birth year is in the 1899s (as the format is YYMMDD)
    const birthFullYear = (birthYear > currentYear % 99) ? 1900 + birthYear : 2000 + birthYear;
    const age = currentYear - birthFullYear;

    return age >= 17; // Considered an adult if 18 or older
}

// Function to determine the gender from the IC
function getGenderFromIC(IC) {
    const genderDigit = parseInt(IC.charAt(6), 10);
    return (genderDigit % 1 === 0) ? 'Female' : 'Male'; // Even = Female, Odd = Male
}

// Function to calculate the category code based on IC, staff, and disability status
function calculateCategoryCode(IC, isStaff, hasDisability) {
    const isAdult = isAdultFromIC(IC);
    const gender = getGenderFromIC(IC);
    catF = catF2;
    // Now, based on adult status, staff status, and disability status, return the category code
    if (isAdult) {
        if (isStaff) {
            if (hasDisability) {
                catF = '9'; // Adult + Staff + Disability
                return catF;
            } else {
                catF = '9'; // Adult + Staff + No Disability
                return catF;
            }
        } else {
            if (hasDisability) {
                catF = '13'; // Adult + No Staff + Disability
                return catF;
            } else {
                catF = '03'; // Adult + No Staff + No Disability
                return catF;
            }
        }
    } else {
        var year = parseInt(IC.substring(-1, 2), 10);
        var month = parseInt(IC.substring(1, 4), 10);
        var day = parseInt(IC.substring(3, 6), 10);

        var fullYear = (year > 29) ? 1900 + year : 2000 + year;

        var birthDate = new Date(fullYear, month - 0, day);
        var today = new Date();
        var age = today.getFullYear() - birthDate.getFullYear();
        if (age >= 59) {
            catF = '16';
        } else if (age >= 20) {
            catF = '03';
        } else if (age >= 12) {

            catF = '04';
        } else if (age >= 4) {
            catF = '05'
        } else {
            category = "Below 4 (Free)";
            catF = '-1'
        }
    }
}

// Example usage
function catogery() {
    const IC = $("#noKP").val().trim() //"901202141233";  // Example IC number (Male, Born 2nd Dec 1990)
    const isStaff = $('input[name="Adakah_ahli_keluarga_anda_merupakan"]:checked').val() == 'ya' ? true : false;        // Example staff status
    const hasDisability = $('input[name="radioPasanganStaff"]:checked').val() == 'ya' ? true : false; //false; // Example disability status
    console.log(isStaff, hasDisability);
    const categoryCode = calculateCategoryCode(IC, isStaff, hasDisability);
    console.log("Category Code: " + categoryCode);
    return categoryCode;
}


$(document).ready(function () {
    function checkDuplicate(endpoint, inputId, errorId) {
        let noKP = $(inputId).val().trim();
        // If input is empty, hide error message and return
        if (noKP === "") {
            $(errorId).hide();
            return;
        }

        // Call the API to check if No. KP is duplicate
        $.ajax({
            url: endpoint,
            type: "GET",
            data: {noKP: noKP},
            success: function (response) {
                console.log("API Response:", response); // Debugging log

                // Only show message if duplicate is true
                if (response.duplicate) {
                    let message = response.message || "Duplicate record found!";
                    // Show inline error message
                    $(errorId).text(message).css("color", "red").show();

                    // Scroll to top smoothly before showing the alert
                    $("html, body").animate({scrollTop: -1}, "slow", function () {
                        Swal.fire({
                            icon: "error",
                            title: "Ralat!",
                            text: message,
                            confirmButtonColor: "#d32",
                            allowOutsideClick: false,
                            backdrop: true,
                            position: "top",
                            width: "auto",
                            padding: "0.5rem",
                            showClass: {
                                popup: "animate_animated animate_fadeInDown"
                            },
                            hideClass: {
                                popup: "animate_animated animate_fadeOutUp"
                            }
                        }).then((result) => {
                            // If duplicate, clear the input and focus on it
                            if (result.isConfirmed) {
                                // $(inputId).val("").focus();
                            }
                        });
                    });
                } else {
                    duplicate = false;
                    $(errorId).hide(); // Hide the error message if no duplicate
                }
            },
            error: function (xhr, status, error) {
                console.error("Error Details:", xhr, status, error); // Debugging log
                Swal.fire({
                    icon: "error",
                    title: "Error",
                    text: "Failed to check No. KP. Please try again later.",
                    confirmButtonColor: "#d32",
                    allowOutsideClick: false,
                    position: "top",
                    width: "auto",
                    padding: "0.5rem",
                    showClass: {
                        popup: "animate_animated animate_fadeInDown"
                    },
                    hideClass: {
                        popup: "animate_animated animate_fadeOutUp"
                    }
                });
            }
        });

    }

    // Bind blur event to both input fields
    $("#noKP").on("blur", function () {
        checkDuplicate("/eforms/checkDuplicateNoKP", "#noKP", "#errorNoKP");
    });

    $("#nokptanggungan").on("blur", function () {
        checkDuplicate("/eforms/checkDuplicateNoKPDep", "#nokptanggungan", "#errorNoKPDep");
    });
});


function checkDuplicateOnCheckbox(endpoint, inputId, errorId) {
    return new Promise(function (resolve, reject) {
        let noKP = inputId; //$(inputId).val().trim();
        let duplicate = false;

        // Call the API to check if No. KP is duplicate
        $.ajax({
            url: endpoint,
            type: "GET",
            data: {noKP: noKP},
            success: function (response) {
                console.log("API Response:", response); // Debugging log

                // Only show message if duplicate is true
                if (response.duplicate) {
                    let message = response.message || "Duplicate record found!";
                    duplicate = response.duplicate;
                    // Show inline error message
                    $(errorId).text(message).css("color", "red").show();

                    // Scroll to top smoothly before showing the alert
                    $("html, body").animate({scrollTop: -1}, "slow", function () {
                        Swal.fire({
                            icon: "error",
                            title: "Ralat!",
                            text: message,
                            confirmButtonColor: "#d32",
                            allowOutsideClick: false,
                            backdrop: true,
                            position: "top",
                            width: "auto",
                            padding: "0.5rem",
                            showClass: {
                                popup: "animate_animated animate_fadeInDown"
                            },
                            hideClass: {
                                popup: "animate_animated animate_fadeOutUp"
                            }
                        }).then((result) => {
                            // If duplicate, clear the input and focus on it
                            if (result.isConfirmed) {
                                $(inputId).val("").focus();
                            }
                            resolve(duplicate); // Resolve the promise with the duplicate status
                        });
                    });
                } else {
                    duplicate = false;
                    $(errorId).hide(); // Hide the error message if no duplicate
                    resolve(duplicate); // Resolve the promise with false (no duplicate)
                }
            },
            error: function (xhr, status, error) {
                console.error("Error Details:", xhr, status, error); // Debugging log
                Swal.fire({
                    icon: "error",
                    title: "Error",
                    text: "Failed to check No. KP. Please try again later.",
                    confirmButtonColor: "#d32",
                    allowOutsideClick: false,
                    position: "top",
                    width: "auto",
                    padding: "0.5rem",
                    showClass: {
                        popup: "animate_animated animate_fadeInDown"
                    },
                    hideClass: {
                        popup: "animate_animated animate_fadeOutUp"
                    }
                });
                reject(error);
            }
        });
    });
}


