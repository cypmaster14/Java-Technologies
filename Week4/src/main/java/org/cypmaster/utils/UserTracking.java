package org.cypmaster.utils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Ciprian at 11/4/2017
 */
public class UserTracking {

    private final static Map<String, Set<String>> USERS_ON_PAGES = new ConcurrentHashMap<>();

    public static void userEnteredPage(String page, String sessionId) {
        System.out.printf("Entering Page:%s\n", page);
        Set<String> usersSessions = USERS_ON_PAGES.getOrDefault(page, new HashSet<>());
        usersSessions.add(sessionId);
        USERS_ON_PAGES.put(page, usersSessions);
    }

    public static void userLeavedPage(String page, String sessionId) {
        System.out.printf("Leaving Page:%s\n", page);
        Set<String> usersSessions = USERS_ON_PAGES.get(page);
        usersSessions.remove(sessionId);
        USERS_ON_PAGES.put(page, usersSessions);
    }

    public static Set<String> getNumberOfUsersOnPage(String page) {
        return USERS_ON_PAGES.getOrDefault(page, new HashSet<>());
    }

}
