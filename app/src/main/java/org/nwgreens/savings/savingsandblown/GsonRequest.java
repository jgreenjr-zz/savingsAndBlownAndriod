package org.nwgreens.savings.savingsandblown;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by root on 11/18/15.
 */
public class GsonRequest<T> extends Request<T> {

    private final Class<T> clazz;
    private final Response.Listener<T> listener;

    /**
     * Creates a new request with the given method.
     *
     * @param method the request {@link Method} to use
     * @param clazz  the class
     * @param url URL to fetch the string at
     * @param listener Listener to receive the String response
     * @param errorListener Error listener, or null to ignore errors
     */
    public GsonRequest(int method, Class<T> clazz, String url, Response.Listener<T> listener, Response.ErrorListener errorListener){

        super(method, url, errorListener);
        this.clazz = clazz;
        this.listener = listener;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String data = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            Gson gson = new Gson();
            T returnValue = gson.fromJson(data, clazz);
            return Response.success(returnValue, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }
}
