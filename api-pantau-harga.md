User Authentication
1. Login 
request :
curl -i -X POST -H "Content-Type: applation/json" -d '{"username": "admin", "password": "admin"}' 23.226.228.133:8081/PantauHarga/api/login
response :
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Cache-Control: no-store
Pragma: no-cache
Content-Type: application/json;charset=UTF-8
Content-Length: 115
Date: Sun, 30 Aug 2015 17:09:03 GMT
{"username":"admin","roles":["ROLE_ADMIN"],"token_type":"Bearer","access_token":"f15ajhchguiijnl0s0n5trt48up6torf"}

2. Logout
request : 
curl -i -X POST -H "Content-Type: applation/json" -H "Authorization: Bearer f15ajhchguiijnl0s0n5trt48up6torf" 23.226.228.133:8081/PantauHarga/api/logout
response : 
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Length: 0
Date: Sun, 30 Aug 2015 17:10:15 GMT

3. Validate
request :
curl -i -X POST -H "Authorization: Bearer f15ajhchguiijnl0s0n5trt48up6torf" -H "Accept: application/vnd.error+json,application/json" -H "Content-Type: application/json" 23.226.228.133:8081/PantauHarga/api/validate
response : 
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Cache-Control: no-store
Pragma: no-cache
Content-Type: application/json;charset=UTF-8
Content-Length: 115
Date: Tue, 06 Oct 2015 07:18:30 GMT

{"username":"admin","roles":["ROLE_ADMIN"],"token_type":"Bearer","access_token":"f15ajhchguiijnl0s0n5trt48up6torf"}

CRUD Rest domain comodityTypes
1. POST 
request :
curl -i -H "Authorization: Bearer f15ajhchguiijnl0s0n5trt48up6torf" -H "Accept: application/vnd.error+json,application/json" -H "Content-Type: application/json" -X POST -d "{\"name\": \"Bawang Merah\", \"weight\": 10}" 23.226.228.133:8081/PantauHarga/api/comodityTypes.json
response :
HTTP/1.1 201 Created
Server: Apache-Coyote/1.1
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 08 Oct 2015 13:57:04 GMT

{"id":20,"name":"Bawang Merah","weight":10.0,"comodities":null}

2. PUT
request :
curl -i -H "Authorization: Bearer f15ajhchguiijnl0s0n5trt48up6torf" -H "Accept: application/vnd.error+json,application/json" -H "Content-Type: application/json" -X PUT -d "{\"name\": \"Bawang\", \"weight\": 10}" 23.226.228.133:8081/PantauHarga/api/comodityTypes/4.json
response : 
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 08 Oct 2015 13:57:44 GMT

{"id":20,"name":"Bawang","weight":10.0,"comodities":[]}

3. GET ALL
request :
curl -i -H "Authorization: Bearer f15ajhchguiijnl0s0n5trt48up6torf" -H "Accept: application/vnd.error+json,application/json" -H "Content-Type: application/json" -X GET 23.226.228.133:8081/PantauHarga/api/comodityTypes.json
response :
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 08 Oct 2015 13:58:16 GMT

[{"id":9,"name":"Beras","weight":0.5,"comodities":[{"id":11,"name":"Beras Pera","sku":"2","type":"Beras","type_id":9},{"id":10,"name":"Beras Medium","sku":"1","type":"Beras","type_id":9},{"id":12,"name":"Beras Premium","sku":"3","type":"Beras","type_id":9}]},{"id":13,"name":"Gula","weight":0.2,"comodities":[{"id":14,"name":"Gula Pasir","sku":"4","type":"Gula","type_id":13}]},{"id":15,"name":"Daging","weight":0.3,"comodities":[{"id":16,"name":"Daging Sapi Murni","sku":"2","type":"Daging","type_id":15},{"id":17,"name":"Daging Sapi Paha Belakang","sku":"2","type":"Daging","type_id":15}]},{"id":20,"name":"Bawang","weight":10.0,"comodities":[]}]

4. DELETE
request :
curl -i -H "Authorization: Bearer f15ajhchguiijnl0s0n5trt48up6torf" -H "Accept: application/vnd.error+json,application/json" -H "Content-Type: application/json" -X DELETE 23.226.228.133:8081/PantauHarga/api/comodityTypes/4.json
response :
HTTP/1.1 204 No Content
Server: Apache-Coyote/1.1
Date: Thu, 08 Oct 2015 13:58:40 GMT


5. GET ONE
request : 
curl -i -H "Authorization: Bearer f15ajhchguiijnl0s0n5trt48up6torf" -H "Accept: application/vnd.error+json,application/json" -H "Content-Type: application/json" -X GET 23.226.228.133:8081/PantauHarga/api/comodityTypes/1.json
response :
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 08 Oct 2015 13:59:09 GMT

{"id":9,"name":"Beras","weight":0.5,"comodities":[{"id":12,"name":"Beras Premium","sku":"3","type":"Beras","type_id":9},{"id":11,"name":"Beras Pera","sku":"2","type":"Beras","type_id":9},{"id":10,"name":"Beras Medium","sku":"1","type":"Beras","type_id":9}]}

CRUD Rest domain comodities
1. POST 
request :
curl -i -H "Authorization: Bearer f15ajhchguiijnl0s0n5trt48up6torf" -H "Accept: application/vnd.error+json,application/json" -H "Content-Type: application/json" -X POST -d "{\"name\": \"Beras Ketan\", \"sku\": \"5\", \"comodityType\": 9}" 23.226.228.133:8081/PantauHarga/api/comodities.json
response :
HTTP/1.1 201 Created
Server: Apache-Coyote/1.1
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 08 Oct 2015 14:00:42 GMT

{"id":21,"name":"Beras Ketan","sku":"5","type":"Beras","type_id":9}

2. PUT
request :
curl -i -H "Authorization: Bearer f15ajhchguiijnl0s0n5trt48up6torf" -H "Accept: application/vnd.error+json,application/json" -H "Content-Type: application/json" -X PUT -d "{\"name\": \"Beras Ketan Hitam\", \"sku\": \"5\", \"comodityType\": 9}}" 23.226.228.133:8081/PantauHarga/api/comodities/21.json
response : 
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 08 Oct 2015 14:02:12 GMT

{"id":21,"name":"Beras Ketan Hitam","sku":"5","type":"Beras","type_id":9}

3. GET ALL
request :
curl -i -H "Authorization: Bearer f15ajhchguiijnl0s0n5trt48up6torf" -H "Accept: application/vnd.error+json,application/json" -H "Content-Type: application/json" -X GET 23.226.228.133:8081/PantauHarga/api/comodities.json
response :
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 08 Oct 2015 14:02:28 GMT

[{"id":10,"name":"Beras Medium","sku":"1","type":"Beras","type_id":9},{"id":11,"name":"Beras Pera","sku":"2","type":"Beras","type_id":9},{"id":12,"name":"Beras Premium","sku":"3","type":"Beras","type_id":9},{"id":14,"name":"Gula Pasir","sku":"4","type":"Gula","type_id":13},{"id":16,"name":"Daging Sapi Murni","sku":"2","type":"Daging","type_id":15},{"id":17,"name":"Daging Sapi Paha Belakang","sku":"2","type":"Daging","type_id":15},{"id":21,"name":"Beras Ketan Hitam","sku":"5","type":"Beras","type_id":9}]

4. DELETE
request :
curl -i -H "Authorization: Bearer f15ajhchguiijnl0s0n5trt48up6torf" -H "Accept: application/vnd.error+json,application/json" -H "Content-Type: application/json" -X DELETE 23.226.228.133:8081/PantauHarga/api/comodities/21.json
response :
HTTP/1.1 204 No Content
Server: Apache-Coyote/1.1
Date: Thu, 08 Oct 2015 14:03:29 GMT

5. GET ONE
request : 
curl -i -H "Authorization: Bearer f15ajhchguiijnl0s0n5trt48up6torf" -H "Accept: application/vnd.error+json,application/json" -H "Content-Type: application/json" -X GET 23.226.228.133:8081/PantauHarga/api/comodities/21.json
response :
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 08 Oct 2015 14:03:07 GMT

{"id":21,"name":"Beras Ketan Hitam","sku":"5","type":"Beras","type_id":9}

CRUD Rest domain comodityInputs
1. POST 
request :
curl -i -H "Authorization: Bearer f15ajhchguiijnl0s0n5trt48up6torf" -H "Accept: application/vnd.error+json,application/json" -H "Content-Type: application/json" -X POST -d "{\"comodityName\": 10, \"price\": 5000, \"amount\": 5, \"lat\": -2.839907659713759, \"lng\":  103.29198536249999, \"delta\": 0}" 23.226.228.133:8081/PantauHarga/api/comodityInputs.json
response :
HTTP/1.1 201 Created
Server: Apache-Coyote/1.1
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 08 Oct 2015 14:05:11 GMT

{"id":22,"price":5000.0,"amount":5,"delta":0.0,"lat":-2.839907659713759,"lng":103.29198536249999,"username":"admin","nohp":"8098080980","comodityName":"Beras Medium","comodityType":"Beras"}

2. PUT
request :
curl -i -H "Authorization: Bearer f15ajhchguiijnl0s0n5trt48up6torf" -H "Accept: application/vnd.error+json,application/json" -H "Content-Type: application/json" -X PUT -d "{\"comodityName\": 10, \"price\": 50000, \"amount\": 5, \"lat\": -2.839907659713759, \"lng\":  103.29198536249999}" 23.226.228.133:8081/PantauHarga/api/comodityInputs/22.json
response : 
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 08 Oct 2015 14:05:47 GMT

{"id":22,"price":50000.0,"amount":5,"delta":0.0,"lat":-2.839907659713759,"lng":103.29198536249999,"username":"admin","nohp":"8098080980","comodityName":"Beras Medium","comodityType":"Beras"}

3. GET ALL
request :
curl -i -H "Authorization: Bearer f15ajhchguiijnl0s0n5trt48up6torf" -H "Accept: application/vnd.error+json,application/json" -H "Content-Type: application/json" -X GET 23.226.228.133:8081/PantauHarga/api/comodityInputs.json
response :
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 08 Oct 2015 14:06:04 GMT

[{"id":22,"price":50000.0,"amount":5,"delta":0.0,"lat":-2.839907659713759,"lng":103.29198536249999,"username":"admin","nohp":"8098080980","comodityName":"Beras Medium","comodityType":"Beras"}]

4. DELETE
request :
curl -i -H "Authorization: Bearer f15ajhchguiijnl0s0n5trt48up6torf" -H "Accept: application/vnd.error+json,application/json" -H "Content-Type: application/json" -X DELETE 23.226.228.133:8081/PantauHarga/api/comodityInputs/22.json
response :
HTTP/1.1 204 No Content
Server: Apache-Coyote/1.1
Date: Thu, 08 Oct 2015 14:06:52 GMT

5. GET ONE
request : 
curl -i -H "Authorization: Bearer f15ajhchguiijnl0s0n5trt48up6torf" -H "Accept: application/vnd.error+json,application/json" -H "Content-Type: application/json" -X GET 23.226.228.133:8081/PantauHarga/api/comodityInputs/22.json
response :
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 08 Oct 2015 14:06:30 GMT

{"id":22,"price":50000.0,"amount":5,"delta":0.0,"lat":-2.839907659713759,"lng":103.29198536249999,"username":"admin","nohp":"8098080980","comodityName":"Beras Medium","comodityType":"Beras"}

CRUD Rest domain authUsers
1. POST 
///akses untuk user : guest, password: guest
request :
curl -i -H "Authorization: Bearer f15ajhchguiijnl0s0n5trt48up6torf" -H "Accept: application/vnd.error+json,application/json" -H "Content-Type: application/json" -X POST -d "{\"username\": \"widodo\", \"password\": \"pangestu\", \"email\": \"wid.pangestu@gmail.com\", \"nama\": \"Widodo Pangestu\", \"ktp\": \"123456\", \"nohp\": \"081385161888\", \"alamat\": \"Jl. Merdeka\", \"kodepos\": \"15133\"}" 23.226.228.133:8081/PantauHarga/api/authUsers.json
response :
HTTP/1.1 201 Created
Server: Apache-Coyote/1.1
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 08 Oct 2015 14:07:13 GMT

{"id":23,"username":"widodo","email":"wid.pangestu@gmail.com","nama":"Widodo Pangestu","alamat":"Jl. Merdeka","nohp":"081385161888","ktp":"123456","kodepos":"15133"}

2. PUT
///akses hanya untuk usernya sendiri
request :
curl -i -H "Authorization: Bearer f15ajhchguiijnl0s0n5trt48up6torf" -H "Accept: application/vnd.error+json,application/json" -H "Content-Type: application/json" -X PUT -d "{\"username\": \"widodo\", \"password\": \"pangestu\", \"email\": \"wid.pangestu@gmail.com\", \"nama\": \"Widodo Pangestu\", \"ktp\": \"123456\", \"nohp\": \"081385161888\", \"alamat\": \"Jl. Merdeka Utara\", \"kodepos\": \"15133\"}" 23.226.228.133:8081/PantauHarga/api/authUsers/23.json
response : 
HTTP/1.1 200 OK
erver: Apache-Coyote/1.1
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 08 Oct 2015 14:07:13 GMT

{"id":23,"username":"widodo","email":"wid.pangestu@gmail.com","nama":"Widodo Pangestu","alamat":"Jl. Merdeka","nohp":"081385161888","ktp":"123456","kodepos":"15133"}

3. GET ALL
//akses hanya user admin
request :
curl -i -H "Authorization: Bearer f15ajhchguiijnl0s0n5trt48up6torf" -H "Accept: application/vnd.error+json,application/json" -H "Content-Type: application/json" -X GET 23.226.228.133:8081/PantauHarga/api/authUsers.json
response :
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 08 Oct 2015 14:09:53 GMT

[{"id":7,"username":"admin","email":"admin@gmail.com","nama":"admin","alamat":null,"nohp":"8098080980","ktp":null,"kodepos":null},{"id":8,"username":"guest","email":"guest@gmail.com","nama":"guest","alamat":null,"nohp":"8098080988","ktp":null,"kodepos":null},{"id":23,"username":"widodo","email":"wid.pangestu@gmail.com","nama":"Widodo Pangestu","alamat":"Jl. Merdeka Utara","nohp":"081385161888","ktp":"123456","kodepos":"15133"}]

4. DELETE
//akses hanya user admin
request :
curl -i -H "Authorization: Bearer f15ajhchguiijnl0s0n5trt48up6torf" -H "Accept: application/vnd.error+json,application/json" -H "Content-Type: application/json" -X DELETE 23.226.228.133:8081/PantauHarga/api/authUsers/4.json
response :
HTTP/1.1 204 No Content
Server: Apache-Coyote/1.1
Date: Tue, 06 Oct 2015 09:26:54 GMT

5. GET ONE
request : 
curl -i -H "Authorization: Bearer f15ajhchguiijnl0s0n5trt48up6torf" -H "Accept: application/vnd.error+json,application/json" -H "Content-Type: application/json" -X GET 23.226.228.133:8081/PantauHarga/api/authUsers/7.json
response :
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 08 Oct 2015 14:10:41 GMT

{"id":7,"username":"admin","email":"admin@gmail.com","nama":"admin","alamat":null,"nohp":"8098080980","ktp":null,"kodepos":null}



untuk mengambil data seluruh harga berdasarkan lokasi dan radius

curl  -X POST -i -H "Authorization: Bearer f15ajhchguiijnl0s0n5trt48up6torf" -H "Accept: application/vnd.error+json,application/json" -H "Content-Type: application/json" -d "{\"radius\": 300, \"name\": \"beras\", \"lat\": -6.1699806, \"lng\": 106.58466759999999}" 23.226.228.133:8081/PantauHarga/api/hargaall.json






