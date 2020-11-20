package es.unex.giiis.asee.executorslab.data.roomdb;

import es.unex.giiis.asee.executorslab.data.model.Owner;

import androidx.room.TypeConverter;

public class OwnerConverter {
    @TypeConverter
    public static String OwnerToUsername(Owner owner){
        return owner == null ? null : owner.getLogin().toLowerCase();
    }
    @TypeConverter
    public static Owner UsernameToOwner(String username){
        return username == null ? null : new Owner(username);
    }
}
