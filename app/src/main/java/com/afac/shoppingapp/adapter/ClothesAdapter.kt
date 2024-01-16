package com.afac.shoppingapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.afac.shoppingapp.R
import com.afac.shoppingapp.databinding.ItemCartBinding
import com.afac.shoppingapp.model.Clothes
import com.afac.shoppingapp.util.downloadFormUrl
import com.afac.shoppingapp.util.placeholderProgressBar
import com.afac.shoppingapp.view.ShoppingCartDirections
import kotlinx.android.synthetic.main.item_cart.view.*

internal  class ClothesAdapter(private val clothesList:ArrayList<Clothes>) :BaseAdapter(),ClothesClickListener{
    private lateinit var textName: TextView
    private lateinit var image: ImageView

    override fun getCount(): Int {
        return  clothesList.size
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
return  0
    }

    override fun getView(p0: Int, convertView: View?, p2: ViewGroup?): View {
        var convertView=convertView
        var layoutInflater=LayoutInflater.from(p2?.context)
        layoutInflater = p2?.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view= DataBindingUtil.inflate<ItemCartBinding>(layoutInflater,R.layout.item_cart,p2,false)
        view.listener=this
        convertView = view.root
        //clothesList[p0].clothesId=p0
        view.clothes=clothesList[p0]

       /* textName=convertView!!.findViewById(R.id.cartName)
        image=convertView!!.findViewById(R.id.cartImage)
        clothesList[p0].clothesImage?.let {
            convertView.cartImage.downloadFormUrl(
                it, placeholderProgressBar(convertView.context))
        }
        textName.text = clothesList[p0].clothesName*/

        return  convertView


}
    fun updateCountryList(newCountryList:List<Clothes>){///swipe refresh için gereken metod
        clothesList.clear()
        clothesList.addAll(newCountryList)
        notifyDataSetChanged()
    }
       override fun onClothesClicked(v: View) {


        val uuid=v.clothesIdText.text.toString().toInt()

        val action =ShoppingCartDirections.actionShoppingCartToShoppinCartDetail()
        val actionWithUuid = action.setCartId(uuid)
        Navigation.findNavController(v).navigate(actionWithUuid)
    }
}