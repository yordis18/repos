package mx.edu.cesctm.tools_store_app
import android.support.v7.app.AppCompatActivity


import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import mx.edu.cesctm.tools_store_app.adapters.DashboardMenuItemAdapter
import mx.edu.cesctm.tools_store_app.logic.DashboarMenuItem
import mx.edu.cesctm.tools_store_app.models.User

class DashboardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var adapter: DashboardMenuItemAdapter? = null
    var dashboardMenuItemList = ArrayList<DashboarMenuItem>()
    var user:User? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        setupDashboardMenu()
        setupNavigationDrawer()
    }



    private fun setupDashboardMenu() : Unit{
        dashboardMenuItemList.add(DashboarMenuItem("users", R.drawable.ic_customers))

        adapter = DashboardMenuItemAdapter(this@DashboardActivity,dashboardMenuItemList)
        dashboard_gv_menu.adapter = adapter
    }

    override fun onBackPressed() {
        if (dashboard_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            dashboard_drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_logout -> finish()
        }
        dashboard_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setupNavigationDrawer(): Unit{
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, dashboard_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        dashboard_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view_dashboard.setNavigationItemSelectedListener(this)

        user = intent.getParcelableExtra("user")

        user!!.fullname()
    }


}
