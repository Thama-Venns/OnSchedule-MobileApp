package com.app.onschedule;

import org.json.JSONException;
import org.json.JSONObject;

public class Listing {

    private JSONObject object;

    public void writeJSON() {
        try {
            object.put("dateTime", "Date Time");
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }

        System.err.println(object);
    }

}
