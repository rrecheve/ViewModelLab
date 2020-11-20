package es.unex.giiis.asee.executorslab.data.roomdb;

import androidx.room.TypeConverter;
import es.unex.giiis.asee.executorslab.data.model.License;

public class LicenseConverter {
    @TypeConverter
    public static License str2License(String license){
        return license == null ? null : new License(license);
    }
    @TypeConverter
    public static String license2Str(License license){
        return license == null ? null : license.getKey() ;
    }
}
