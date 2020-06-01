/*
Author: Liam O'Shea
File: main.java
Description: This program builds a graph data structure based on input given in an external text file.
The program builds and displays an adjacency matrix representing the graph, and then performs and displays the output of
a breadth first search.
 */


import java.util.Scanner;
import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] args) throws IOException {

        //Declare variables
        String v1, v2;
        int v1Int, v2Int, vertex, numVertices;

        //Create file for input, instantiate file scanner
        File graphInput = new File("input.txt");
        Scanner graph = new Scanner(graphInput);

        //Get number of graph vertices, create array to represent graph, create result and queue lists.
        numVertices = graph.nextInt();
        int [][] adjMatrix = new int[numVertices][numVertices];
        ArrayList<Integer> result = new ArrayList<Integer>();
        LinkedList<Integer> q = new LinkedList<Integer>();

        //Grab edges and add them to adjacency matrix
        //Stop when we run out of input
        while(graph.hasNext()){
            v1 = graph.next();
            v2 = graph.next();

            //Convert vertex characters to correct indices for the adjacency array
            //A = 0, B = 1, C = 2, and so on..
            v1Int = v1.charAt(0)-65;
            v2Int = v2.charAt(0)-65;

            //Fill in adjacency array
            adjMatrix[v1Int][v2Int] = 1;
            adjMatrix[v2Int][v1Int] = 1;
        }

        //Display adjacency matrix
        System.out.println("Adjacency Matrix:");
        for(int i = 0; i < numVertices; i++){
            for(int j = 0; j < numVertices; j++){
                System.out.print(adjMatrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();


        //Breadth First Search**********

        //Enqueue first vertex (Picked A as first vertex)
        q.add(0);  //0 is value for A

        while(!q.isEmpty()){
            //Dequeue vertex from queue and add to result list
            vertex = q.removeFirst();
            result.add(vertex);

            //Enqueue neighbors of vertex
            for(int j = 0; j < numVertices; j++){
                //Detect neighbors of vertex
                if(adjMatrix[vertex][j] == 1){
                    //Add neighbor to queue if not already in queue or result list
                    if(!result.contains(j) && !q.contains(j)){
                        q.addLast(j);
                    }
                }
            }
        }

        //Display output
        System.out.println("Breadth first traversal:");
        while(!result.isEmpty()){
            char ch = (char)(result.remove(0) + 65);
            System.out.print(ch + " ");
        }
    }
}