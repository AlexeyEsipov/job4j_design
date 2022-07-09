package ru.job4j.mailmerge;

import java.util.*;

public class MergeSetUser {

    public ArrayList<User> clearDuplicatesFromHashMap(ListUser sourceUser) {
        ArrayList<User> whiteUser = new ArrayList<>();
        ArrayList<Mail> listEmails = new ArrayList<>();
        LinkedHashMap<String, String> mapEmails = new LinkedHashMap<>();
        LinkedHashMap<String, LinkedHashSet<String>> mapUserMails = new LinkedHashMap<>();
        LinkedHashMap<String, LinkedHashSet<String>> mapResult = new LinkedHashMap<>();

        convertUserMailsToListToMap(sourceUser, listEmails, mapUserMails);

        merge(listEmails, mapEmails, mapUserMails, mapResult);

        mapUserMails.clear();
        mapUserMails.putAll(mapResult);
        mapResult.clear();
        listEmails.clear();
        mapEmails.clear();

        convertMapUserMailsToListEmails(mapUserMails, listEmails);

        merge(listEmails, mapEmails, mapUserMails, mapResult);
        Set<Map.Entry<String, LinkedHashSet<String>>> set = mapResult.entrySet();
        for (Map.Entry<String, LinkedHashSet<String>> el: set) {
            String name = el.getKey();
            LinkedHashSet<String> setMails = el.getValue();
            whiteUser.add(new User(name, setMails));
        }
        return whiteUser;
    }

    private void merge(ArrayList<Mail> listEmails,
                       LinkedHashMap<String, String> mapEmails,
                       LinkedHashMap<String, LinkedHashSet<String>> mapUserMails,
                       LinkedHashMap<String, LinkedHashSet<String>> mapResult) {
        boolean combine = false;
        String oldName = listEmails.get(0).getUserName();
        String destName = oldName;
        for (int i = 0; i < listEmails.size(); i++) {
            Mail mail = listEmails.get(i);
            String email = mail.getMail();
            String name = mail.getUserName();
            if (!name.equals(oldName))  {
                if (combine) {
                    unionSet(mapUserMails, mapResult, oldName, destName);
                } else {
                    mapResult.put(oldName, mapUserMails.get(oldName));
                }
                oldName = name;
                combine = false;
            }
            if (mapEmails.containsKey(email)) {
                combine = true;
                destName = mapEmails.get(email);
            } else {
                mapEmails.put(email, name);
            }
            if ((i + 1) == listEmails.size()) {
                if (combine) {
                    unionSet(mapUserMails, mapResult, oldName, destName);
                } else {
                    mapResult.put(oldName, mapUserMails.get(oldName));
                }
            }
        }
    }

    /*временная сложность метода m (общее количество емайлов)*/

    private void unionSet(LinkedHashMap<String, LinkedHashSet<String>> sourceMap,
                          LinkedHashMap<String, LinkedHashSet<String>> destMap,
                          String oldName, String destName) {
        LinkedHashSet<String> sourceSet =
                new LinkedHashSet<>(sourceMap.get(oldName));
        LinkedHashSet<String> destSet =
                new LinkedHashSet<>(destMap.get(destName));
        destSet.addAll(sourceSet);
        destMap.replace(destName, destSet);
    }

    /*временная сложность метода m (общее количество емайлов)*/

    private void convertMapUserMailsToListEmails(LinkedHashMap<String,
            LinkedHashSet<String>> sourceMap, ArrayList<Mail> destList) {
        Set<Map.Entry<String, LinkedHashSet<String>>> set = sourceMap.entrySet();
        for (Map.Entry<String, LinkedHashSet<String>> el: set) {
            String name = el.getKey();
            LinkedHashSet<String> setMails = el.getValue();
            for (String email: setMails) {
                destList.add(0, new Mail(email, name));
            }
        }
    }

    /*временная сложность метода m (общее количество емайлов)*/

    private void convertUserMailsToListToMap(ListUser sourceUser, ArrayList<Mail> listUser,
                                             LinkedHashMap<String, LinkedHashSet<String>> mapUser) {
        for (int i = 0; i < sourceUser.size(); i++) {
            User user = sourceUser.getUser(i);
            mapUser.put(user.getName(), new LinkedHashSet<>(user.getMailUser()));
            String nameUser = user.getName();
            LinkedHashSet<String> mailSetUser = user.getMailUser();
            for (String mail : mailSetUser) {
                listUser.add(new Mail(mail, nameUser));
            }
        }
    }
}