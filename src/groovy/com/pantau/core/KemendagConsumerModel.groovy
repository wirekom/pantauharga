package com.pantau.core

import grails.validation.Validateable;

@Validateable
/**
 * Created by GE62 on 3/8/2016.
 */
class KemendagConsumerModel {
    Date tanggal
    String lokasi
    String pasar
    String komoditas
    String satuan
    String harga
}
