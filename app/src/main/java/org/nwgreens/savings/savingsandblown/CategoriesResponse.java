package org.nwgreens.savings.savingsandblown;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

/**
 * Created by root on 1/30/16.
 */
public class CategoriesResponse extends ArrayList<CategoriesResponse.Category> {

    private static Context applicationContext;


     public static void GetCategories(Context context, String bankName, Response.Listener<CategoriesResponse> listener){
        applicationContext = context;
        RequestQueue rq = MySingleton.getInstance(applicationContext).getRequestQueue();

        GsonRequest <CategoriesResponse> request = new GsonRequest<>(Request.Method.GET,
                CategoriesResponse.class, String.format("%scategories/%s", context.getResources().getText(R.string.url),bankName),
                listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(applicationContext, "Failed to Get Categories", Toast.LENGTH_LONG).show();
            }
        });

        rq.add(request);


    }

    public String[] GetCategoryString() {
        String [] returnValue = new String[this.size()];
        for(int i = 0; i < this.size(); i++){
            returnValue[i] = this.get(i).getName();
        }
        return returnValue;
    }

    public class Category{
        private String name;

        public String getName() {
            return name;
        }
    }

}
