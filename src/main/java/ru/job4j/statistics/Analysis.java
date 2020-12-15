package ru.job4j.statistics;

import java.util.*;
import java.util.stream.Collectors;

public class Analysis {
    public Info diff(List<User> previous, List<User> current) {
        int changed = 0;
        Info result = new Info();
        Map<Integer, String> currentMap;
        currentMap = convertListToMap(current);
        List<User> listTmp = new ArrayList<>(current);
        listTmp.removeAll(previous);
        result.setAdded(listTmp.size());

        listTmp.clear();
        listTmp.addAll(previous);
        listTmp.removeAll(current);
        result.setDeleted(listTmp.size());

        listTmp.clear();
        listTmp.addAll(previous);
        listTmp.retainAll(current);
        for (User user : listTmp) {
            var id = user.getId();
            var name = user.getName();
            if (!name.equals(currentMap.get(id))) {
                changed++;
            }
        }
        result.setChanged(changed);
        return result;
    }

    private Map<Integer, String> convertListToMap(List<User> listUser) {
       return listUser.stream()
               .collect(Collectors.toMap(User::getId, User::getName));
    }

    public static class User {
        private int id;
        private String name;

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

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof User)) {
                return false;
            }
            User user = (User) o;
            return getId() == user.getId();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId());
        }
    }

    public static class Info {
        private int added;
        private int changed;
        private int deleted;

        public int getAdded() {
            return added;
        }

        public void setAdded(int added) {
            this.added = added;
        }

        public int getChanged() {
            return changed;
        }

        public void setChanged(int changed) {
            this.changed = changed;
        }

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }
    }
}
