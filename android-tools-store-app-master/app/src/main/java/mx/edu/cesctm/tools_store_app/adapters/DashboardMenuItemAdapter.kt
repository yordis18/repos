package mx.edu.cesctm.tools_store_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.dashboard_menu_entry.view.*
import mx.edu.cesctm.tools_store_app.*
import mx.edu.cesctm.tools_store_app.logic.DashboarMenuItem
import mx.edu.cesctm.tools_store_app.utils.UiUtils

class DashboardMenuItemAdapter: BaseAdapter {
    var dashboardMenuItemList = ArrayList<DashboarMenuItem>()
    var context: Context? = null

    constructor(context: Context, dashboardMenuItemList: ArrayList<DashboarMenuItem>): super(){
        this.context = context
        this.dashboardMenuItemList = dashboardMenuItemList
    }

    override fun getCount(): Int {
        return this.dashboardMenuItemList.size
    }

    override fun getItem(position: Int): Any {
        return this.dashboardMenuItemList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val dashboardMenuItem = this.dashboardMenuItemList[position]

        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var dashboardMenuItemView = inflator.inflate(R.layout.dashboard_menu_entry, null)
        dashboardMenuItemView.dashboard_iv_menu_entry.setImageResource(dashboardMenuItem.image!!)
        dashboardMenuItemView.dashboard_txt_menu_entry.text = dashboardMenuItem.name!!

        dashboardMenuItemView.setOnClickListener {
            when(position){
                0 -> UiUtils.startActivityWithoutExtras(context!!, UsersActivity())
            }
        }

        return dashboardMenuItemView
    }


}