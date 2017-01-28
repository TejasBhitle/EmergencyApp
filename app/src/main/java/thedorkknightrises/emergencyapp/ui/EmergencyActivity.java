package thedorkknightrises.emergencyapp.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import thedorkknightrises.emergencyapp.R;


public class EmergencyActivity extends AppCompatActivity {


    Toolbar toolbar;
    ExpandableListView faqListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencycontact);

        toolbar = (Toolbar)findViewById(R.id.toolbar_emer);
        faqListView  = (ExpandableListView) findViewById(R.id.list_faq);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Help");
        String[] groups = new String[0];
        String[][] children = new String[0][0];


            groups = new String[]{"Child Helpline","Police","Fire","Ambulance","Emergency Disaster Management","Women in Distress (Delhi)","Emergency medical service in local area","Central Accident and Trauma Services"};

            children = new String[][]
                    {{"Childline India Foundation HO\n"+"1 review · Non-Profit Organization\n" + "11.5 km · 4th Floor, Sumer Kendra, HR Devrukhkar Marg\n" +
                            "022 2388 1098","Childline India Foundation\n"+"No reviews · Child Social Service Organization\n" +"12.7 km · 2nf Floor,Unit No.204/A,Parvati Industrial Estate,New Sun Mills Compound, Senapati Bapat Marg\n" +
                            "022 2498 9630"}, {"100"},{"101"},{"102"},{"108"},{"181"},{"1099"},{"1056"}};


        faqListView.setAdapter(new ExpandableListAdapter(groups, children));
        faqListView.setGroupIndicator(null);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public class ExpandableListAdapter extends BaseExpandableListAdapter {

        private final LayoutInflater inf;
        private String[] groups;
        private String[][] children;

        public ExpandableListAdapter(String[] groups, String[][] children) {
            this.groups = groups;
            this.children = children;
            inf = LayoutInflater.from(getApplicationContext());
        }

        @Override
        public int getGroupCount() {
            return groups.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return children[groupPosition].length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groups[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return children[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

            ExpandableListAdapter.ViewHolder holder;
            if (convertView == null) {
                convertView = inf.inflate(R.layout.list_item, parent, false);
                holder = new ExpandableListAdapter.ViewHolder();

                holder.text = (TextView) convertView.findViewById(R.id.lblListItem);
                //holder.text.setBackgroundColor(getResources().getColor(R.color.colorTranslucent));
                convertView.setTag(holder);
            } else {
                holder = (ExpandableListAdapter.ViewHolder) convertView.getTag();
            }

            holder.text.setText(getChild(groupPosition, childPosition).toString());
            //holder.text.setTextColor(Color.BLACK);
            holder.text.setAutoLinkMask(Linkify.PHONE_NUMBERS);

            return convertView;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            ExpandableListAdapter.ViewHolder holder;

            if (convertView == null) {
                convertView = inf.inflate(R.layout.list_group, parent, false);

                holder = new ExpandableListAdapter.ViewHolder();
                holder.text = (TextView) convertView.findViewById(R.id.lblListHeader);
                convertView.setTag(holder);
            } else {
                holder = (ExpandableListAdapter.ViewHolder) convertView.getTag();
            }

            holder.text.setText(getGroup(groupPosition).toString());
            holder.text.setTextColor(Color.WHITE);

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        private class ViewHolder {
            TextView text;
        }
    }
}
