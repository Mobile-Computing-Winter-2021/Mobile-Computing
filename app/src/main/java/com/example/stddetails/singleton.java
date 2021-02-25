package com.example.stddetails;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

public class singleton {
    private static singleton ss;

    private ArrayList<list> slist=new ArrayList<list>();
;
    public static singleton get(Context context) {
        if (ss == null) {
            ss = new singleton(context);
        }

        return ss;
    }

    private singleton(Context context) {



    }

    public ArrayList<list> getSlist() {
        return slist;
    }

    public void setSlist(ArrayList<list> slist) {
        this.slist = slist;
    }
}


