<?php
//define variables
$url = "https://api.bigdataindonesia.com/poi/";
$method = "textsearch"; // You can change with another method (textsearch/area/specific/nearby)
$output = "xml"; // You can change with another output (json / xml)

//define paramaters
$paramters = array (
"query" => "ATM Senopati Jakarta Selatan",
);
$send_params = https_build_query($paramters);

//combine url request
$send_url = $url."".$method."/".$output."?".$send_params;

// set config key
$config = array(
"key" => "58b066d5064c98e75bb02adb853bc1ad",
);

$postdata = http_build_query($config);
// sending API Request
$opts = array(
'http' => array(
'method' => 'POST',
'header' => 'Content-type: application/x-www-form-urlencoded',
'content' => $postdata
)
);

$context = stream_context_create($opts);
$result = file_get_contents($send_url, false, $context);
echo $result;
?> 
