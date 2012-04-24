/**
 * @copyright 2012 City of Bloomington, Indiana
 * @license http://www.gnu.org/licenses/gpl.txt GNU/GPL, see LICENSE.txt
 * @author Cliff Ingham <inghamn@bloomington.in.gov>
 */
package gov.in.bloomington.open311;





import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class MainActivity extends TabActivity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final TabHost tabHost = getTabHost();
        tabHost.setup();

        Resources res = getResources();
        Configuration cfg = res.getConfiguration();
		
		/*
		  horizontal orientation identifier - true if device in landscape orientation 
		*/
        boolean hor = cfg.orientation == Configuration.ORIENTATION_LANDSCAPE;

        if (hor) {
		     // setting orientaion of the tab widget's linear layout 
            TabWidget tw = tabHost.getTabWidget();
            tw.setOrientation(LinearLayout.VERTICAL);
        }
        
        
        TabHost.TabSpec spec;
        Intent intent;
        
        intent = new Intent().setClass(this, HomeActivity.class);
        spec = tabHost.newTabSpec("home").setIndicator(createIndicatorView(tabHost,res.getString(R.string.home), res.getDrawable(R.drawable.ic_menu_home))).setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, ReportActivity.class);
        spec = tabHost.newTabSpec("report").setIndicator(createIndicatorView(tabHost,res.getString(R.string.report), res.getDrawable(R.drawable.ic_menu_notifications))).setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, MyReportsActivity.class);
        spec = tabHost.newTabSpec("my_reports").setIndicator(createIndicatorView(tabHost,res.getString(R.string.my_reports), res.getDrawable(R.drawable.ic_menu_home))).setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, MyServersActivity.class);
        spec = tabHost.newTabSpec("my_servers").setIndicator(createIndicatorView(tabHost,res.getString(R.string.my_servers), res.getDrawable(R.drawable.ic_menu_star))).setContent(intent);
        tabHost.addTab(spec);
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.mainmenu, menu);
    	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case R.id.preferences:
    		Intent i = new Intent(MainActivity.this, MyPreferencesActivity.class);
    		startActivity(i);
    		break;
    	}
    	return true;
    }
    
    
    
    /*
	creating custom indicator view for use with horizontal configurations of the tab host
	this method uses custom layout available in the res/layout/tab_indicator.xml for the tab indicator view. this layout contains different configurations for lanscape and portrait orientations
	*/
    private View createIndicatorView(TabHost tabHost, CharSequence label, Drawable icon) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View tabIndicator = inflater.inflate(R.layout.tab_indicator,
                tabHost.getTabWidget(), // tab widget is the parent
                false); // no inflate params

        final TextView tv = (TextView) tabIndicator.findViewById(R.id.title);
        tv.setText(label);

        final ImageView iconView = (ImageView) tabIndicator.findViewById(R.id.icon);
        iconView.setImageDrawable(icon);

        return tabIndicator;
    }
}