package quest5;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        var scanner = new Scanner(System.in);
        var users = provideNetworkUsers(scanner);
        provideUsersConnections(scanner, users);
        var possibilities = getPossibilitiesOfUsersToTakeOver(users);
        var takeOverUsers = getTakeOverUsers(users, possibilities);

        System.out.println(takeOverUsers.size());
        printArray(takeOverUsers);
        printCost(takeOverUsers);
    }

    private static void printCost(Set<User> takeOverUsers) {
       int cost =  takeOverUsers.stream().map(User::getCost).reduce(0, Integer::sum);
       System.out.println(cost);
    }

    private static void provideUsersConnections(Scanner scanner, List<User> users) {

        int connections = scanner.nextInt();
        scanner.nextLine();

        int j = 0;
        while (j < connections) {
            String[] data = scanner.nextLine().split(" ");
            User user = getUser(users, data[0]);
            User friend = getUser(users, data[1]);
            user.addFriends(friend);
            j++;
        }
    }

    private static List<User> provideNetworkUsers(Scanner scanner) {

        List<User> users = new LinkedList<>();
        int numberOfUsers = scanner.nextInt();
        scanner.nextLine();
        int i = 0;

        while (i < numberOfUsers) {
            String[] data = scanner.nextLine().split(" ");
            users.add(new User(data[0], Integer.parseInt(data[1])));
            i++;
        }

        return users;
    }

    private static Set<User> getTakeOverUsers(List<User> users, Map<Set<User>, Integer> possibilities) {

        int min = 1000000;
        Set<User> resultSet = new HashSet<>();
        for (var set : possibilities.entrySet()) {
            if (set.getValue() < min && checkIfNetworkIsTakeOver(set.getKey(), users.size())) {
                min = set.getValue();
                resultSet = set.getKey();
            }
        }
        return resultSet;
    }

    private static Map<Set<User>, Integer> getPossibilitiesOfUsersToTakeOver(List<User> users) {

        Map<Set<User>, Integer> possibilities = new HashMap<>();
        for (User user : users) {
            for (User friend : users) {

                if(!user.equals(friend) && !user.friends.contains(friend)){
                    Set<User> option = new HashSet<>();
                    option.add(user);
                    option.add(friend);
                    possibilities.put(option, user.cost + friend.cost);
                }
            }
        }
        return possibilities;
    }

    private static User getUser(List<User> users, String name){
        User foundedUser = null;
        for (User user : users){
           if (user.name.equals(name)) {
               foundedUser = user;
           }
        }
        return Objects.requireNonNull(foundedUser, "User is null");
    }

    private static void printArray(Collection<User> elements){
        elements.forEach(user -> System.out.println(user.name));
    }

    private static boolean checkIfNetworkIsTakeOver(Set<User> users, int networkSize) {

        Set<User> checkingArray = new HashSet<>();

        for (User user : users) {
            checkingArray.add(user);
            checkingArray.addAll(user.friends);
        }
        return checkingArray.size() == networkSize;
    }

    private static class User{

        private String name;
        private int cost;
        private List<User> friends = new ArrayList<>();

        private User(String name, int cost) {
            this.name = name;
            this.cost = cost;
        }

        public int getCost() {
            return cost;
        }

        private void addFriends(User friend){
            friends.add(friend);
            friend.friends.add(this);
        }
    }
}


