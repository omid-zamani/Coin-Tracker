package com.omidzamani.creptocoin.model

import org.json.JSONException
import org.json.JSONObject

/**
 * Created by omidzamani on 15.07.2018.
 *
 * this is my coin model
 */
class Currency
        /*
     "selling": 5.6668,
        "update_date": 1532725193,
        "currency": 2,
        "buying": 5.651,
        "change_rate": -0.093439819467218,
        "name": "euro",
        "full_name": "Euro",
        "code": "EUR"
     */
@Throws(JSONException::class)
constructor(item: JSONObject) {
    var currencyFullName: String? = null
        private set
    var currencyPriceBuy: Double? = null
        private set
    var currencyPercent: Double? = null
        private set
    var currencyPriceSell: Double? = null
        private set
    var currnecySymbol: String? = null
        private set


    init {

        currencyFullName = item.getString("full_name")
        currencyPercent = item.getDouble("change_rate")
        currencyPriceBuy = item.getDouble("buying")
        currencyPriceSell = item.getDouble("selling")
        currnecySymbol = item.getString("code")


    }


}
