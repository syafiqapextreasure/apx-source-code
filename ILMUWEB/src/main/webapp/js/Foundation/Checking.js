document.getElementById("number").addEventListener("keypress", myFunction);

function myFunction() {
	 var regex = new RegExp("[0-9]+");
	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	    if (!regex.test(key)) {
	       event.preventDefault();
	       return false;
	       alert("Please insert number only.");
	    }
}