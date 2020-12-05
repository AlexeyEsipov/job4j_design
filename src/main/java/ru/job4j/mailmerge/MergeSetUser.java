package ru.job4j.mailmerge;

import java.util.*;

public class MergeSetUser {

    private void merge(int i, int j, SetUser setUser) {
        var set = new LinkedHashSet<>(setUser.getUser(i).getMailUser());
        var set1 = new LinkedHashSet<>(setUser.getUser(j).getMailUser());
        set.retainAll(set1);
        if (!set.isEmpty()) {
            setUser.getUser(j).getMailUser().addAll(setUser.getUser(i).getMailUser());
            setUser.getUser(i).getMailUser().clear();
        }
    }

    public ArrayList<User> clearDuplicates(SetUser sourceUser) {
        ArrayList<User> whiteUser = new ArrayList<>();
        var setUser = new SetUser();
        for (int i = 0; i < sourceUser.size(); i++) {
            setUser.addUser(sourceUser.getUser(i));
        }
        for (int j = 0; j < setUser.size() - 1; j++) {
            if (!setUser.getUser(j).getMailUser().isEmpty()) {
                for (int i = j; i < setUser.size() - 1; i++) {
                    if (!setUser.getUser(i + 1).getMailUser().isEmpty()) {
                        merge(i + 1, j, setUser);
                    }
                }
                for (int i = setUser.size() - 1; i > j; i--) {
                    if (!setUser.getUser(i).getMailUser().isEmpty()) {
                        merge(i, j, setUser);
                    }
                }
            }
        }
        for (int i = 0; i < setUser.size(); i++) {
            if (!setUser.getUser(i).getMailUser().isEmpty()) {
                whiteUser.add(setUser.getUser(i));
// для отладки вывод результата
//                System.out.print(whiteUser.get(whiteUser.size() - 1).getName() + " -> ");
//                System.out.println(whiteUser.get(whiteUser.size() - 1).getMailUser());
            }
        }
        return whiteUser;
    }
}