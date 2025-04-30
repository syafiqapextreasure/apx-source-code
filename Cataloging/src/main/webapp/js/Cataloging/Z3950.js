function starting(){
	alert("ddd");
}

function selectValue(select) {
    return select[select.selectedIndex].value;
}

function dateNull(date, month, year) {
    if (date == -1 && month == -1 && year == -1) {
        return true;
    }
    return false;
}

function dateValue(date, month, year) {
    return (new Date(year, month - 1, date)).valueOf();
}

function dateValid(date, month, year, mandatory) {
    var iDate = parseInt(selectValue(date));
    var iMonth = parseInt(selectValue(month));
    var iYear = parseInt(selectValue(year));

    if (! mandatory) {
        if (iDate == -1 && iMonth == -1 && iYear == -1) {
            return true;
        }
    }
    if (iDate == -1 || iMonth == -1 || iYear == -1) {
        return false;
    }
    if ((new Date(iYear, iMonth - 1, iDate)).getMonth() != iMonth - 1) {
        return false;
    }
    return true;
}

function integerValid(text) {
    var sInteger = "0123456789";
    var sText = text.value;

    for (var i = 0; i < sText.length; i ++) {
        if (sInteger.indexOf(sText.charAt(i)) == -1) {
            return false;
        }
    }
    return true;
}

function imageValid(image) {
    var ext = image.value;
    var i = ext.lastIndexOf(".");
    ext = ext.substring(i, ext.length).toLowerCase();
    if ((ext != ".gif") && (ext != ".jpg") && (ext != ".jpeg")) {
       return false;
   }
   return true;
}

function numberValid(text) {
    var sNumber = "0123456789.-";
    var sText = text.value;
    var counter = 0;
    var counter2 = 0;

    for (var i = 0; i < sText.length; i ++) {
        if (sText.charAt(i) == "-") {
            counter2++;
            if (counter2 == 2) {
                return false;
            }
        }
        if (sText.charAt(i) == ".") {
            counter++;
            if (counter == 2) {
                return false;
            }
        }
        if (sText.charAt(i+1) == "-") {
            return false;
        }
        if (sNumber.indexOf(sText.charAt(i)) == -1) {
            return false;
        }
    }
    return true;
}

function textValid(text) {
    var sText = text.value;

    for (var i = 0; i < sText.length; i ++) {
        if (sText.charAt(i) != " ") {
            return true;
        }
    }
    return false;
}

function emailValid(email) {
    var sText = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!#$%&'*+-/=?^_`{|}~";
    var email = email.value;
    var aEmail = email.toUpperCase().split("@");
    if (aEmail.length != 2) {
        return false;
    }
    for (var a = 0; a < aEmail.length; a++) {
        var sPart = aEmail[a];
        if (sPart.length == 0) {
            return false;
        }
        for (var i = 0; i < sPart.length; i ++) {
            if (sText.indexOf(sPart.charAt(i)) == -1) {
                if (sPart.charAt(i) != ".") {
                    return false;
                }
                if (i == 0 || i == sPart.length - 1) {
                    return false;
                }
                if (sText.indexOf(sPart.charAt(i + 1)) == -1) {
                    return false;
                }
            }
        }
    }
    return true;
}


function linkValid(link) {
    var link = link.value;
    var link1 = link.substring(0,7).toLowerCase();
    var link2 = link.substring(0,6).toLowerCase();
    var link3 = link.substring(0,8).toLowerCase();

    if (link1 != "http://") {
      if (link2 != "ftp://") {
        if (link3 != "https://") {
          return false;
        }
      }
    }
    return true;
}

function alphanumericValid(text) {
    var sAlphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    var sText = text.value.toUpperCase();

    for (var i = 0; i < sText.length; i ++) {
        if (sAlphaNumeric.indexOf(sText.charAt(i)) == -1) {
            return false;
        }
    }
    return true;
}

function stringReplace(s1, s2, s3) {
    var aryS = s1.split(s2);
    var s4 = "";
    for (var i = 0; i < aryS.length; i ++) {
        s4 += aryS[i] + ((i == aryS.length - 1) ? "" : s3);
    }
    return s4;
}

function goPage(url) {
    window.location.href = url;
    return false;
}

function goPage2(module, action) {
    window.location.href = "index.jsp?module=" + module + "&action="  + action;
    return false;
}

function backPage() {
    history.back();
    return false;
}

function resetForm(form) {
    form.reset();
    return false;
}

function submitForm(form, url) {
    form.action = url;
    form.submit();
    return false;
}

function submitForm2(form, module) {

   // form.action = "/index.jsp?module=" + module + "&action="  + action;
	form.action = module;
    form.submit();
    return false;
}

function submitForm3(form, path, module, action) {
    form.action = path + "/index.jsp?module=" + module + "&action="  + action;
    form.submit();
    return false;
}

function swapImgRestore() {
    var i,x,a=document.sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function preloadimages() {
    var d=document; if(d.images){ if(!d.p) d.p=new Array();
    var i,j=d.p.length,a=preload.images.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.p[j]=new Image; d.p[j++].src=a[i];}}
}

function findObj(n, d) {
    var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
    if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
    for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=findObj(n,d.layers[i].document); return x;
}

function swapImage() {
    var i,j=0,x,a=swapImage.arguments; document.sr=new Array; for(i=0;i<(a.length-2);i+=3)
    if ((x=findObj(a[i]))!=null){document.sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

function getCheckedValue(objName) {
    var elems = document.getElementsByTagName("input");
    var isChecked = false;
    var checkedValue = "";
    for (var i = 0; i < elems.length && ! isChecked; i++) {
        if (elems[i].name == objName) {
            if (elems[i].checked) {
                isChecked = true;
                checkedValue = elems[i].value;
            }
        }
    }
    return checkedValue;
}

function openChildWindow(url, width, height) {
    return window.open(url, '', 'resizable=yes, toolbar=no,status=no,scrollbars=yes,location=no,menubar=no,directories=no,width=' + width + ',height=' + height);
}

function setRadioValue(name, value) {
    var y = document.getElementsByTagName("input");
    for (var l=0;l<y.length;l++) {
        if ((y[l].name) == name) {
            if ((y[l].value) == value) {
                y[l].checked = true;
            }
        }
    }
}

function setUnicodeValue(obj, value){
    if (value != "") {
        var domDiv = document.createElement("div");
        domDiv.innerHTML = value;
        obj.value = domDiv.firstChild.nodeValue;
    } else {
        obj.value = "";
    }
}
