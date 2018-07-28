package com.omidzamani.creptocoin.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.omidzamani.creptocoin.R
import kotlinx.android.synthetic.main.currency_list.view.*
import android.widget.Toast
import com.omidzamani.creptocoin.interfaces.CustomCurrencyListener
import com.omidzamani.creptocoin.model.Currency
import com.omidzamani.creptocoin.utils.SharedPreference
import java.text.DecimalFormat
import java.util.*


/**
 * Created by omidzamani on 15.07.2018.
 * this is my adapter for listing currencies
 */
class CurrencyAdapter constructor(private val listener: CustomCurrencyListener,
                                  private val context: Context,
                                  private var items: ArrayList<Currency>,
                                  private val isEditMode: Boolean)
    : RecyclerView.Adapter<CurrencyAdapter.MyViewHolder>() {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context)
                    .inflate(R.layout.currency_list, parent, false)

        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        val currencyFormat = DecimalFormat("#.##")
        holder.title.text = item.currencyFullName
        holder.sell.text = context.getString(R.string.sell).plus(currencyFormat.format(item.currencyPriceSell).plus("₺"))
        holder.buy.text = context.getString(R.string.buy).plus(currencyFormat.format(item.currencyPriceBuy).plus("₺"))
        holder.percent.text = currencyFormat.format(item.currencyPercent).plus("%")
        if (item.currencyPercent!! >= 0.0)
            holder.percent.setTextColor(Color.GREEN)
        else
            holder.percent.setTextColor(Color.RED)

        if (isEditMode) {
            holder.checkBox.visibility = View.VISIBLE
            holder.checkBox.isChecked = SharedPreference.getInstance(context).getCustomCoins().contains(item.currnecySymbol)
            holder.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    if (!SharedPreference.getInstance(context).canAddCustomCoin()) {
                        buttonView.isChecked = false
                        Toast.makeText(context, "Limit is 6 coins", Toast.LENGTH_LONG).show()
                    } else {
                        SharedPreference.getInstance(context).addCoin(item.currnecySymbol as String)
                    }
                } else {
                    SharedPreference.getInstance(context).deleteCoin(item.currnecySymbol as String)
                }
                listener.onCurrencyAddOrRemove()
            }

        } else {
            holder.checkBox.visibility = View.GONE
        }
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MyViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {


        var title: TextView = view.title
        var buy: TextView = view.buy
        var sell: TextView = view.sell
        var percent: TextView = view.percent
        var checkBox: CheckBox = view.check

    }


}