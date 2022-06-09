package com.joseph_ridgway.inputoutput;

import java.io.*;
import java.util.*;

public class Locations implements Map<Integer, Location> {

    private static Map<Integer, Location> locations = new HashMap<>();

    public static void main(String[] args) throws IOException {

        try(DataOutputStream locFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))) {
            for(Location location : locations.values()) {
                locFile.writeInt(location.getLocationID());
                locFile.writeUTF(location.getDescription());
                System.out.println("Writing location " + location.getLocationID() + " : " + location.getDescription());
                System.out.println("Writing " + (location.getExits().size() - 1) + " exits");
                locFile.writeInt(location.getExits().size() - 1);
                for(String direction : location.getExits().keySet()) {
                    if(!direction.equalsIgnoreCase("Q")) {
                        System.out.println("\t\t" + direction + "," + location.getExits().get(direction));
                        locFile.writeUTF(direction);
                        locFile.writeInt(location.getExits().get(direction));
                    }
                }
             }
        }
//        try(FileWriter localFile = new FileWriter("locations_big.txt");
//        FileWriter dirFile = new FileWriter("directions_big.txt")) {
//            for(Location location : locations.values()) {
//                localFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
//                for(String direction : location.getExits().keySet()) {
//                    dirFile.write(location.getLocationID() + "," + direction + "," + location.getExits().get(direction) + "\n");
//                }
//            }
//        }    localFile = new FileWriter("locations.txt");
////            for (Location location : l
//        FileWriter localFile = null;
//        try {
//        ocations.values()) {
//                localFile.write(location.getLocationID() + ", " + location.getDescription() + "\n");
//                throw new IOException("Test exception thrown while writing.");
//            }
//        } finally {
//            System.out.println("In finally block");
//            if(localFile != null) {
//                localFile.close();
//                }
//        }

    }

    static {

        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader("locations_big.txt"));
            scanner.useDelimiter(",");
            while(scanner.hasNextLine()) {
                int loc = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String description = scanner.nextLine();
                System.out.println("Imported loc: " + loc + ": " + description);
                Map<String, Integer> tempExit = new HashMap<>();
                locations.put(loc, new Location(loc, description, tempExit));
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(scanner != null)
                scanner.close();
        }
        //Now read the exits.
        try {
            scanner = new Scanner(new BufferedReader(new FileReader("directions_big.txt")));
            scanner.useDelimiter(",");
            while(scanner.hasNextLine()) {
                int loc = scanner.nextInt();
                String direction = scanner.next();
                scanner.skip(scanner.delimiter());
                String dest = scanner.nextLine();
                int destination = Integer.parseInt(dest);
                System.out.println(loc + ": " + direction + ": " + destination);
                Location location = locations.get(loc);
                location.addExit(direction, destination);
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(scanner != null)
                scanner.close();
        }
//        locations.put(0, new Location(0, "You are sitting in front of a computer learning Java"));
//        locations.put(1, new Location(1, "You are standing at the end of a road before a small brick building"));
//        locations.put(2, new Location(2, "You are at the top of a hill"));
//        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring"));
//        locations.put(4, new Location(4, "You are in a valley beside a stream"));
//        locations.put(5, new Location(5, "You are in the forest"));
//
//        locations.get(1).addExit("W", 2);
//        locations.get(1).addExit("E", 3);
//        locations.get(1).addExit("S", 4);
//        locations.get(1).addExit("N", 5);
//
//        locations.get(2).addExit("N", 5);
//
//        locations.get(3).addExit("W", 1);
//
//        locations.get(4).addExit("N", 1);
//        locations.get(4).addExit("W", 2);
//
//        locations.get(5).addExit("S", 1);
//        locations.get(5).addExit("W", 2);
    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object o) {
        return locations.containsKey(o);
    }

    @Override
    public boolean containsValue(Object o) {
        return locations.containsValue(o);
    }

    @Override
    public Location get(Object o) {
        return locations.get(o);
    }

    @Override
    public Location put(Integer integer, Location location) {
        return locations.put(integer, location);
    }

    @Override
    public Location remove(Object o) {
        return locations.remove(o);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> map) {

    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
