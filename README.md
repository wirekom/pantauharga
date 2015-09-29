# Pantau Harga Web Portal
Aplikasi web Pantau Harga untuk melihat hasil pengolahan data yang dikumpulkan melaui aplikasi android Pantau Harga https://github.com/wirekom/cekhargamobile

Deskripsi solusi lebih lengkap dapat diunduh di https://www.dropbox.com/s/0dd1lwo2ep0myc9/Pantau%20Harga%20-%20CodingStelsel.docx

cara install
1. install java
2. install grails
3.install postgres


#list api
/Api/input.json
buat input
{"id": [id barang(int)],"quantity":[jumlah barang, 0 kl cuma laporin harga(int)],"harga":[harganya(float)],"lat":[lat (float)],"lng":[long(float)],"nohp":[no hp (string)]}

/Api/hargaall.json

{"name":"[namanya(string)]","radius":"[km(int)]","lat":[float] ,"lng":[float]}

/Api/comodityall.json
inisialisasi data dl buat dropdown