## **DOKUMENTASI API PANTAU HARGA**

![enter image description here](http://pantauharga.id/images/banana.png)

[PantauHarga](http://pantauharga.id/) adalah layanan situs web dan aplikasi untuk memantau harga komoditas pangan yang berada di sekitar pengguna. Pengguna pun dapat ikut serta melaporkan harga komoditasnya ketika sedang belanja di pasar tradisional atau supermarket.

http://pantauharga.id
```

###**Content-Type**
```json
application/json
```
----------

#### **Ambil Daftar Komoditas**
Ambil jenis komoditas pangan yang telah tersedia di server.

**GET (/api/comodityall.json)**

**PARAMETER** :

Tidak ada

**RESPONSE**
```json
[
    {
        "id": 1034,
        "name": "Bawang Merah Kering",
        "sku": "35"
    },
    {
        "id": 1044,
        "name": "Bawang Putih Impor",
        "sku": "45"
    },
    {
        "id": 1043,
        "name": "Bawang Putih Lokal",
        "sku": "44"
    },
    {
        "id": 958,
        "name": "Beras Ketan",
        "sku": "3"
    },
    {
        "id": 9,
        "name": "Beras Medium",
        "sku": "1"
    },
    {
        "id": 3791,
        "name": "Beras Organik",
        "sku": "098"
    },
    {
        "id": 10,
        "name": "Beras Pera",
        "sku": "2"
    },
    {
        "id": 8,
        "name": "Beras Premium",
        "sku": "3"
    },
    {
        "id": 1035,
        "name": "Cabe Merah Besar",
        "sku": "36"
    },
    {
        "id": 1036,
        "name": "Cabe Merah Keriting",
        "sku": "37"
    },
    {
        "id": 1045,
        "name": "Cabe Rawit Hijau",
        "sku": "46"
    }
]
```

----------

#### **Ambil Harga Komoditas Pangan**
Ambil harga komoditas pangan.

**POST (/api/hargaall.json)**


**PARAMETER** :

Parameter  | Tipe | Keterangan
---------- | -----|-----
name       | String | nama komoditas
radius    | Integer | radius pencarian dalam kilometer
lat     | Double | koordinat latitude
Ing     | Double | koordinat longitude

Disusun dalam bentuk respon body String tipe JSON dengan contoh berikut :
```json
{
    "name": "Beras Medium",
    "radius": "100",
    "lat": -6.217,
    "lng": 106.9
}
```

**RESPONSE**

```json
[
    {
        "barang": "Beras Medium",
        "errors":
        {
            "errors":
            [
            ]
        },
        "lastUpdated": "2016-02-16T17:00:00Z",
        "latitude": "-6.849407",
        "longitude": "106.955305",
        "nohp": "0",
        "price": 9600,
        "type": null
    },
    {
        "barang": "Beras Medium",
        "errors":
        {
            "errors":
            [
            ]
        },
        "lastUpdated": "2016-02-10T17:00:00Z",
        "latitude": "-6.4952915",
        "longitude": "107.7345795",
        "nohp": "0",
        "price": 9500,
        "type": null
    }
]
```

**GET (/api/hargadate.json)**


**PARAMETER** :

Parameter  | Tipe | Keterangan
---------- | -----|-----
date (mandatory)       | String | tanggal post harga (format: dd/mm/yyyy, cth: 31/12/2015)
name (optional)    | String | nama komoditas (dari /api/comodityall.json)

Contoh:
```
http://pantauharga.id/api/hargadate.json?date=31/12/2015&name=Cabe Merah Keriting
```

**RESPONSE**

```json
[
    {
        "barang": "Cabe Merah Keriting",
        "description": null,
        "errors":
        {
            "errors":
            [
            ]
        },
        "lastUpdated": "2015-12-31T17:00:00Z",
        "latitude": "-0.927534",
        "longitude": "100.837884",
        "nohp": "0",
        "price": 65000,
        "type": null
    },
    {
        "barang": "Cabe Merah Keriting",
        "description": null,
        "errors":
        {
            "errors":
            [
            ]
        },
        "lastUpdated": "2015-12-31T17:00:00Z",
        "latitude": "-3.242327",
        "longitude": "118.9360231",
        "nohp": "0",
        "price": 35000,
        "type": null
    }
]
```

----------

#### **Kirim Harga/Jual Komoditas Pangan**
Kirim harga komoditas pangan.

**POST (/api/input.json)**

**PARAMETER** :

Parameter  | Tipe | Keterangan
---------- | -----|-----
id       | String | id komoditas komoditas
lat     | Double | koordinat latitude
Ing     | Double | koordinat longitude
nohp    |String  | nomor handphone pengguna atau pelapor
harga | Integer | harga komoditas pangan
quantity | Integer | jumlah komoditas yang tersedia
description | Text | keterangan komoditas

Disusun dalam bentuk respon body String tipe JSON dengan contoh berikut :
```json
{
    "id": "1034",
    "lat": "-6.217",
    "lng": "106.9",
    "nohp": "08123123",
    "harga": "20000",
    "quantity": "30",
    "description": "cabai segar dan alami"
}
```
**RESPONSE**
```json
{
    "errors": {
        "errors": []
    },
    "harga": 20000,
    "id": 1034,
    "lat": -6.217,
    "lng": 106.9,
    "nohp": "08123123",
    "quantity": 30,
    "description": "cabai segar dan alami"
}
```

----------

#### **Registrasi Pengguna/Pelapor Harga**
Pengguna yang belum terdaftar wajib mendaftarkan dirinya.

**POST (/api/register.json)**

**PARAMETER** :

Parameter  | Tipe | Keterangan
---------- | -----|-----
nama | String | nama lengkap pengguna
username       | String | nama panggilan atau username untuk login
password     | String | password pengguna minimal 6 karakter
email     | String | alamat email pengguna
ktp    |String  | nomor KTP pengguna
nohp | String | nomor handphone pengguna
alamat | String | alamat lengkap rumah pengguna
kodepos | String | kode pos daerah tempat tinggal pengguna

Disusun dalam bentuk respon body String tipe JSON dengan contoh berikut :
```json
{
    "username": "username1s",
    "password": "1234356",
    "email": "emailss@mail.com",
    "ktp": "123456",
    "nohp": "1234567",
    "alamat": "alamat tempat tinggal",
    "kodepos": "50123",
    "nama": "nama lengkap pengguna"
}
```

**RESPONSE**

```json
{
    "alamat": "alamat tempat tinggal",
    "email": "emailss@mail.com",
    "errors": {
        "errors": []
    },
    "kodepos": "50123",
    "ktp": "123456",
    "nama": "nama lengkap pengguna",
    "nohp": "1234567",
    "password": "1234356",
    "username": "username1s"
}
```

--------

#### **Masuk ke Akun / Login**
Pengguna yang sudah terdaftar, bisa langsung masuk ke akun Pantau Harga.

**POST (/api/login.json)**

**PARAMETER** :

|Parameter  | Tipe   | Keterangan                               |
|---------- | -------|------------------------------------------|
|username   | String | nama panggilan atau username untuk login |
|password   | String | password pengguna minimal 6 karakter     |


Disusun dalam bentuk respon body String tipe JSON dengan contoh berikut :
```json
{
    "username": "username1",
    "password": "1234356"
}
```
**RESPONSE**
```json
{
    "username": "saep",
    "nama": "Saep Mahmudin",
    "email": "saep@gmail.com",
    "ktp": "123456",
    "nohp": "1234567890",
    "alamat": "Jalan Bandung Nomor 10",
    "kodepos": "50121"
}
```
