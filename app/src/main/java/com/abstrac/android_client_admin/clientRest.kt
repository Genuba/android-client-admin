package com.abstrac.android_client_admin

class Client(val client_fname: String,
             val cl_tconsums: List<Cl_tconsum>,
             val cl_treceipts: List<Cl_treceipt>)

class Cl_tconsum(val consum_consum: Int,
                 val consum_cant: Int,
                 val consum_date: String,
                 val consum_state: String,
                 val client_client: Int)


class Cl_treceipt(val receipt_receipt: Int,
                  val receipt_value: String,
                  val receipt_date: String,
                  val receipt_lastc: Int,
                  val receipt_pasc: Int,
                  val receipt_state: String,
                  val client_client: Int)