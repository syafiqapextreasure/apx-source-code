<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/global.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/general/Search_Modal.js"></script>	
					

<!-- <script type="text/javascript" src="/js/general/global.js"></script>	
<script type="text/javascript" src="/js/general/Search_Modal.js"></script>	 -->

<style>
#officerSearch{
	z-index: 1080 !important;
}

body {
  padding-top: 50px;
}
legend a {
  color: inherit;
}
legend.legendStyle {
padding-left: 5px;
padding-right: 5px;
}
fieldset.fsStyle {
font-weight: normal;
border: 1px solid #999999;
padding: 4px 0 4px 0;
margin: 5px 0 5px 0;
}
legend.legendStyle {
font-size: 14px;
}

legend {
width: auto;
border-bottom: 0px;
}


</style>


	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">Catalog Search_1</h4>
	</div>
	<div class="modal-body">
	
		<div class="panel-group" id="form_parent">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#form_parent" href="#search_title" id="search-title">
								Search
								<i class="indicator glyphicon glyphicon-chevron-down  pull-right"></i>
							</a>
						</h4>
					</div>
					<div  class="panel-collapse collapse in" id="search_title">
						<div class="panel-body">
							<form role="form" class="form-horizontal" id="retrieveBO">
							<div class="form-group">
									<div class="col-sm-3 col-md-3">
										<label>Search For</label>
									</div>
									
									
									<div class="col-sm-6 col-md-6">
										<input type="text" class="form-control criteria" name="criteria" id="criteria" placeholder="Enter Search Term"onkeypress="getkeyURL(event)">

									</div>
								</div>
							
								
								<div class="form-group">
									<div class="col-sm-3 col-md-3">
										<label>
											<input name="type" id="type" value="type" type="checkbox"> 
											Type
										</label> 
									</div>
									<div class="col-sm-4 col-md-4">
									<select class="form-control tags" id="type" name="type">
										
										<option value="C" id="type" >Cataloging</option>
	
										
										<option value="I" id="type" >IRS</option>
	
										
										<option value="A" id="type" >Acquisition</option>
	
										
										<option value="S" id="type" >Serial</option>
	
										
										<option value="E" id="type" >E-Book</option>
	
										
									</select>
								</div>
								</div>
								
								<div class="clearfix"></div>
								
								<div class="clearfix"></div>
								<div class="form-group">
									<div class="col-sm-3 col-md-3">
										<label>
											<input name="searchSelection" id="selection" value="selection" type="radio" onclick="radio()"> 
											Selection
										</label> 
									</div>
									<div class="col-sm-4 col-md-4">
										<select class="form-control title" id="search_type" name="search_type">
											<option value="title">Title</option>
											<option value="name">Name</option>
											<option value="subject">Subject</option>
											<option value="publisher">Publisher</option>
											<option value="placeOfPublication">Place of Publication</option>
											<option value="yearOfPublication">Year of Publication</option>
											<option value="series">Series</option>
											<option value="callNo">Call-No.</option>
											<option value="isbn">ISBN</option>
											<option value="issn">ISSN</option>
											<option value="notesArea">Notes Area</option>
										</select>
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="form-group">		
									<div class="col-sm-3 col-md-3">
										<label>
											<input name="searchSelection" id="tag" value="tag" type="radio" onclick="radio()"> 
											In Tag
										</label>
									</div>
									<div class="col-sm-4 col-md-4">
										
									<select class="form-control tags" id="select_tags" name="tags" disabled>
										<option value="0">Please select</option>
										
										<option value="000" data-id="003ggg">000-LEADER</option>

										
										<option value="001" data-id="007">001-Test</option>

										
										<option value="002" data-id="007">002-CONTROL NUMBER USER-DEFINED</option>

										
										<option value="003" data-id="007">003-CONTROL NUMBER IDENTIFIER</option>

										
										<option value="004" data-id="null">004-BIB-NO</option>

										
										<option value="005" data-id="007">005-DATE AND TIME OF LATEST TRANSACTION</option>

										
										<option value="006" data-id="007">006-ADDITIONAL MATERIAL CHARACTERISTICS</option>

										
										<option value="007" data-id="007">007-PHYSICAL DESCRIPTION FIXED FIELD--GENERAL INFORMATION</option>

										
										<option value="008" data-id="007">008-PRINT MEDIA: INFORMATION CODES</option>

										
										<option value="009" data-id="007">009-AUDIOVISUAL MATERIAL: INFORMATION CODES</option>

										
										<option value="010" data-id="007">010-LIBRARY OF CONGRESS CONTROL NUMBER</option>

										
										<option value="013" data-id="007">013-PATENT CONTROL INFORMATION</option>

										
										<option value="015" data-id="007">015-NATIONAL BIBLIOGRAPHY NUMBER</option>

										
										<option value="016" data-id="007">016-NATIONAL BIBLIOGRAPHIC AGENCY CONTROL NUMBER</option>

										
										<option value="017" data-id="007">017-COPYRIGHT OR LEGAL DEPOSIT NUMBER</option>

										
										<option value="018" data-id="007">018-COPYRIGHT ARTICLE-FEE CODE</option>

										
										<option value="020" data-id="007">020-null</option>

										
										<option value="022" data-id="007">022-ISSN</option>

										
										<option value="024" data-id="007">024-OTHER STANDARD IDENTIFIER</option>

										
										<option value="025" data-id="007">025-OVERSEAS ACQUISITION NUMBER</option>

										
										<option value="026" data-id="007">026-FINGERPRINT IDENTIFIER</option>

										
										<option value="027" data-id="007">027-STANDARD TECHNICAL REPORT NUMBER</option>

										
										<option value="028" data-id="007">028-PUBLISHER NUMBER</option>

										
										<option value="030" data-id="007">030-CODEN (SERIALS)</option>

										
										<option value="031" data-id="007">031-MUSICAL INCIPITS INFORMATION</option>

										
										<option value="032" data-id="007">032-POSTAL REGISTRATION NUMBER</option>

										
										<option value="033" data-id="007">033-DATE/TIME AND PLACE OF AN EVENT</option>

										
										<option value="034" data-id="007">034-CODED CARTOGRAPHIC MATHEMATICAL DATA</option>

										
										<option value="035" data-id="007">035-SYSTEM CONTROL NUMBER</option>

										
										<option value="036" data-id="007">036-ORIGINAL STUDY NUMBER FOR COMPUTER DATA FILES</option>

										
										<option value="037" data-id="007">037-SOURCE OF ACQUISITION</option>

										
										<option value="038" data-id="007">038-RECORD CONTENT LICENSOR</option>

										
										<option value="040" data-id="007">040-CATALOGING SOURCE</option>

										
										<option value="041" data-id="007">041-LANGUAGE</option>

										
										<option value="042" data-id="007">042-AUTHENTICATION CODE</option>

										
										<option value="043" data-id="007">043-GEOGRAPHIC AREA CODE</option>

										
										<option value="044" data-id="007">044-COUNTRY OF PUBLISHING/PRODUCING ENTITY CODE</option>

										
										<option value="045" data-id="007">045-TIME PERIOD OF CONTENT</option>

										
										<option value="046" data-id="007">046-SPECIAL CODED DATES</option>

										
										<option value="047" data-id="007">047-FORM OF MUSICAL COMPOSITION CODE</option>

										
										<option value="048" data-id="007">048-NUMBER OF MUSICAL INSTRUMENTS OR VOICES CODE</option>

										
										<option value="049" data-id="007">049-SPECIAL COLLECTION CODE</option>

										
										<option value="050" data-id="007">050-LIBRARY OF CONGRESS CALL NUMBER</option>

										
										<option value="051" data-id="007">051-LIBRARY OF CONGRESS COPY, ISSUE, OFFPRINT STATEMENT</option>

										
										<option value="052" data-id="006">052-GEOGRAPHIC CLASSIFICATION</option>

										
										<option value="055" data-id="006">055-CLASSIFICATION NUMBERS ASSIGNED IN CANADA</option>

										
										<option value="060" data-id="006">060-NAT. LIB. OF MEDICINE CALL NUMBER</option>

										
										<option value="061" data-id="007">061-NATIONAL LIBRARY OF MEDICINE COPY STATEMENT</option>

										
										<option value="066" data-id="007">066-CHARACTER SETS PRESENT</option>

										
										<option value="070" data-id="006">070-NATIONAL AGRICULTURAL LIBRARY CALL NUMBER</option>

										
										<option value="071" data-id="007">071-NATIONAL AGRICULTURAL LIBRARY COPY STATEMENT</option>

										
										<option value="072" data-id="007">072-SUBJECT CATEGORY CODE</option>

										
										<option value="074" data-id="007">074-GPO ITEM NUMBER</option>

										
										<option value="080" data-id="006">080-UNIVERSAL DECIMAL CLASSIFICATION NUMBER</option>

										
										<option value="082" data-id="007">082-DEWEY DECIMAL CLASSIFICATION NUMBER</option>

										
										<option value="083" data-id="007">083-ADDITIONAL DEWEY DECIMAL CLASSIFICATION NUMBER</option>

										
										<option value="084" data-id="007">084-OTHER CLASSIFICATION NUMBER</option>

										
										<option value="085" data-id="007">085-SYNTHESIZED CLASSIFICATION NUMBER COMPONENTS </option>

										
										<option value="086" data-id="006">086-GOVERNMENT DOCUMENT CLASSIFICATION NUMBER</option>

										
										<option value="088" data-id="007">088-REPORT NUMBER</option>

										
										<option value="090" data-id="006">090-LOCAL CALL NUMBER</option>

										
										<option value="091" data-id="006">091-CALL NUMBER (AUDIOVISUAL)</option>

										
										<option value="092" data-id="006">092-CALL NUMBER (SLIDES)</option>

										
										<option value="093" data-id="006">093-LOCAL FEATURE HEADING</option>

										
										<option value="096" data-id="006">096-LOCAL NLM CALL NUMBER</option>

										
										<option value="099" data-id="null">099-TAG 099</option>

										
										<option value="100" data-id="002">100-AUTHOR</option>

										
										<option value="110" data-id="002">110-MAIN ENTRY - CORPORATE NAME</option>

										
										<option value="111" data-id="002">111-MAIN ENTRY - MEETING NAME</option>

										
										<option value="130" data-id="002">130-MAIN ENTRY - UNIFORM TITLE</option>

										
										<option value="150" data-id="null">150-TAG 150</option>

										
										<option value="151" data-id="null">151-TAG 151</option>

										
										<option value="160" data-id="null">160-TAG 160</option>

										
										<option value="172" data-id="null">172-TAG 172</option>

										
										<option value="202" data-id="null">202-TAG 202</option>

										
										<option value="210" data-id="001">210-ABBREVIATED TITLE</option>

										
										<option value="222" data-id="001">222-KEY TITLE</option>

										
										<option value="235" data-id="null">235-ARTICLE TITLE </option>

										
										<option value="240" data-id="001">240-UNIFORM TITLE</option>

										
										<option value="241" data-id="null">241-ROMANIZED TITLE</option>

										
										<option value="242" data-id="001">242-TRANSLATION OF TITLE BY CATALOGING AGENCY</option>

										
										<option value="243" data-id="001">243-COLLECTIVE UNIFORM TITLE</option>

										
										<option value="245" data-id="001">245-TITLE</option>

										
										<option value="246" data-id="001">246-VARYING FORM OF TITLE</option>

										
										<option value="247" data-id="001">247-FORMER TITLE</option>

										
										<option value="248" data-id="null">248-MULTI LEVEL DESCRIPTION</option>

										
										<option value="250" data-id="007">250-EDITION</option>

										
										<option value="251" data-id="null">251-CTVTLS TAG</option>

										
										<option value="254" data-id="001">254-MUSICAL PRESENTATION STATEMENT</option>

										
										<option value="255" data-id="001">255-CARTOGRAPHIC MATHEMATICAL DATA</option>

										
										<option value="256" data-id="001">256-COMPUTER FILE CHARACTERISTICS</option>

										
										<option value="257" data-id="007">257-COUNTRY OF PRODUCING ENTITY FOR ARCHIVAL FILMS</option>

										
										<option value="258" data-id="007">258-PHILATELIC ISSUE DATA</option>

										
										<option value="260" data-id="004">260-IMPRINT</option>

										
										<option value="263" data-id="007">263-PROJECTED PUBLICATION DATE</option>

										
										<option value="264" data-id="007">264-PRODUCTION, PUBLICATION, DISTRIBUTION, MANUFACTURE, AND COPYRIGHT NOTICE</option>

										
										<option value="265" data-id="null">265-CTVTLS TAG</option>

										
										<option value="270" data-id="007">270-ADDRESS</option>

										
										<option value="300" data-id="007">300-PHYSICAL DESCRIPTION</option>

										
										<option value="301" data-id="null">301-CTVTLS TAG</option>

										
										<option value="306" data-id="007">306-PLAYING TIME</option>

										
										<option value="307" data-id="007">307-HOURS, ETC.</option>

										
										<option value="310" data-id="007">310-CURRENT PUBLICATION FREQUENCY</option>

										
										<option value="311" data-id="null">311-TAG 311</option>

										
										<option value="321" data-id="007">321-FORMER PUBLICATION FREQUENCY</option>

										
										<option value="336" data-id="007">336-CONTENT TYPE </option>

										
										<option value="337" data-id="007">337-MEDIA TYPE</option>

										
										<option value="338" data-id="007">338-CARRIER TYPE</option>

										
										<option value="340" data-id="007">340-PHYSICAL MEDIUM</option>

										
										<option value="342" data-id="007">342-GEOSPATIAL REFERENCE DATA</option>

										
										<option value="343" data-id="007">343-PLANAR COORDINATE DATA</option>

										
										<option value="344" data-id="007">344-SOUND CHARACTERISTICS</option>

										
										<option value="345" data-id="007">345-PROJECTION CHARACTERISTICS OF MOVING IMAGE </option>

										
										<option value="346" data-id="007">346-VIDEO CHARACTERISTICS </option>

										
										<option value="347" data-id="007">347-DIGITAL FILE CHARACTERISTICS</option>

										
										<option value="350" data-id="null">350-TERMS OF AVAILABILITY</option>

										
										<option value="351" data-id="007">351-ORGANIZATION AND ARRANGEMENT OF MATERIALS</option>

										
										<option value="352" data-id="007">352-DIGITAL GRAPHIC REPRESENTATION</option>

										
										<option value="355" data-id="007">355-SECURITY CLASSIFICATION CONTROL</option>

										
										<option value="357" data-id="007">357-ORIGINATOR DISSEMINATION CONTROL</option>

										
										<option value="360" data-id="null">360-TAG 360</option>

										
										<option value="362" data-id="007">362-DATES OF PUBLICATION AND/OR SEQUENTIAL DESIGNATION</option>

										
										<option value="363" data-id="007">363-NORMALIZED DATE AND SEQUENTIAL DESIGNATION</option>

										
										<option value="365" data-id="007">365-TRADE PRICE</option>

										
										<option value="366" data-id="007">366-TRADE AVAILABILITY INFORMATION</option>

										
										<option value="377" data-id="007">377-ASSOCIATED LANGUAGE </option>

										
										<option value="380" data-id="007">380-FORM OF WORK</option>

										
										<option value="381" data-id="007">381-OTHER DISTINGUISHING CHARACTERISTICS OF WORK OR EXPRESSION</option>

										
										<option value="382" data-id="007">382-MEDIUM OF PERFORMANCE </option>

										
										<option value="383" data-id="007">383-NUMERIC DESIGNATION OF MUSICAL WORK</option>

										
										<option value="384" data-id="007">384-KEY</option>

										
										<option value="385" data-id="007">385-AUDIENCE CHARACTERISTICS</option>

										
										<option value="386" data-id="007">386-CREATOR/CONTRIBUTOR CHARACTERISTICS</option>

										
										<option value="400" data-id="null">400-TAG 400</option>

										
										<option value="403" data-id="null">403-CTVTLS TAG</option>

										
										<option value="404" data-id="null">404-TAG 404</option>

										
										<option value="405" data-id="null">405-TAG 405</option>

										
										<option value="410" data-id="null">410-TAG 410</option>

										
										<option value="440" data-id="005">440-SERIES STATEMENT/ADDED ENTRY - TITLE</option>

										
										<option value="450" data-id="null">450-TAG 450</option>

										
										<option value="451" data-id="null">451-TAG 451</option>

										
										<option value="490" data-id="005">490-SERIES STATEMENT (NO ADDED ENTRY)</option>

										
										<option value="500" data-id="007">500-NOTES</option>

										
										<option value="501" data-id="007">501-WITH NOTE</option>

										
										<option value="502" data-id="007">502-DISSERTATION NOTE</option>

										
										<option value="503" data-id="null">503-CTVTLS TAG</option>

										
										<option value="504" data-id="007">504-BIBLIOGRAPHY</option>

										
										<option value="505" data-id="007">505-CONTENTS</option>

										
										<option value="506" data-id="007">506-RESTRICTIONS ON ACCESS NOTE</option>

										
										<option value="507" data-id="007">507-SCALE NOTE FOR GRAPHIC MATERIAL</option>

										
										<option value="508" data-id="007">508-CREATION/PRODUCTION CREDITS NOTE</option>

										
										<option value="510" data-id="007">510-CITATION/REFERENCES NOTE</option>

										
										<option value="511" data-id="007">511-PARTICIPANT OR PERFORMER NOTE</option>

										
										<option value="513" data-id="007">513-TYPE OF REPORT AND PERIOD COVERED NOTE</option>

										
										<option value="514" data-id="007">514-DATA QUALITY NOTE</option>

										
										<option value="515" data-id="007">515-NUMBERING PECULIARITIES NOTE</option>

										
										<option value="516" data-id="007">516-TYPE OF COMPUTER FILE OR DATA NOTE</option>

										
										<option value="518" data-id="007">518-DATE/TIME AND PLACE OF AN EVENT NOTE</option>

										
										<option value="520" data-id="007">520-SUMMARY NOTE</option>

										
										<option value="521" data-id="007">521-TARGET AUDIENCE NOTE</option>

										
										<option value="522" data-id="007">522-GEOGRAPHIC COVERAGE NOTE</option>

										
										<option value="524" data-id="007">524-PREFERRED CITATION OF DESCRIBED MATERIALS NOTE</option>

										
										<option value="525" data-id="007">525-SUPPLEMENT NOTE</option>

										
										<option value="526" data-id="007">526-STUDY PROGRAM INFORMATION NOTE</option>

										
										<option value="530" data-id="007">530-ADDITIONAL PHYSICAL FORM AVAILABLE NOTE</option>

										
										<option value="533" data-id="007">533-REPRODUCTION NOTE</option>

										
										<option value="534" data-id="007">534-ORIGINAL VERSION NOTE</option>

										
										<option value="535" data-id="007">535-LOCATION OF ORIGINALS/DUPLICATES NOTE</option>

										
										<option value="536" data-id="007">536-FUNDING INFORMATION NOTE</option>

										
										<option value="538" data-id="007">538-SYSTEM DETAILS NOTE</option>

										
										<option value="540" data-id="007">540-TERMS GOVERNING USE AND REPRODUCTION NOTE</option>

										
										<option value="541" data-id="007">541-IMMEDIATE SOURCE OF ACQUISITION NOTE</option>

										
										<option value="542" data-id="007">542-INFORMATION RELATING TO COPYRIGHT STATUS</option>

										
										<option value="544" data-id="007">544-LOCATION OF OTHER ARCHIVAL MATERIALS NOTE</option>

										
										<option value="545" data-id="007">545-BIOGRAPHICAL OR HISTORICAL DATA</option>

										
										<option value="546" data-id="007">546-LANGUAGE NOTE</option>

										
										<option value="547" data-id="007">547-FORMER TITLE COMPLEXITY NOTE</option>

										
										<option value="550" data-id="007">550-ISSUING BODY NOTE</option>

										
										<option value="551" data-id="null">551-TAG 551</option>

										
										<option value="552" data-id="007">552-ENTITY AND ATTRIBUTE INFORMATION NOTE</option>

										
										<option value="555" data-id="007">555-CUMULATIVE INDEX/FINDING AIDS NOTE</option>

										
										<option value="556" data-id="007">556-INFORMATION ABOUT DOCUMENTATION NOTE</option>

										
										<option value="561" data-id="007">561-OWNERSHIP AND CUSTODIAL HISTORY</option>

										
										<option value="562" data-id="007">562-COPY AND VERSION IDENTIFICATION NOTE</option>

										
										<option value="563" data-id="007">563-BINDING INFORMATION</option>

										
										<option value="565" data-id="007">565-CASE FILE CHARACTERISTICS NOTE</option>

										
										<option value="567" data-id="007">567-METHODOLOGY NOTE</option>

										
										<option value="570" data-id="null">570-CTVTLS TAG</option>

										
										<option value="580" data-id="007">580-LINKING ENTRY COMPLEXITY NOTE</option>

										
										<option value="581" data-id="007">581-PUBLICATIONS ABOUT DESCRIBED MATERIALS NOTE</option>

										
										<option value="583" data-id="007">583-ACTION NOTE</option>

										
										<option value="584" data-id="007">584-ACCUMULATION AND FREQUENCY OF USE NOTE</option>

										
										<option value="585" data-id="007">585-EXHIBITIONS NOTE</option>

										
										<option value="586" data-id="007">586-AWARDS NOTE</option>

										
										<option value="588" data-id="007">588-SOURCE OF DESCRIPTION NOTE</option>

										
										<option value="600" data-id="003">600-SUBJECT - PERSONAL NAME</option>

										
										<option value="601" data-id="null">601-DESCRIPTOR</option>

										
										<option value="605" data-id="null">605-TAG 605</option>

										
										<option value="610" data-id="003">610-SUBJECT - CORPORATE NAME</option>

										
										<option value="611" data-id="003">611-SUBJECT ADDED ENTRY - MEETING NAME</option>

										
										<option value="630" data-id="003">630-SUBJECT - UNIFORM TITLE</option>

										
										<option value="640" data-id="null">640-SUBJECT - UNIFORM TITLE</option>

										
										<option value="645" data-id="null">645-TITLE SUBJECT HEADING</option>

										
										<option value="648" data-id="003">648-SUBJECT ADDED ENTRY - CHRONOLOGICAL TERM</option>

										
										<option value="650" data-id="003">650-SUBJECT - TOPICAL</option>

										
										<option value="651" data-id="003">651-SUBJECT - GEOGRAPHIC NAME</option>

										
										<option value="653" data-id="003">653-INDEX TERM - UNCONTROLLED</option>

										
										<option value="654" data-id="003">654-SUBJECT ADDED ENTRY - FACETED TOPICAL TERMS</option>

										
										<option value="655" data-id="003">655-INDEX TERM - GENRE/FORM</option>

										
										<option value="656" data-id="003">656-INDEX TERM - OCCUPATION</option>

										
										<option value="657" data-id="003">657-INDEX TERM - FUNCTION</option>

										
										<option value="658" data-id="003">658-INDEX TERM - CURRICULUM OBJECTIVE</option>

										
										<option value="660" data-id="null">660-CHAIN INDEX</option>

										
										<option value="662" data-id="007">662-SUBJECT ADDED ENTRY - HIERARCHICAL PLACE NAME </option>

										
										<option value="665" data-id="null">665-TAG 665</option>

										
										<option value="680" data-id="null">680-TAG 680</option>

										
										<option value="700" data-id="002">700-OTHER AUTHOR</option>

										
										<option value="710" data-id="002">710-ADDED ENTRY - CORPORATE NAME</option>

										
										<option value="711" data-id="002">711-ADDED ENTRY - MEETING NAME</option>

										
										<option value="720" data-id="002">720-ADDED ENTRY - UNCONTROLLED NAME</option>

										
										<option value="730" data-id="001">730-ADDED ENTRY - UNIFORM TITLE</option>

										
										<option value="740" data-id="001">740-ADDED ENTRY - TITLE</option>

										
										<option value="745" data-id="null">745-ADDED ENTRY - TITLE</option>

										
										<option value="751" data-id="007">751-ADDED ENTRY - GEOGRAPHIC NAME </option>

										
										<option value="752" data-id="007">752-ADDED ENTRY - HIERARCHICAL PLACE NAME</option>

										
										<option value="753" data-id="007">753-SYSTEM DETAILS ACCESS TO COMPUTER FILES</option>

										
										<option value="754" data-id="007">754-ADDED ENTRY - TAXONOMIC IDENTIFICATION</option>

										
										<option value="760" data-id="007">760-MAIN SERIES ENTRY</option>

										
										<option value="762" data-id="007">762-SUBSERIES ENTRY</option>

										
										<option value="765" data-id="007">765-ORIGINAL LANGUAGE ENTRY</option>

										
										<option value="767" data-id="007">767-TRANSLATION ENTRY</option>

										
										<option value="770" data-id="007">770-SUPPLEMENT/SPECIAL ISSUE ENTRY</option>

										
										<option value="772" data-id="007">772-SUPPLEMENT PARENT ENTRY</option>

										
										<option value="773" data-id="007">773-HOST ITEM ENTRY</option>

										
										<option value="774" data-id="007">774-CONSTITUENT UNIT ENTRY</option>

										
										<option value="775" data-id="007">775-OTHER EDITION ENTRY</option>

										
										<option value="776" data-id="007">776-ADDITIONAL PHYSICAL FORM ENTRY</option>

										
										<option value="777" data-id="007">777-ISSUED WITH ENTRY</option>

										
										<option value="780" data-id="005">780-PRECEDING ENTRY</option>

										
										<option value="785" data-id="005">785-SUCCEEDING ENTRY</option>

										
										<option value="786" data-id="007">786-DATA SOURCE ENTRY</option>

										
										<option value="787" data-id="007">787-NONSPECIFIC RELATIONSHIP ENTRY</option>

										
										<option value="800" data-id="002">800-SERIES ADDED ENTRY - PERSONAL NAME</option>

										
										<option value="810" data-id="002">810-SERIES ADDED ENTRY - CORPORATE NAME</option>

										
										<option value="811" data-id="002">811-SERIES ADDED ENTRY - MEETING NAME</option>

										
										<option value="830" data-id="007">830-SERIES ADDED ENTRY - UNIFORM TITLE</option>

										
										<option value="840" data-id="null">840-SERIES ADDED ENTRY - TITLE TRACED DIFFERENTLY</option>

										
										<option value="843" data-id="null">843-TAG 843</option>

										
										<option value="850" data-id="005">850-HOLDING INSTITUTION</option>

										
										<option value="851" data-id="null">851-HOLDINGS INFORMATION(SERIALS)</option>

										
										<option value="852" data-id="007">852-LOCATION</option>

										
										<option value="853" data-id="null">853-INDEX</option>

										
										<option value="856" data-id="007">856-URL</option>

										
										<option value="863" data-id="null">863-TAG 863</option>

										
										<option value="864" data-id="null">864-TAG 864</option>

										
										<option value="865" data-id="null">865-TAG 865</option>

										
										<option value="866" data-id="null">866-TAG 866</option>

										
										<option value="880" data-id="007">880-ALTERNATE GRAPHIC REPRESENTATION</option>

										
										<option value="882" data-id="007">882-REPLACEMENT RECORD INFORMATION</option>

										
										<option value="883" data-id="007">883-MACHINE - GENERATED METADATA PROVENANCE</option>

										
										<option value="886" data-id="007">886-FOREIGN MARC INFORMATION FIELD</option>

										
										<option value="887" data-id="007">887-NON-MARC INFORMATION FIELD</option>

										
										<option value="888" data-id="">888-test</option>

										
										<option value="889" data-id="null">889-test33</option>

										
										<option value="900" data-id="null">900-Universal Resource Locator (URL)</option>

										
										<option value="901" data-id="null">901-Universal Resource Locator (URL)</option>

										
										<option value="905" data-id="null">905-CATALOG METHODS</option>

										
										<option value="907" data-id="null">907-CTVTLS TAG</option>

										
										<option value="920" data-id="null">920-CTVTLS TAG</option>

										
										<option value="949" data-id="null">949-TAG 949</option>

										
										<option value="998" data-id="007">998-INSTITUTIONAL CODE</option>

										
										<option value="999" data-id="NULL">999-ACCESSION NUMBER</option>

										
										</select>
									</div>
								</div>
								<div class="clearfix"></div>
								<div id="index">
								<div class="form-group">		
									<div class="col-sm-4 col-md-4">
										<label>
											<input name="searchSelection" id="ctrlNo" value="ctrlNo" type="radio" onclick="radio()">
											Control Number 
										</label>
									</div>
								</div>
								</div>
								<div id="buffer" style="display:none">
									<div class="form-group">		
									<div class="col-sm-4 col-md-4">
										<label>
											<input name="searchSelection" id="bufferNo" value="bufferNo" type="radio" onclick="radio()">
											Buffer Number: 
										</label>
									</div>
								</div>
								</div>
								<div class="clearfix"></div>
									<div class="cold-md-12">
											<fieldset class="fsStyle">													
												<legend class="legendStyle">
													<label>
									                  <input name="searchSelection" id="OfficerID" value="officerID" type="radio" onclick="radio()">
													 	as Officer ID
													 </label>
												</legend>
												<div class="row collapse in" id="demo">
													<div class="form-group">
														<label class="col-sm-3 control-label">&nbsp;</label>
														<div class="col-sm-2 col-md-2">
															<select class="form-control officerID" id="officerValue" name="search_type" onchange="updatePlaceholder()" disabled>
																<option value="creator">Creator</option>
																<option value="modifier">Modifier</option>
															</select>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label">Date Range:</label>
														<div class="col-sm-6">
															<div class="input-daterange input-group date" id="orderDate">
																<input type="text" class="form-control" readonly id="startDateInput"/>
																<span class="input-group-addon">to</span>
																<input type="text" class="form-control" readonly id="stopDateInput"/>
															</div>
														</div>
													</div>
									          	</div>
									  		</fieldset>
									</div>
								<!-- 	<div class="form-group">
										<div class="col-sm-12 col-md-12">
										<fieldset style="border: 1px solid #999999;padding:4px 0 4px 0;">
											<legend class="legendStyle" style="font-size: 90%;">
						                      <input name="searchSelection" id="OfficerID" value="OfficerID" type="radio" onclick="radio()">
						                      as Officer ID
											</legend>
											<div class="form-group">
												<div class="col-sm-2 col-md-2">
													<select class="form-control officerID" id="search_type" name="search_type" onchange="updatePlaceholder()">
														<option value="creator">Creator</option>
														<option value="modifier">Modifier</option>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label">Date Range:</label>
												<div class="col-sm-6">
													<div class="input-daterange input-group date" id="orderDate">
														<input type="text" class="form-control" readonly id="startDateInput"/>
														<span class="input-group-addon">to</span>
														<input type="text" class="form-control" readonly id="stopDateInput"/>
													</div>
												</div>
											</div>
											<div class="col-sm-2 col-md-2">
												<button type="button" class="btn btn-success officerID" id="btn_add" data-toggle="modal" 
												data-target="#officerSearch" href="Modal_OfficerID">...</button>
											</div>
											</fieldset>
										</div>
									</div>
 -->
								<div class="form-group">
									<div class="col-sm-4 col-md-4"></div>
									<div class="col-sm-8 col-md-8">
								
									
										<button type="button" class="btn btn-info" id="btn-submit-retrievemodal1" data-action="8" onclick="getURL(this);return send_title_info()">
											<span class="glyphicon glyphicon-search"></span> Search
										</button>
									
									<!-- 	<input type="button" name="cancel" value="Cancel" class="btn btn-info" data-dismiss="modal"/> -->
									 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									</div>
								</div>
							</form>
						</div>
					</div>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#form_parent" href="#result_title" id="result-title">
							Result
							<i class="indicator glyphicon glyphicon-chevron-up pull-right"></i>
						</a>
					</h4>
				</div>
				<div  class="panel-collapse collapse" id="result_title">
					<div class="panel-body"  style="height:50%;overflow-y:scroll">
						<div id="display_title"></div>
					</div>
				</div>
			</div>
			
			</div>
		</div>
	</div>
	
	<div class="modal-footer">
		<button id="retrieve" class="btn btn-info">Retrieve</button>
	</div>
	
	<div class="modal fade" id="officerSearch" tabindex="-1" role="dialog" aria-labelledby="officerSearch">
 	    <div class="modal-dialog" role="document">
			  <div class="modal-content" id='officerResult'>
			  Remote content load here
			  </div>
		</div> 
	</div>

<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html> --%>