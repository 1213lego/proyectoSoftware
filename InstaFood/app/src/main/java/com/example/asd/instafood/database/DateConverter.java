package com.example.asd.instafood.database;

import android.arch.persistence.room.TypeConverter;

import java.sql.Date;

public class DateConverter
{

    @TypeConverter
    public static Date toDate(Long value)
    {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static long fromDate(Date date)
    {
        return date == null ? null : date.getTime();
    }
}