<%

    String message = "";

    String invalidMessage = null;

    String username = "";

    String userName = (String) request.getAttribute("USERNAME");

    String pathToPage = (String) request.getAttribute("PATHTOPAGE");



    invalidMessage = (String) session.getAttribute("InvalidMessage");

    if (invalidMessage != null){

        message = invalidMessage;

        session.removeAttribute("InvalidMessage");

    }

 %>



<script language = "JavaScript">



function validateForm(){

    var form = document.formLogin;

    var url = 'index.jsp?module=memberPage&action=memberPage.jsp';

    if ((! textValid(form.LoginName)) && (! textValid(form.Password))){

        alert("Please enter a login name and password.");

        form.LoginName.focus();

        return false;

    } else if (! textValid(form.Password)){

        alert("Please enter a valid Name passsword.");

        form.Password.focus();

        return false;

    } else if (! textValid(form.LoginName)){

        alert("Please enter a valid Name login name.");

        form.LoginName.focus();

        return false;

    } else{

        form.page.value = "home.jsp";

        return submitForm(form, url);

    }

}



</script>



<form name="formLogin" method="post" onsubmit="return false;">

  <input type="hidden" name="page">

  <div style="text-align:center">

  <table border="0" cellpadding="0" cellspacing="0" width="100%">

    <tr>

      <td align="center">

        <table border="0" cellpadding="0" cellspacing="0" width="88%">

          <tr>

            <td bgcolor="#14018A" class="white" align="center">LOGIN</td>

          </tr>

          <tr>

            <td bgcolor="#DDECFF" align="center">

              <br />

<%

    if (userName == null) {

%>

              <div class="blue">Login Name :</div>

              <input type="text" name="LoginName" size="15">

              <br />

              <br />

<%

    } else {

%>

              <input type="hidden" name="LoginName" value="<%= userName %>">

              <div class="red"><%= userName %></div>

              <br />

<%

    }

%>

              <div class="blue">Password :</div>

              <input type="password" name="Password" size="15">

              <br />

              <br />

<%

    if ((message != null)&&(!message.equals(""))) {

%>

              <div class="red"><i><%= message %></i></div>

              <br />

<%

    }

%>

              <div class="note">

<%

    if (userName != null) {

%>

                <a href="" onclick="submitForm(document.formLogin, 'index.jsp?module=ROOT&action=logout.jsp'); return false;">Login as another user</a>

<%

    } else {

%>

                <a href="" onclick="submitForm(document.formLogin, 'index.jsp?module=hintPassword&action=hint.jsp'); return false;">

                  Forgot your Login Name <br />

                  and Password?

                </a>

<%

    }

%>

              </div>

              <br />

              <input type="image" border="0" src="images/submit.gif" width="66" height="22" onclick="validateForm(); return false;">

            </td>

          </tr>

        </table>

      </td>

    </tr>

    <tr>

      <td>&nbsp;</td>

    </tr>

    <tr>

      <td align="center">

        <input type="image" border="0" src="images/signup.gif" width="110" height="60" onclick="submitForm(document.formLogin, 'index.jsp?module=registration&action=registration1.jsp'); return false;">

      </td>

    </tr>

  </table>

  </div>

</form>