<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>

<head>
    <!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"> -->
<%--     <link rel="stylesheet" href="<c:url value=" ../resources/style.css" />" /> --%>
        <link rel="stylesheet" href="resources/spineLabel/css/style.css" />
    <!-- <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"> -->
<%--    
    <style type="text/css">
        <%@include file="../resources/style.css"%>
    </style>
 --%>

    <title>Read</title>
</head>
<!-- 	<p><strong><a href="printPDF">Print</a></strong></p> -->
<div class="no-print">
    <button style="margin:auto;display:inline-block;" class="no-print" onclick="history.back()">Cancel</button>
    <button style="margin:auto;display:block;" class="no-print" onclick="window.print();return false;">Print</button>
</div>

<body>
    <page size="A4" layout="landscape">
    
    
        <!-- TABLE 1  -->
        <c:set var="val1" value="${table1}" />
        <c:choose>
            <c:when test="${val1 == '0'}">

                <table>
                    <tr>
                        <td class="hiddenTableLeft tableLeft tableTop cellBottomMargin">1</td>
                    </tr>
                </table>

            </c:when>
            <c:otherwise>    
    
               <table id="table1" class="tableLeft">
                    <tr>
                        <td style="background-color:${label1colcode};">${label1location}</td>
                        <td class="logoBar" rowspan="4"><img class="logoImage" src="${logoLink}">
                            <div class="textLabel">${textLabel}</div></td>
                        <td class="emptyCell20mm" rowspan="8"></td>
                    </tr>
                    <tr>
                        <td style="background-color:${label1colour1};">${fn:substring(label1callno1,0,1)}</td>
                    </tr>
                    <tr>
                        <td style="background-color:${label1colour2};">${fn:substring(label1callno1,1,2)}</td>
                    </tr>
                    <tr>
                        <td style="background-color:${label1colour3};">${fn:substring(label1callno1,2,3)}</td>
                    </tr>
                    <tr class='cutterRow'>
                        <c:choose>
                            <c:when test="${checking1 == 'on'}">
                                <td style="background-color:${label1colour4};">${fn:substring(label1callno1,3,4)}</td>
                            </c:when>
                            <c:otherwise>
                                <td></td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>
 
                                <td class='narrow' style="background-color:${label1colour5};">${label1itemclass}</td>
   
                                <td class='wide' style="background-color:${label1colour5};border-top: 0.1mm solid; height: 35px;">${fn:substring(label1callno1,3,4)}</td>

                        <td class="logoBar" rowspan="2">
                            <svg class="barcodeImage barcode" jsbarcode-format="code39" jsbarcode-value= ${accessionNo1} 
                            	jsbarcode-width="1" jsbarcode-height="23" jsbarcode-margin="6"  
                            	jsbarcode-font="arial" jsbarcode-fontSize="11" >
                            </svg>                         
                        </td>
                    </tr>
                    <tr>
                        <td class="noBorder" rowspan="2">${label1AuthMark}<br><span <c:if test="${label1CallNo == '.'}">style="color: white;"</c:if>>${label1CallNo}</span></td>
                    </tr>
                </table>
                            </c:otherwise>
        </c:choose>
                
        <!-- TABLE 2  -->
        <c:set var="val2" value="${table2}" />
        <c:choose>
            <c:when test="${val2 == '0'}">

                <table>
                    <tr>
                        <td class="hiddenTableLeft tableLeft tableTop cellBottomMargin">1</td>
                    </tr>
                </table>

            </c:when>
            <c:otherwise>    
    
               <table id="table2" class="tableLeft">
                    <tr>
                        <td style="background-color:${label2colcode};">${label2location}</td>
                        <td class="logoBar" rowspan="4"><img class="logoImage" src="${logoLink}">
                            <div class="textLabel">${textLabel}</div></td>
                        <td class="emptyCell20mm" rowspan="8"></td>
                    </tr>
                    <tr>
                        <td style="background-color:${label2colour1};">${fn:substring(label2callno1,0,1)}</td>
                    </tr>
                    <tr>
                        <td style="background-color:${label2colour2};">${fn:substring(label2callno1,1,2)}</td>
                    </tr>
                    <tr>
                        <td style="background-color:${label2colour3};">${fn:substring(label2callno1,2,3)}</td>
                    </tr>
                    <tr class='cutterRow'>
                        <c:choose>
                            <c:when test="${checking2 == 'on'}">
                                <td style="background-color:${label2colour4};">${fn:substring(label2callno1,3,4)}</td>
                            </c:when>
                            <c:otherwise>
                                <td></td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>
                       <td class='narrow' style="background-color:${label2colour5};">${label2itemclass}</td>

                       <td class='wide' style="background-color:${label2colour5};border-top: 0.1mm solid; height: 35px;">${fn:substring(label2callno1,3,4)}</td>

                        <td class="logoBar" rowspan="2">
                            <svg class="barcodeImage barcode" jsbarcode-format="code39" jsbarcode-value= ${accessionNo2} 
                            	jsbarcode-width="1" jsbarcode-height="23" jsbarcode-margin="6"  
                            	jsbarcode-font="arial" jsbarcode-fontSize="11" >
                            </svg>                         
                        </td>
                    </tr>
                    <tr>
                        <td class="noBorder" rowspan="2">${label2AuthMark}<br><span <c:if test="${label2CallNo == '.'}">style="color: white;"</c:if>>${label2CallNo}</span></td>
                    </tr>

                </table>
                            </c:otherwise>
        </c:choose>
        
        
        <!-- TABLE 3  -->
        <c:set var="val3" value="${table3}" />
        <c:choose>
            <c:when test="${val3 == '0'}">

                <table>
                    <tr>
                        <td class="hiddenTableLeft tableLeft tableTop cellBottomMargin">1</td>
                    </tr>
                </table>

            </c:when>
            <c:otherwise>    
    

               <table id="table3" class="tableLeft">
                    <tr>
                        <td style="background-color:${label3colcode};">${label3location}</td>
                        <td class="logoBar" rowspan="4"><img class="logoImage" src="${logoLink}">
                            <div class="textLabel">${textLabel}</div></td>
                        <td class="emptyCell20mm" rowspan="8"></td>
                    </tr>
                    <tr>
                        <td style="background-color:${label3colour1};">${fn:substring(label3callno1,0,1)}</td>
                    </tr>
                    <tr>
                        <td style="background-color:${label3colour2};">${fn:substring(label3callno1,1,2)}</td>
                    </tr>
                    <tr>
                        <td style="background-color:${label3colour3};">${fn:substring(label3callno1,2,3)}</td>
                    </tr>
                    <tr class='cutterRow'>
                        <c:choose>
                            <c:when test="${checking3 == 'on'}">
                                <td style="background-color:${label3colour4};">${fn:substring(label3callno1,3,4)}</td>
                            </c:when>
                            <c:otherwise>
                                <td></td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>

                        <td class='narrow' style="background-color:${label3colour5};">${label3itemclass}</td>

                        <td class='wide' style="background-color:${label3colour5};border-top: 0.1mm solid; height: 35px;">${fn:substring(label3callno1,3,4)}</td>

                        <td class="logoBar" rowspan="2">
                            <svg class="barcodeImage barcode" jsbarcode-format="code39" jsbarcode-value= ${accessionNo3} 
                            	jsbarcode-width="1" jsbarcode-height="23" jsbarcode-margin="6"  
                            	jsbarcode-font="arial" jsbarcode-fontSize="11" >
                            </svg>                         
                        </td>
                    </tr>
                    <tr>
                        <td class="noBorder" rowspan="2">${label3AuthMark}<br><span <c:if test="${label3CallNo == '.'}">style="color: white;"</c:if>>${label3CallNo}</span></td>
                    </tr>
                </table>
                            </c:otherwise>
        </c:choose>                

       <!-- TABLE 4  -->
        <c:set var="val4" value="${table4}" />
        <c:choose>
            <c:when test="${val4 == '0'}">

                <table>
                    <tr>
                        <td class="hiddenTableLeft tableLeft tableTop cellBottomMargin">1</td>
                    </tr>
                </table>

            </c:when>
            <c:otherwise>    
    

               <table id="table4" class="tableLeft">
                    <tr>
                        <td style="background-color:${label4colcode};">${label4location}</td>
                        <td class="logoBar" rowspan="4"><img class="logoImage" src="${logoLink}">
                            <div class="textLabel">${textLabel}</div></td>
                        <td class="emptyCell20mm" rowspan="8"></td>
                    </tr>
                    <tr>
                        <td style="background-color:${label4colour1};">${fn:substring(label4callno1,0,1)}</td>
                    </tr>
                    <tr>
                        <td style="background-color:${label4colour2};">${fn:substring(label4callno1,1,2)}</td>
                    </tr>
                    <tr>
                        <td style="background-color:${label4colour3};">${fn:substring(label4callno1,2,3)}</td>
                    </tr>
                    <tr class='cutterRow'>
                        <c:choose>
                            <c:when test="${checking4 == 'on'}">
                                <td style="background-color:${label4colour4};">${fn:substring(label4callno1,3,4)}</td>
                            </c:when>
                            <c:otherwise>
                                <td></td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>

                        <td class='narrow' style="background-color:${label4colour5};">${label4itemclass}</td>

                        <td class='wide' style="background-color:${label4colour5};border-top: 0.1mm solid; height: 35px;">${fn:substring(label4callno1,3,4)}</td>

                        <td class="logoBar" rowspan="2">
                            <svg class="barcodeImage barcode" jsbarcode-format="code39" jsbarcode-value= ${accessionNo4} 
                            	jsbarcode-width="1" jsbarcode-height="23" jsbarcode-margin="6"  
                            	jsbarcode-font="arial" jsbarcode-fontSize="11" >
                            </svg>                         
                        </td>
                    </tr>
                    <tr>
                        <td class="noBorder" rowspan="2">${label4AuthMark}<br><span <c:if test="${label4CallNo == '.'}">style="color: white;"</c:if>>${label4CallNo}</span></td>
                    </tr>
                </table>
                            </c:otherwise>
        </c:choose> 
        
       <!-- TABLE 5  -->
        <c:set var="val5" value="${table5}" />
        <c:choose>
            <c:when test="${val5 == '0'}">

                <table>
                    <tr>
                        <td class="hiddenTableLeft tableLeft tableTop cellBottomMargin">1</td>
                    </tr>
                </table>

            </c:when>
            <c:otherwise>    
    

               <table id="table5" class="tableLeft">
                    <tr>
                        <td style="background-color:${label5colcode};">${label5location}</td>
                        <td class="logoBar" rowspan="4"><img class="logoImage" src="${logoLink}">
                            <div class="textLabel">${textLabel}</div></td>
                        <td class="emptyCell20mm" rowspan="8"></td>
                    </tr>
                    <tr>
                        <td style="background-color:${label5colour1};">${fn:substring(label5callno1,0,1)}</td>
                    </tr>
                    <tr>
                        <td style="background-color:${label5colour2};">${fn:substring(label5callno1,1,2)}</td>
                    </tr>
                    <tr>
                        <td style="background-color:${label5colour3};">${fn:substring(label5callno1,2,3)}</td>
                    </tr>
                    <tr class='cutterRow'>
                        <c:choose>
                            <c:when test="${checking5 == 'on'}">
                                <td style="background-color:${label5colour4};">${fn:substring(label5callno1,3,4)}</td>
                            </c:when>
                            <c:otherwise>
                                <td></td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                     <tr>

                        <td  class='narrow' style="background-color:${label5colour5};">${label5itemclass}</td>

                        <td class='wide' style="background-color:${label5colour5};border-top: 0.1mm solid; height: 35px;">${fn:substring(label5callno1,3,4)}</td>

                        <td class="logoBar" rowspan="2">
                            <svg class="barcodeImage barcode" jsbarcode-format="code39" jsbarcode-value= ${accessionNo5} 
                            	jsbarcode-width="1" jsbarcode-height="23" jsbarcode-margin="6"  
                            	jsbarcode-font="arial" jsbarcode-fontSize="11" >
                            </svg>                         
                        </td>
                    </tr>
                    <tr>
                        <td class="noBorder" rowspan="2">${label5AuthMark}<br><span <c:if test="${label5CallNo == '.'}">style="color: white;"</c:if>>${label5CallNo}</span></td>
                    </tr>
                </table>
                            </c:otherwise>
        </c:choose>     
       <!-- TABLE 6  -->
        <c:set var="val6" value="${table6}" />
        <c:choose>
            <c:when test="${val6 == '0'}">

                <table>
                    <tr>
                        <td class="hiddenTableLeft tableLeft tableTop cellBottomMargin">1</td>
                    </tr>
                </table>

            </c:when>
            <c:otherwise>    
    

               <table id="table6" class="tableLeft">
                    <tr>
                        <td style="background-color:${label6colcode};">${label6location}</td>
                        <td class="logoBar" rowspan="4"><img class="logoImage" src="${logoLink}">
                            <div class="textLabel">${textLabel}</div></td>
                        <td class="emptyCell20mm" rowspan="8"></td>
                    </tr>
                    <tr>
                        <td style="background-color:${label6colour1};">${fn:substring(label6callno1,0,1)}</td>
                    </tr>
                    <tr>
                        <td style="background-color:${label6colour2};">${fn:substring(label6callno1,1,2)}</td>
                    </tr>
                    <tr>
                        <td style="background-color:${label6colour3};">${fn:substring(label6callno1,2,3)}</td>
                    </tr>
                    <tr class='cutterRow'>
                        <c:choose>
                            <c:when test="${checking6 == 'on'}">
                                <td style="background-color:${label6colour4};">${fn:substring(label6callno1,3,4)}</td>
                            </c:when>
                            <c:otherwise>
                                <td></td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>

                        <td class='narrow' style="background-color:${label6colour5};">${label6itemclass}</td>

                        <td class='wide' style="background-color:${label6colour5};border-top: 0.1mm solid; height: 35px;">${fn:substring(label6callno1,3,4)}</td>

                        <td class="logoBar" rowspan="2">
                            <svg class="barcodeImage barcode" jsbarcode-format="code39" jsbarcode-value= ${accessionNo6} 
                            	jsbarcode-width="1" jsbarcode-height="23" jsbarcode-margin="6"  
                            	jsbarcode-font="arial" jsbarcode-fontSize="11" >
                            </svg>                         
                        </td>
                    </tr>
                     <tr>
                        <td class="noBorder" rowspan="2">${label6AuthMark}<br><span <c:if test="${label6CallNo == '.'}">style="color: white;"</c:if>>${label6CallNo}</span></td>
                    </tr>

                </table>
                            </c:otherwise>
        </c:choose>    
        
       <!-- TABLE 7  -->
        <c:set var="val7" value="${table7}" />
        <c:choose>
            <c:when test="${val7 == '0'}">

                <table>
                    <tr>
                        <td class="hiddenTableLeft tableLeft tableTop cellBottomMargin">1</td>
                    </tr>
                </table>

            </c:when>
            <c:otherwise>    
    

               <table id="table7" class="tableLeft">
                    <tr>
                        <td style="background-color:${label7colcode};">${label7location}</td>
                        <td class="logoBar" rowspan="4"><img class="logoImage" src="${logoLink}">
                            <div class="textLabel">${textLabel}</div></td>
                        <td class="emptyCell20mm" rowspan="8"></td>
                    </tr>
                    <tr>
                        <td style="background-color:${label7colour1};">${fn:substring(label7callno1,0,1)}</td>
                    </tr>
                    <tr>
                        <td style="background-color:${label7colour2};">${fn:substring(label7callno1,1,2)}</td>
                    </tr>
                    <tr>
                        <td style="background-color:${label7colour3};">${fn:substring(label7callno1,2,3)}</td>
                    </tr>
                    <tr class='cutterRow'>
                        <c:choose>
                            <c:when test="${checking7 == 'on'}">
                                <td style="background-color:${label7colour4};">${fn:substring(label7callno1,3,4)}</td>
                            </c:when>
                            <c:otherwise>
                                <td></td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                     <tr>

                        <td class='narrow' style="background-color:${label7colour5};">${label7itemclass}</td>

                        <td class='wide' style="background-color:${label7colour5};border-top: 0.1mm solid; height: 35px;">${fn:substring(label7callno1,3,4)}</td>

                        <td class="logoBar" rowspan="2">
                            <svg class="barcodeImage barcode" jsbarcode-format="code39" jsbarcode-value= ${accessionNo7} 
                            	jsbarcode-width="1" jsbarcode-height="23" jsbarcode-margin="6"  
                            	jsbarcode-font="arial" jsbarcode-fontSize="11" >
                            </svg>                         
                        </td>
                    </tr>
                    <tr>
                        <td class="noBorder" rowspan="2">${label7AuthMark}<br><span <c:if test="${label7CallNo == '.'}">style="color: white;"</c:if>>${label7CallNo}</span></td>
                    </tr>

                </table>
                            </c:otherwise>
        </c:choose>
       <!-- TABLE 8  -->
        <c:set var="val8" value="${table8}" />
        <c:choose>
            <c:when test="${val8 == '0'}">

                <table>
                    <tr>
                        <td class="hiddenTableLeft tableLeft tableTop cellBottomMargin">1</td>
                    </tr>
                </table>

            </c:when>
            <c:otherwise>    
    

               <table id="table8" class="tableLeft">
                    <tr>
                        <td style="background-color:${label8colcode};">${label8location}</td>
                        <td class="logoBar" rowspan="4"><img class="logoImage" src="${logoLink}">
                            <div class="textLabel">${textLabel}</div></td>
                        <td class="emptyCell20mm" rowspan="8"></td>
                    </tr>
                    <tr>
                        <td style="background-color:${label8colour1};">${fn:substring(label8callno1,0,1)}</td>
                    </tr>
                    <tr>
                        <td style="background-color:${label8colour2};">${fn:substring(label8callno1,1,2)}</td>
                    </tr>
                    <tr>
                        <td style="background-color:${label8colour3};">${fn:substring(label8callno1,2,3)}</td>
                    </tr>
                    <tr class='cutterRow'>
                        <c:choose>
                            <c:when test="${checking8 == 'on'}">
                                <td style="background-color:${label8colour4};">${fn:substring(label8callno1,3,4)}</td>
                            </c:when>
                            <c:otherwise>
                                <td></td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>

                        <td class='narrow' style="background-color:${label8colour5};">${label8itemclass}</td>
						<td class='wide' style="background-color:${label8colour5};border-top: 0.1mm solid; height: 35px;">${fn:substring(label8callno1,3,4)}</td>

                        <td class="logoBar" rowspan="2">
                            <svg class="barcodeImage barcode" jsbarcode-format="code39" jsbarcode-value= ${accessionNo8} 
                            	jsbarcode-width="1" jsbarcode-height="23" jsbarcode-margin="6"  
                            	jsbarcode-font="arial" jsbarcode-fontSize="11" >
                            </svg>                         
                        </td>
                    </tr>
                     <tr>
                        <td class="noBorder">${label8AuthMark}<br><span <c:if test="${label8CallNo == '.'}">style="color: white;"</c:if>>${label8CallNo}</span></td>
                    </tr>
 
                </table>
                            </c:otherwise>
        </c:choose>

       <!-- TABLE 9  -->
        <c:set var="val9" value="${table9}" />
        <c:choose>
            <c:when test="${val9 == '0'}">

                <table>
                    <tr>
                        <td class="hiddenTableLeft tableLeft tableTop cellBottomMargin">1</td>
                    </tr>
                </table>

            </c:when>
            <c:otherwise>    
    

               <table id="table9" class="tableLeft">
                    <tr>
                        <td style="background-color:${label9colcode};">${label9location}</td>
                        <td class="logoBar" rowspan="4"><img class="logoImage" src="${logoLink}">
                            <div class="textLabel">${textLabel}</div></td>
                        <td class="emptyCell20mm" rowspan="8"></td>
                    </tr>
                    <tr>
                        <td style="background-color:${label9colour1};">${fn:substring(label9callno1,0,1)}</td>
                    </tr>
                    <tr>
                        <td style="background-color:${label9colour2};">${fn:substring(label9callno1,1,2)}</td>
                    </tr>
                    <tr>
                        <td style="background-color:${label9colour3};">${fn:substring(label9callno1,2,3)}</td>
                    </tr>
                    <tr class='cutterRow'>
                        <c:choose>
                            <c:when test="${checking9 == 'on'}">
                                <td style="background-color:${label9colour4};">${fn:substring(label9callno1,3,4)}</td>
                            </c:when>
                            <c:otherwise>
                                <td></td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>

                        <td class='narrow'style="background-color:${label9colour5};">${label9itemclass}</td>

                        <td class='wide' style="background-color:${label9colour5};border-top: 0.1mm solid; height: 35px;">${fn:substring(label9callno1,3,4)}</td>

                        <td class="logoBar" rowspan="3">
                            <svg class="barcodeImage barcode" jsbarcode-format="code39" jsbarcode-value= ${accessionNo9} 
                            	jsbarcode-width="1" jsbarcode-height="23" jsbarcode-margin="6"  
                            	jsbarcode-font="arial" jsbarcode-fontSize="11" >
                            </svg>                         
                        </td>
                    </tr>
                    <tr>
                        <td class="noBorder" rowspan="2">${label9AuthMark}<br><span <c:if test="${label9CallNo == '.'}">style="color: white;"</c:if>>${label9CallNo}</span></td>
                    </tr>

                </table>
                            </c:otherwise>
        </c:choose>

       <!-- TABLE 10  -->
        <c:set var="val10" value="${table10}" />
        <c:choose>
            <c:when test="${val10 == '0'}">

                <table>
                    <tr>
                        <td class="hiddenTableLeft tableLeft tableTop cellBottomMargin">1</td>
                    </tr>
                </table>

            </c:when>
            <c:otherwise>    
    

               <table id="table10" class="tableLeft">
                    <tr>
                        <td style="background-color:${label10colcode};">${label10location}</td>
                        <td class="logoBar" rowspan="4"><img class="logoImage" src="${logoLink}">
                            <div class="textLabel">${textLabel}</div></td>
                        <td class="emptyCell20mm" rowspan="8"></td>
                    </tr>
                    <tr>
                        <td style="background-color:${label10colour1};">${fn:substring(label10callno1,0,1)}</td>
                    </tr>
                    <tr>
                        <td style="background-color:${label10colour2};">${fn:substring(label10callno1,1,2)}</td>
                    </tr>
                    <tr>
                        <td style="background-color:${label10colour3};">${fn:substring(label10callno1,2,3)}</td>
                    </tr>
                    <tr class='cutterRow'>
                        <c:choose>
                            <c:when test="${checking10 == 'on'}">
                                <td style="background-color:${label10colour4};">${fn:substring(label10callno1,3,4)}</td>
                            </c:when>
                            <c:otherwise>
                                <td></td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>
 
                        <td class='narrow' style="background-color:${label10colour5};">${label10itemclass}</td>

                        <td class='wide' style="background-color:${label10colour5};border-top: 0.1mm solid; height: 35px;">${fn:substring(label10callno1,3,4)}</td>

                        <td class="logoBar" rowspan="2">
                            <svg class="barcodeImage barcode" jsbarcode-format="code39" jsbarcode-value= ${accessionNo10} 
                            	jsbarcode-width="1" jsbarcode-height="23" jsbarcode-margin="6"  
                            	jsbarcode-font="arial" jsbarcode-fontSize="11" >
                            </svg>                         
                        </td>
                    </tr>
                    <tr>
                        <td class="noBorder" rowspan="2">${label10AuthMark}<br><span <c:if test="${label10CallNo == '.'}">style="color: white;"</c:if>>${label10CallNo}</span></td>
                    </tr>

                </table>
                            </c:otherwise>
        </c:choose>       

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jsbarcode/3.11.0/JsBarcode.all.min.js"></script>
        <script>JsBarcode(".barcode").init();</script>
<!--         <script>JsBarcode("#barcode1", <fmt:formatNumber pattern="00" value="${accesionNo1}" />, {format: "code39",width:1, height:22, displayValue: false, marginBottom: 10 });</script> -->

        <!-- KAA@2019 -->
    </page>
</body>

                     	<script>
                     	$(document).ready(function(){
                     		
                     		$('table[id^=table] > tbody > tr > td').each(function(index, td) {
   
                     			var table = $(td).parent().parent().parent().attr("id");

							let digitOrAlphabert;
							if ($(td).attr('class') === 'noBorder') {
								digitOrAlphabert = $(td).html();
								
                     			let onlyLetters = /^[a-zA-Z]+$/;
                     		
                     			if (onlyLetters.test(digitOrAlphabert.charAt(0))) {
 							
 									$('#'+table+' .cutterRow').hide();
							    	$('#'+table+' .narrow').hide();
 									$('#'+table+' .wide').show();

								 }else{

									    $('#'+table+' .cutterRow').show();
									    $('#'+table+' .narrow').show();
									    $('#'+table+' .wide').hide();
								 }
                     			 
								
							}
						 

                     		});
                     	
                     	});

						</script>
						
						

</html>