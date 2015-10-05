<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="landing"/>
    <title>Pantau Harga Application</title>
</head>

<body>
<div class="content_wrapper_inner">

    <g:render template="service"/>

    <g:render template="about"/>

    <g:render template="map"/>

    <g:render template="download"/>

    <!-- inflasi Block -->
    <!--
			<section id="inflasi" class="anchor_holder">
				<div class="content_wrapper_section2">
					<div class="container content_wrapper_section">
						<div class="main_title_wrapper">
							<div class="block_tile_wrapper">
								<h1 class="block_tile_1"> Check Inflasi</h1>
								<div class="icon_separator"></div>
							</div>
							<div class="clear"></div>
						</div>
						<div class="anchor_holder">
							<form class="anchor_inflasi" role="form" method="get" action="">
								<div class="input_holder">
									<label for="price">Location<span class="required-indicator">*</span></label>
									<select id="region.id" name="region.id">
										<option value="null">-Nasional-</option>
										<option value="17">DKI JAKARTA</option>
										<option value="1">KAB. PIDIE</option>
										<option value="2">KAB. SIMALUNGUN</option>
										<option value="3">KAB. SOLOK</option>
										<option value="4">KAB. AGAM</option>
										<option value="5">KAB. PAGAR ALAM</option>
										<option value="6">KAB. BENGKULU SELATAN</option>
										<option value="7">KAB. REJANG LEBONG</option>
										<option value="8">KAB. KAUR</option>
										<option value="9">KAB. KUNINGAN</option>
										<option value="10">KAB. CIREBON</option>
										<option value="11">KAB. KARAWANG</option>
										<option value="12">KAB. MAGELANG</option>
										<option value="13">KAB. PEMALANG</option>
										<option value="14">KAB. KUPANG</option>
										<option value="15">KAB. MANGGARAI</option>
										<option value="16">KAB. BULUKUMBA</option>
										<option value="19">KAB. BANTAENG</option>
										<option value="20">KAB. SINJAI</option>
										<option value="21">KAB. SOPPENG</option>
										<option value="22">KAB. WAJO</option>
									</select>
								</div>
								<div class="input_holder">
									<label for="price">Start Date<span class="required-indicator">*</span></label>
									<select id="start_month" name="start_month">
										<option selected="selected" value="">-Choose-</option>
										<option value="1">January</option>
										<option value="2">February</option>
										<option value="3">March</option>
										<option value="4">April</option>
										<option value="5">May</option>
										<option value="6">June</option>
										<option value="7">July</option>
										<option value="8">August</option>
										<option value="9">September</option>
										<option value="10">October</option>
										<option value="11">November</option>
										<option value="12">December</option>
									</select>
									<select id="start_year" name="start_year">
										<option selected="selected" value="">-Choose-</option>
										<option value="2013">2013</option>
										<option value="2014">2014</option>
										<option value="2015">2015</option>
										<option value="2016">2016</option>
										<option value="2017">2017</option>
										<option value="2018">2018</option>
										<option value="2019">2019</option>
										<option value="2020">2020</option>
										<option value="2021">2021</option>
										<option value="2022">2022</option>
									</select>
								</div>
								<div class="input_holder">
									<label for="price">End Date<span class="required-indicator">*</span></label>
									<select id="end_month" name="end_month">
										<option value="">-Choose-</option>
										<option value="1">January</option>
										<option value="2">February</option>
										<option value="3">March</option>
										<option value="4">April</option>
										<option value="5">May</option>
										<option value="6">June</option>
										<option value="7">July</option>
										<option value="8">August</option>
										<option value="9">September</option>
										<option value="10">October</option>
										<option value="11">November</option>
										<option value="12">December</option>
									</select>
									<select id="end_year" name="end_year">
										<option value="">-Choose-</option>
										<option value="2013">2013</option>
										<option value="2014">2014</option>
										<option value="2015">2015</option>
										<option value="2016">2016</option>
										<option value="2017">2017</option>
										<option value="2018">2018</option>
										<option value="2019">2019</option>
										<option value="2020">2020</option>
										<option value="2021">2021</option>
										<option value="2022">2022</option>
									</select>
								</div>
								<input type="submit" value="Calculate" name="submit" id="submit">
							</form>
							<div class="clearfix"></div>
							<span class="inflasi text-success">inflasinya : 0 %</span>
						</div>
					</div>
					<div class="clear"></div>
				</div>
			</section>-->
    <!-- End inflasi Block -->

    <g:render template="contact"/>

</div><!-- End Content wrapper inner -->


</body>
</html>