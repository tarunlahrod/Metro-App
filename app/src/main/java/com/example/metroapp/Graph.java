package com.example.metroapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


// A directed graph using
// adjacency list representation
public class Graph {

    // No. of vertices in graph
    private int v;
    public static ArrayList <ArrayList <Integer>> allpathList = new ArrayList <ArrayList<Integer>>();


    // adjacency list
    private ArrayList<Integer>[] adjList;

    // path
    private ArrayList<Integer> path;


    //Constructor
    public Graph(int vertices){

        //initialise vertex count
        this.v = vertices;

        // initialise adjacency list
        initAdjList();
    }

    // utility method to initialise
    // adjacency list
    @SuppressWarnings("unchecked")
    private void initAdjList()
    {
        adjList = new ArrayList[v];

        for(int i = 0; i < v; i++)
        {
            adjList[i] = new ArrayList<>();
        }
    }

    // add edge from u to v
    public void addEdge(int u, int v)
    {
        // Add v to u's list.
        adjList[u].add(v);
    }

    // Prints all paths from
    // 's' to 'd'
    public void printAllPaths(int s, int d)
    {
        boolean[] isVisited = new boolean[v];
        ArrayList<Integer> pathList = new ArrayList<>();

        //add source to path[]
        pathList.add(s);

        //Call recursive utility
        printAllPathsUtil(s, d, isVisited, pathList);
    }

    // A recursive function to print
    // all paths from 'u' to 'd'.
    // isVisited[] keeps track of
    // vertices in current path.
    // localPathList<> stores actual
    // vertices in the current path
    private void printAllPathsUtil(Integer u, Integer d,
                                   boolean[] isVisited,
                                   List<Integer> localPathList) {

        // Mark the current node
        isVisited[u] = true;

        if (u.equals(d))
        {
            this.path = new ArrayList<Integer>(localPathList);
            noteThePath(this.path);
            // if match found then no need to traverse more till depth
            isVisited[u] = false;
            return ;
        }

        // Recur for all the vertices
        // adjacent to current vertex
        for (Integer i : adjList[u])
        {
            if (!isVisited[i])
            {
                // store current node
                // in path[] fs
                localPathList.add(i);
                printAllPathsUtil(i, d, isVisited, localPathList);

                // remove current node
                // in path[]
                localPathList.remove(i);
            }
        }

        // Mark the current node
        isVisited[u] = false;
    }

    public void noteThePath(ArrayList <Integer> path){
        this.allpathList.add(path);

        Log.d("route2", "Route is : "+ allpathList);
    }

    public ArrayList<ArrayList<Integer>> getAllPaths(){
        return this.allpathList;
    }

//
//    // Driver program
//    public static void main(String[] args)
//    {
//        // Create a sample graph
//
//        String[] stationNameList = new String[]{"Netaji Subhash Place", "Shalimar Bagh", "Azadpur", "Model Town", "G.T.B. Nagar", "Vishwavidyalaya", "Vidhan Sabha", "Civil Lines", "Kashmere Gate", "Tis Hazari", "Pulbangash", "Pratap Nagar", "Shastri Nagar", "Inderlok", "Kanhaiya Nagar", "Keshav Puram", "Chandni Chowk", "Chawri Bazar", "New Delhi", "Rajiv Chowk", "RK Ashram Marg", "Jhandewalan", "Karol Bagh", "Rajendra Place", "Patel Nagar", "Shadipur", "Kirti Nagar", "Satguru Ram Singh Marg", "Ashok Park Main", "Moti Nagar", "Ramesh Nagar", "Rajouri Garden", "ESI - Basaidarapur", "Punjabi Bagh (W)", "Shakurpur"};
//        int[] stationNodeList = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34};
//
//
//        Graph metroMap = new Graph(35);
//        addEdgesToMetroMap(metroMap);
//        showPath(metroMap, "Kashmere Gate", "Vishwavidyalaya", stationNameList, stationNodeList);
//
//    }
//
//    public static int search(String[] stationNameList, String s){
//        int index=0;
//        for(int i=0; i<stationNameList.length; i++)
//            if(s == stationNameList[i])
//                index = i;
//        return index;
//    }
//
//
//    public static void addEdgesToMetroMap(Graph metroMap){
//
//        // Netaji Subhash Place
//        metroMap.addEdge(0, 34);
//        metroMap.addEdge(0, 15);
//        metroMap.addEdge(0, 1);
//
//        // Shalimar Bagh
//        metroMap.addEdge(1, 0);
//        metroMap.addEdge(1, 2);
//
//        // Azadpur
//        metroMap.addEdge(2, 1);
//        metroMap.addEdge(2, 3);
//
//        // Model Town
//        metroMap.addEdge(3, 2);
//        metroMap.addEdge(3, 4);
//
//        // G.T.B. Nagar
//        metroMap.addEdge(4, 3);
//        metroMap.addEdge(4, 5);
//
//        // Vishwavidyalaya
//        metroMap.addEdge(5, 4);
//        metroMap.addEdge(5, 6);
//
//        // Vidhan Sabha
//        metroMap.addEdge(6, 5);
//        metroMap.addEdge(6, 7);
//
//        // Civil Lines
//        metroMap.addEdge(7, 6);
//        metroMap.addEdge(7, 8);
//
//        // Kashmere Gate
//        metroMap.addEdge(8, 7);
//        metroMap.addEdge(8, 9);
//
//        // Tis Hazari
//        metroMap.addEdge(9, 8);
//        metroMap.addEdge(9, 10);
//
//        // Pulbangash
//        metroMap.addEdge(10, 9);
//        metroMap.addEdge(10, 11);
//
//        // Pratap Nagar
//        metroMap.addEdge(11, 10);
//        metroMap.addEdge(11, 12);
//
//        // Shastri Nagar
//        metroMap.addEdge(12, 11);
//        metroMap.addEdge(12, 13);
//
//        // Inderlok
//        metroMap.addEdge(13, 12);
//        metroMap.addEdge(13, 14);
//        metroMap.addEdge(13, 28);
//
//        // Kanhaiya Nagar
//        metroMap.addEdge(14, 13);
//        metroMap.addEdge(14, 15);
//
//        // Keshav Puram
//        metroMap.addEdge(15, 14);
//        metroMap.addEdge(15, 0);
//
//        // Chandni Chowk
//        metroMap.addEdge(16, 8);
//        metroMap.addEdge(16, 17);
//
//        // Chawri Bazar
//        metroMap.addEdge(17, 16);
//        metroMap.addEdge(17, 18);
//
//        // New Delhi
//        metroMap.addEdge(18, 17);
//        metroMap.addEdge(18, 19);
//
//        // Rajiv Chowk
//        metroMap.addEdge(19, 18);
//        metroMap.addEdge(19, 20);
//
//        // RK Ashram Marg
//        metroMap.addEdge(20, 19);
//        metroMap.addEdge(20, 21);
//
//        // Jhandewalan
//        metroMap.addEdge(21, 20);
//        metroMap.addEdge(21, 22);
//
//        // Karol Bagh
//        metroMap.addEdge(22, 21);
//        metroMap.addEdge(22, 23);
//
//        // Rajendra Place
//        metroMap.addEdge(23, 22);
//        metroMap.addEdge(23, 24);
//
//        // Patel Nagar
//        metroMap.addEdge(24, 23);
//        metroMap.addEdge(24, 25);
//
//        // Shadipur
//        metroMap.addEdge(25, 24);
//        metroMap.addEdge(25, 26);
//
//        // Kirti Nagar
//        metroMap.addEdge(26, 25);
//        metroMap.addEdge(26, 27);
//
//        // Satguru Ram Singh Marg
//        metroMap.addEdge(27, 26);
//        metroMap.addEdge(27, 28);
//
//        // Ashok Park Main
//        metroMap.addEdge(28, 27);
//        metroMap.addEdge(28, 29);
//
//        // Moti Nagar
//        metroMap.addEdge(29, 26);
//        metroMap.addEdge(29, 30);
//
//        // Ramesh Nagar
//        metroMap.addEdge(30, 29);
//        metroMap.addEdge(30, 31);
//
//        // Rajouri Garden
//        metroMap.addEdge(31, 30);
//        metroMap.addEdge(31, 32);
//
//        // ESI - Basaidarapur
//        metroMap.addEdge(32, 31);
//        metroMap.addEdge(32, 33);
//
//        // Punjabi Bagh (W)
//        metroMap.addEdge(33, 32);
//        metroMap.addEdge(33, 34);
//
//        // Shakurpur
//        metroMap.addEdge(34, 33);
//        metroMap.addEdge(34, 0);
//
//    }
//
//    public static void showPath(Graph metroMap, String source, String destination, String[] stationNameList, int[] stationNodeList) {
//        int src, dest;
//        src = search(stationNameList, source);
//        dest = search( stationNameList, destination);
//
//        System.out.println("src: " + src);
//        System.out.println("dest: " + dest);
//
//        // use something like: metroMap.get(position).getStationGraphNode;
//        metroMap.printAllPaths(src, dest);
//        ArrayList <ArrayList <Integer> > allRouteArrayList = new ArrayList <ArrayList<Integer>>();
//
//        allRouteArrayList = metroMap.getAllPaths();
//
//
//
//        // say, selecting the first path only
//        ArrayList<Integer> routeArrayList = new ArrayList<>();
//        for(int j=0; j<allRouteArrayList.size(); j++){
//            System.out.println("\n\nPath #"+(j+1));
//            routeArrayList = allRouteArrayList.get(j);
//
//
//            int routeLength = routeArrayList.size();
//
//            // a string array of stations in route
//            String[] routeNameArray = new  String[routeLength];
//            int[] routeNodeArray = new int[routeLength];
//
//            routeNameArray = nodeToStation(stationNameList, routeArrayList, routeLength);
//            routeNodeArray = nodeArrayListToNodeArray(stationNodeList, routeArrayList, routeLength);
//
//            // for(int i=0; i<routeNameArray.length; i++){
//            // 	System.out.println("\t"+routeNameArray[i]);
//            // }
//        }
//
//
//        // passing strings to intent
//        Intent intent = new Intent(getApplicationContext(), RouteActivity.class);
//        intent.putExtra("nameList", routeNameArray);
//        intent.putExtra("nodeList", routeNodeArray);
//        startActivity(intent);
//
//    }
//
//    public static String[] nodeToStation(String stationNameList[], ArrayList<Integer> route, int length){
//        String[] stationName = new String[length];
//        for(int i = 0; i < length; i++){
//            stationName[i] = stationNameList[route.get(i)];
//        }
//        return stationName;
//    }
//
//    public static int[] nodeArrayListToNodeArray(int stationNodeList[], ArrayList<Integer> route, int length){
//        int[] stationNode = new int[length];
//        for(int i=0; i<length; i++){
//            stationNode[i] = route.get(i);
//        }
//        return stationNode;
//    }
}