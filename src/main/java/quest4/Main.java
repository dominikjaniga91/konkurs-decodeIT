package quest4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<Set<String>> listOfIp = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String line = br.readLine();
                if (isNullOrEmpty(line)) {
                    return;
                }
                String[] addresses = line.split(" ");
                String ip1 = addresses[1];
                String ip2 = addresses[2];
                int index1 = doesIPExist(listOfIp, ip1);
                int index2 = doesIPExist(listOfIp, ip2);

                if (line.startsWith("B")) {
                    if(index1 > -1 && index2 == -1) {
                        listOfIp.get(index1).add(ip2);
                    } else if (index1 == -1 && index2 > -1) {
                        listOfIp.get(index2).add(ip1);
                    } else if (index1 == -1 && index2 == -1) {
                        addIPsToNewSet(listOfIp, ip1, ip2);
                    } else {
                        mergeSetsOfIP(listOfIp, index1, index2);
                    }

                } else {
                    printResult(index1, index2);
                }
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private static void mergeSetsOfIP(List<Set<String>> listOfIp, int index1, int index2) {
        if (index1 != index2) {
            listOfIp.get(index1).addAll(listOfIp.get(index2));
            listOfIp.remove(index2);
        }
    }

    private static void addIPsToNewSet(List<Set<String>> listOfIp, String ip1, String ip2) {
        Set<String> newSet = new HashSet<>();
        newSet.add(ip1);
        newSet.add(ip2);
        listOfIp.add(newSet);
    }

    private static void printResult(int index1, int index2) {
        if (index2 != -1 && (index1 == index2)) {
            System.out.println("T");
        } else {
            System.out.println("N");
        }
    }

    private static int doesIPExist(List<Set<String>> listOfIp, String ip){

        for (Set<String> list : listOfIp) {
            if (list.contains(ip)){
                return listOfIp.indexOf(list);
            }
        }
        return -1;
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0 || str.replaceAll("\\s+", "").length() == 0;
    }
}


