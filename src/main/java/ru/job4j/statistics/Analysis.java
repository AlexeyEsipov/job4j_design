package ru.job4j.statistics;

import java.util.*;

public class Analysis {
    public Info diff(List<User> previous, List<User> current) {
        int changed = 0;
        int added;
        int deleted = 0;
        int exist = 0;
        HashMap<Integer, String> previousMap;
        HashMap<Integer, String> currentMap;
        currentMap = convertListToMap(current);
        previousMap = convertListToMap(previous);
        for (int id: previousMap.keySet()) {
            if (!currentMap.containsKey(id)) {
                deleted++;
                continue;
            }
            if (currentMap.containsKey(id)) {
                if (currentMap.get(id).equals(previousMap.get(id))) {
                    exist++;
                } else {
                    changed++;
                }
            }
        }
        added = currentMap.size() - exist - changed;
        return new Info(added, changed, deleted);
    }

    private HashMap<Integer, String> convertListToMap(List<User> listUser) {
       HashMap<Integer, String> result = new HashMap<>();
        for (User user: listUser) {
            result.put(user.getId(), user.getName());
        }
        return result;
    }

    public static class User {
        private final int id;
        private final String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public static class Info {
        private final int added;
        private final int changed;
        private final int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }
    }
}
