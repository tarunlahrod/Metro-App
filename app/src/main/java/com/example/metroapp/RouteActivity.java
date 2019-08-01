package com.example.metroapp;

import android.content.Context;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;

public class RouteActivity extends AppCompatActivity {

    ListView listViewRoute;
    TextView tv_station, tv_cost, tv_time, tv_titleBar;
    Spinner spinner;
    MyAdapter adapter;


    String[] stationNameList = new String[]{"Netaji Subhash Place", "Shalimar Bagh", "Azadpur", "Model Town", "G.T.B. Nagar", "Vishwavidyalaya", "Vidhan Sabha", "Civil Lines", "Kashmere Gate", "Tis Hazari", "Pulbangash", "Pratap Nagar", "Shastri Nagar", "Inderlok", "Kanhaiya Nagar", "Keshav Puram", "Chandni Chowk", "Chawri Bazar", "New Delhi", "Rajiv Chowk", "RK Ashram Marg", "Jhandewalan", "Karol Bagh", "Rajendra Place", "Patel Nagar", "Shadipur", "Kirti Nagar", "Satguru Ram Singh Marg", "Ashok Park Main", "Moti Nagar", "Ramesh Nagar", "Rajouri Garden", "ESI - Basaidarapur", "Punjabi Bagh (W)", "Shakurpur"};
    int[] stationNodeList = new int[]{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34};
    int[] stationColorCode = new int[]{ 1, 5, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 3, 1, 1, 4, 4, 4, 4, 2, 2, 2, 2, 2, 2, 2, 3, 3, 2, 2, 2, 5, 5, 5};
    int[] stationInterchange = new int[]{ 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0};
    float[] crowdWeight = new float[]{0.75f, 0.62f, 0.7f, 0.66f, 0.72f, 0.78f, 0.62f, 0.6f, 0.85f, 0.63f, 0.62f, 0.64f, 0.7f, 0.78f, 0.7f, 0.65f, 0.79f, 0.78f, 0.8f, 0.95f, 0.7f, 0.75f, 0.8f, 0.75f, 0.69f, 0.7f, 0.85f, 0.63f, 0.71f, 0.68f, 0.66f, 0.78f, 0.75f, 0.75f, 0.75f};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        listViewRoute = findViewById(R.id.listView_route);
        tv_station = findViewById(R.id.numStation);
        tv_cost = findViewById(R.id.cost);
        tv_time = findViewById(R.id.time);
        spinner = findViewById(R.id.spinner);
        tv_titleBar = findViewById(R.id.titleBar);

        String currTime = getTime();
//        tv_titleBar.setText(currTime);
        Log.d("okok", "Current time: " + currTime);

        int numArrayList;
        final ArrayList <ArrayList <Integer> > allRouteArrayList;

        // adding colored logos
        for(int i=0; i<stationColorCode.length; i++){
            stationColorCode[i] = colorCodeToImageResource(stationColorCode[i]);
        }

        //getting data from intent
        numArrayList  = getIntent().getIntExtra("noOfArrayLists", 3);
        allRouteArrayList = new ArrayList<>(numArrayList);
        String[] routes = new String[numArrayList];

        for(int i = 0; i < numArrayList; i++){
            routes[i] = "Route " + (i+1);
            ArrayList<Integer> arrayList = getIntent().getIntegerArrayListExtra("pathArrayList"+i);
            allRouteArrayList.add(arrayList);
        }
        sortBubble(allRouteArrayList);


        // storing only 3 routes at maximum
        String[] routes3only;
        if(numArrayList > 3){
            routes3only = new String[3];

            for(int i = 0; i < 3; i++) {
                routes3only[i] = routes[i] + "";
                Log.d("okok", "here");
            }

            // set numArrayList = 3
            numArrayList = 3;
        }
        else {

            routes3only = new String[numArrayList];
            for(int i = 0; i < numArrayList; i++)
                routes3only[i] = routes[i] + "";
        }

        int timeEfficient = getTimeEfficientRouteIndex(allRouteArrayList, numArrayList);
        int crowdEfficient = getCrowdEfficientRouteIndex(allRouteArrayList, numArrayList);

        routes3only[timeEfficient] += " (Time Efficient)";
        routes3only[crowdEfficient] += " (Crowd Efficient)";
        Log.d("okok", "Time Eff: " + timeEfficient);
        Log.d("okok", "Crowd Eff: " + crowdEfficient);


        //setting the spinner
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, routes3only);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            	
            	int size = allRouteArrayList.get(position).size();

                String stationNameArray[] = nodeToStation(allRouteArrayList.get(position), size);
                int stationNodeArray[] = nodeArrayListToNodeArray(allRouteArrayList.get(position), size);
                int stationColorArray[] = nodeToColor(allRouteArrayList.get(position), size);

                int time = calculateTime(allRouteArrayList.get(position), size);
                
                adapter = new MyAdapter(getApplicationContext(), stationNameArray, stationNodeArray, stationColorArray);
                listViewRoute.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                
                tv_station.setText("" + stationNameArray.length);
                tv_cost.setText("Rs. 0");
                tv_time.setText("" + time + " min");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    // creating a custom array adapter class

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String stationName[];
        int stationNode[];
        int lineColor[];

        MyAdapter (Context c, String name[], int node[], int img[]) {
            super(c, R.layout.station_row_final, R.id.tv_stationName, name);
            this.context = c;
            this.lineColor = img;
            this.stationName = name;
            this.stationNode = node;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.station_row_final, parent, false);
            TextView sName = row.findViewById(R.id.tv_stationName);
            TextView sNode = row.findViewById(R.id.tv_stationNode);
            ImageView sColor = row.findViewById(R.id.imageView);

            // now set our resources on views
            sName.setText(stationName[position]);
            sNode.setText("Node number " + stationNode[position]);
            sColor.setImageResource(lineColor[position]);

            return row;
        }
    }

    public String[] nodeToStation(ArrayList<Integer> route, int length){
        String[] stationName = new String[length];
        for(int i = 0; i < length; i++){
            stationName[i] = stationNameList[route.get(i)];
        }
        return stationName;
    }

    public int[] nodeToColor(ArrayList<Integer> route, int length){
        int[] stationColor = new int[length];
        for(int i=0; i<length; i++){
            stationColor[i] = stationColorCode[route.get(i)];
        }
        return stationColor;
    }

    public int[] nodeArrayListToNodeArray(ArrayList<Integer> route, int length){
        int[] stationNode = new int[length];
        for(int i=0; i<length; i++){
            stationNode[i] = route.get(i);
        }
        return stationNode;
    }

    public void sortBubble(ArrayList <ArrayList<Integer>> allPaths){
        int size  = allPaths.size();
        for(int i=0; i < size; i++){
            for(int j=0; j < size-1-i; j++){
                if(allPaths.get(j).size() > allPaths.get(j+1).size()){
                   Collections.swap(allPaths, j, j+1);
                }
            }
        }
    }

    public int colorCodeToImageResource(int colorCode){
        int red = R.drawable.delhi_metro_logo1;
        int blue = R.drawable.delhi_metro_logo2;
        int green = R.drawable.delhi_metro_logo3;
        int yellow = R.drawable.delhi_metro_logo4;
        int pink = R.drawable.delhi_metro_logo5;
        int purple = R.drawable.delhi_metro_logo6;
        int magenta = R.drawable.delhi_metro_logo7;
		int violet = R.drawable.delhi_metro_logo8;
		int orange = R.drawable.delhi_metro_logo9;
        int black = R.drawable.delhi_metro;

        int logoColor;

        switch(colorCode){
            case 1: logoColor = red; break;
            case 2: logoColor = blue; break;
            case 3: logoColor = green; break;
            case 4: logoColor = yellow; break;
            case 5: logoColor = pink; break;
            case 6: logoColor = purple; break;
            case 7: logoColor = magenta; break;
			case 8: logoColor = violet; break;
			case 9: logoColor = orange; break;
            default: logoColor = black;
        }
        return logoColor;
    }

    public int calculateTime(ArrayList<Integer> route, int length) {

    	// Time criteria: 2 min at all stations except at interchange and 3 min at interchange

    	// Logic: 2 min x no. of stations + 1 min for each interchange
    	int time = 2*length;

    	for(int i=0; i<length; i++){

    		// check if current station is an interchangeable station
    		// if interchangeable stations at first and last, do not consider them as interchange
    		if((stationInterchange[route.get(i)] == 1) && (i != 0) && (i != length-1)){

    			// if interchangeable station, then check if the path has an interchange
    			// for this, check if the lineColor of previous and next station is same or not. If not, it is an interchange
    			if(stationColorCode[route.get(i-1)] != stationColorCode[route.get(i+1)]){

    				// It is an interchange
    				// Increment time by 1 min.
    				time++;
    			}
    		}
    	}

    	//return the time
    	return time;
	}

	public float calculateCrowd(ArrayList<Integer> route, int length) {
		// variable for crowd count.
		float crowd = 0.0f;

		// a loop to go through the crowd values of all the stations in the current route and sum them up.
		for(int i = 0; i < length; i++){
			crowd += crowdWeight[route.get(i)];
		}

		// return the total summed up crowd.
		return crowd;
	}

	public int getTimeEfficientRouteIndex(ArrayList <ArrayList <Integer> > allroutes, int n) {
		
		int[] time = new int[n];
		for(int i = 0; i < n; i++){
			time[i] = calculateTime(allroutes.get(i), allroutes.get(i).size());
			Log.d("Time","Vlaue of time is "+ time);
		}
		int minInd = 0;
		for(int i = 1; i < n; i++)
			if(time[i] < time[minInd])
				minInd = i;

		return minInd;
	}

	public int getCrowdEfficientRouteIndex(ArrayList <ArrayList <Integer> > allroutes, int n) {
		
		float[] crowd = new float[n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < allroutes.get(i).size(); j++){
				crowd[i] += crowdWeight[allroutes.get(i).get(j)];
			}
		}
		int minInd = 0;
		for(int i = 1; i < n; i++)
			if(crowd[i] < crowd[minInd])
				minInd = i;

		return minInd;
	}


	public String getTime(){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+5:30"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm:ss");
        date.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        String localTime = date.format(currentLocalTime);

        return localTime;
    }

}
