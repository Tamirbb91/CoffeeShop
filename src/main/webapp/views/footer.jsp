<%--
  Created by IntelliJ IDEA.
  User: Tamir
  Date: 3/12/2018
  Time: 11:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Footer</title>
    <style>
        #footer{
            width: 100%;
            text-align: center;
            background-color: aliceblue;
        }

        #toast {
            visibility: hidden; /* Hidden by default. Visible on click */
            min-width: 250px; /* Set a default minimum width */
            margin-left: -125px; /* Divide value of min-width by 2 */
            background-color: #333; /* Black background color */
            color: #fff; /* White text color */
            text-align: center; /* Centered text */
            border-radius: 2px; /* Rounded borders */
            padding: 16px; /* Padding */
            position: fixed; /* Sit on top of the screen */
            z-index: 1; /* Add a z-index if needed */
            left: 50%; /* Center the toast */
            bottom: 30px; /* 30px from the bottom */
        }

        /* Show the toast when clicking on a button (class added with JavaScript) */
        #toast.show {
            visibility: visible; /* Show the toast */

            /* Add animation: Take 0.5 seconds to fade in and out the toast. 
            However, delay the fade out process for 2.5 seconds */
            -webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
            animation: fadein 0.5s, fadeout 0.5s 2.5s;
        }

        /* Animations to fade the toast in and out */
        @-webkit-keyframes fadein {
            from {bottom: 0; opacity: 0;}
            to {bottom: 30px; opacity: 1;}
        }

        @keyframes fadein {
            from {bottom: 0; opacity: 0;}
            to {bottom: 30px; opacity: 1;}
        }

        @-webkit-keyframes fadeout {
            from {bottom: 30px; opacity: 1;}
            to {bottom: 0; opacity: 0;}
        }

        @keyframes fadeout {
            from {bottom: 30px; opacity: 1;}
            to {bottom: 0; opacity: 0;}
        }
    </style>
</head>
<body>
<div id="footer">
    MUM WAA Course project<br>
    Tamir Batmunkh <a href="mailto:tamirsnarf@gmail.com">tamirsnarf@gmail.com</a><br>
    2018
</div>
<c:if test="${message != null}">
    <div id="toast">
            ${message}
    </div>
</c:if>
<script language="JavaScript">
    window.onload = function () {
        var x = document.getElementById("toast");
        x.className = "show";
        setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
    }
</script>

<script language="JavaScript"
        src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
