package com.moretolearn.model;

import java.util.GregorianCalendar;

public record Cricketer(Integer id, String name, GregorianCalendar calendar, Group group) {
}