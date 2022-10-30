// Omer Arviv 205967847

package test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Graph {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    ArrayList<Edge> visitedEdges;
    public int[][] findMinSpanTree (Vertex[] verts, Edge[] edges){
        int[][] arr = new int[verts.length][verts.length];
        int[][] end = new int[verts.length][verts.length];
        int i,j,temp=999;

        for(Edge e:edges){           // Initializing the edges into the tree
            if(e.dest.getVerNum()==e.source.getVerNum())
                System.out.println("Cannot have same source and destination in edge");
                arr[e.dest.getVerNum()][e.source.getVerNum()]=e.weight;
                arr[e.source.getVerNum()][e.dest.getVerNum()]=e.weight;
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(ANSI_GREEN+"Omer Arviv 205967847"+ANSI_RESET);
        System.out.println();
        System.out.println("Hi,");
        System.out.println("Little explanation to understand the graph:");
        System.out.println("I displayed the tree by a matrix of ints. Every Row and Col represent a vertex.");
        System.out.println("In every cell in the array, if there is 0 - it means there is no edge between these 2 vertexes.");
        System.out.println("But if there is a number - it means there is an edge between this 2 vertexes, and its weight its the number.");
        System.out.println();
        System.out.println(ANSI_CYAN+"The starting tree we randomized:");
        System.out.println();

        System.out.print("\t\t "+ANSI_RED);
        for(i=0;i<verts.length;i++)
            if(i<10)
                System.out.print(i+"  ");
            else
                System.out.print(i+" ");
        System.out.println();
        System.out.println();

        for(i=0;i< verts.length;i++) {
            for (j = 0; j < verts.length; j++) {
                if(j==0&&i<10) {
                    System.out.print(ANSI_RED + "\t " + i + "   " + ANSI_RESET);
                }
                else if(j==0)
                    System.out.print(ANSI_RED+"\t"+i+"   "+ANSI_RESET);
                if(i==j)
                    System.out.print(ANSI_RED+arr[i][j]+"  "+ANSI_RESET);
                else
                    System.out.print(arr[i][j] + "  ");
            }
            System.out.println();
        }

        int start = pickRandomStartVertex(verts);
        ArrayList<Integer> visited=new ArrayList<>();
        visited.add(start);
        int index1=-1,index2=-1;
        visitedEdges=new ArrayList<>();

        while(visited.size()<verts.length){
            for(i=0;i<verts.length;i++) {
                if (visited.contains(i)) {
                    for (j = 0; j < verts.length; j++) {
                        if(!visited.contains(j)) {
                            if (arr[i][j] != 0 && arr[i][j] < temp) {
                                temp = arr[i][j];
                                index1 = i;
                                index2 = j;
                            }
                        }
                    }
                }
            }

            end[index1][index2]=arr[index1][index2];
            end[index2][index1]=arr[index1][index2];
            arr[index1][index2]=0;
            arr[index2][index1]=0;
            visited.add(index2);
            visitedEdges.add(new Edge (new Vertex(index1),new Vertex(index2),arr[index1][index2]));
            temp=999;
        }
        System.out.println();
        System.out.println(ANSI_CYAN+"The minimal spanning tree:");
        System.out.println();

        System.out.print("\t\t "+ANSI_RED);
        for(i=0;i<verts.length;i++)
            if(i<10)
                System.out.print(i+"  ");
            else
                System.out.print(i+" ");
        System.out.println();
        System.out.println();

        for(i=0;i< verts.length;i++) {
            for (j = 0; j < verts.length; j++) {
                if(j==0&&i<10)
                    System.out.print(ANSI_RED+"\t "+i+"   "+ANSI_RESET);
                else if(j==0)
                    System.out.print(ANSI_RED+"\t"+i+"   "+ANSI_RESET);
                if(i==j)
                    System.out.print(ANSI_RED+end[i][j]+"  "+ANSI_RESET);
                else
                    System.out.print(end[i][j] + "  ");
            }
            System.out.println();
        }

        return end;
    }

    public int pickRandomStartVertex (Vertex[] verArr){
        Random rand= new Random();
        int temp = (int)rand.nextInt(verArr.length);
        return temp;
    }

    public int[][] NewEdgeToTree (int[][] arr, Edge edge){
        if( arr[edge.source.getVerNum()][edge.dest.getVerNum()]!=0||arr[edge.dest.getVerNum()][edge.source.getVerNum()]!=0) {
            System.out.println("Edge already exist in this tree");
        }

        int[] fathers=new int[arr.length];
        for(int i=0;i<fathers.length;i++)
            fathers[i]=-1;

        BFS(arr,fathers,edge.source.getVerNum());

        visitedEdges.add(edge);
        arr[edge.source.getVerNum()][edge.dest.getVerNum()]=edge.weight;
        arr[edge.dest.getVerNum()][edge.source.getVerNum()]=edge.weight;
        ArrayList<Edge> newvisited=new ArrayList<>();
        int b,a;
        newvisited.add(edge);

        b=edge.dest.getVerNum();
        a=fathers[b];
        while (a!=-1) {
            newvisited.add(new Edge(new Vertex(a), new Vertex(b), arr[a][b]));
            b = a;
            a = fathers[a];
        }

        a=0;
        Edge tempEdge=edge;
        for(Edge e:newvisited){
            if(e.weight>a) {
                a = e.weight;
                tempEdge=e;
            }
       }
        arr[tempEdge.source.getVerNum()][tempEdge.dest.getVerNum()]=0;
        arr[tempEdge.dest.getVerNum()][tempEdge.source.getVerNum()]=0;
        newvisited.remove(tempEdge);

        int i,j;
        System.out.print("\t\t "+ANSI_RED);
        for(i=0;i<arr.length;i++)
            if(i<10)
                System.out.print(i+"  ");
            else
                System.out.print(i+" ");
        System.out.println();
        System.out.println();

        for(i=0;i< arr.length;i++) {
            for (j = 0; j < arr.length; j++) {
                if(j==0&&i<10)
                    System.out.print(ANSI_RED+"\t "+i+"   "+ANSI_RESET);
                else if(j==0)
                    System.out.print(ANSI_RED+"\t"+i+"   "+ANSI_RESET);
                if(i==j)
                    System.out.print(ANSI_RED+arr[i][j]+"  "+ANSI_RESET);
                else
                    System.out.print(arr[i][j] + "  ");
            }
            System.out.println();
        }

        return arr;
    }

    void BFS(int[][]arr,int[] fathers,int start){

        ArrayList<Integer> q = new ArrayList<>();
        q.add(start);
        ArrayList<Integer> visited=new ArrayList<>();
        visited.add(start);

        while(!q.isEmpty()){
            for(int i=0;i<arr.length;i++){
                if(arr[q.get(0)][i]!=0 && !visited.contains(i)){
                    q.add(i);
                    fathers[i]=q.get(0);
                    visited.add(i);
                }
            }
            q.remove(0);
        }
        int temp =0;
    }
}
