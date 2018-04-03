package com.tado.android.client;

import android.app.Activity;
import android.net.Network;
import android.os.Build.VERSION;
import com.google.gson.JsonSyntaxException;
import com.tado.android.requests.Request;
import com.tado.android.responses.Response;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.Util;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LocalAPICall extends APICall {
    private static final String TAG = LocalAPICall.class.getCanonicalName();
    protected Network network;

    class C07371 extends Response {
        C07371() {
        }

        public void parse(String stream) {
        }
    }

    public LocalAPICall(Request request, Activity context) {
        super(request, context);
    }

    public LocalAPICall(Request request, Network network, Activity context) {
        super(request, context);
        this.network = network;
    }

    protected Response doInBackground(Void... params) {
        boolean exception;
        if (this.network != null && !Util.isNetworkAvailable(this.network)) {
            return null;
        }
        if (!Util.isNetworkAvailable()) {
            return null;
        }
        byte[] requestData = getRequest().toBytes();
        try {
            URL url = new URL(getRequest().getUrl());
            String bla = "";
            if (requestData != null) {
                try {
                    bla = new String(requestData, HttpRequest.CHARSET_UTF8);
                } catch (UnsupportedEncodingException e2) {
                    Snitcher.start().toCrashlytics().logException(TAG, e2);
                }
            }
            try {
                HttpURLConnection connection;
                Response response;
                if (this.network != null) {
                    connection = (HttpURLConnection) this.network.openConnection(url);
                } else {
                    connection = (HttpURLConnection) url.openConnection();
                }
                Snitcher.start().log(3, TAG, "open connection", new Object[0]);
                Snitcher.start().log(6, TAG, url.toString(), new Object[0]);
                connection.setRequestMethod(getRequest().getHttpMethod());
                String referer = getRequest().getReferer();
                if (referer != null) {
                    connection.setRequestProperty(HttpRequest.HEADER_REFERER, referer);
                }
                boolean z = getRequest().getHttpMethod().equals(HttpRequest.METHOD_POST) || getRequest().getHttpMethod().equals(HttpRequest.METHOD_PUT);
                connection.setDoOutput(z);
                connection.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, getRequest().getContentType());
                if (requestData != null) {
                    connection.setFixedLengthStreamingMode(requestData.length);
                }
                if (VERSION.SDK != null && VERSION.SDK_INT > 13) {
                    connection.setRequestProperty("Connection", "close");
                }
                OutputStream requestStream = null;
                try {
                    if (getRequest().getHttpMethod().equals(HttpRequest.METHOD_POST) || getRequest().getHttpMethod().equals(HttpRequest.METHOD_PUT)) {
                        try {
                            requestStream = connection.getOutputStream();
                            requestStream.write(requestData);
                            requestStream.close();
                            if (requestStream != null) {
                                requestStream.close();
                            }
                            if (false) {
                                if (connection == null) {
                                    return null;
                                }
                                connection.disconnect();
                                return null;
                            }
                        } catch (IOException e1) {
                            exception = true;
                            Snitcher.start().toCrashlytics().logException(TAG, e1);
                            if (requestStream != null) {
                                try {
                                    requestStream.close();
                                } catch (IOException e) {
                                    Snitcher.start().log(6, TAG, "finally " + e.getStackTrace().toString() + " " + e.getLocalizedMessage(), new Object[0]);
                                    e.printStackTrace();
                                    Snitcher.start().log(6, TAG, "Throwable open output stream " + e.getStackTrace().toString() + " " + e.getLocalizedMessage(), new Object[0]);
                                }
                            }
                            if (1 != null) {
                                if (connection == null) {
                                    return null;
                                }
                                connection.disconnect();
                                return null;
                            }
                        } catch (Throwable th) {
                            if (requestStream != null) {
                                try {
                                    requestStream.close();
                                } catch (IOException e3) {
                                    Snitcher.start().log(6, TAG, "finally " + e3.getStackTrace().toString() + " " + e3.getLocalizedMessage(), new Object[0]);
                                    e3.printStackTrace();
                                    Snitcher.start().log(6, TAG, "Throwable open output stream " + e3.getStackTrace().toString() + " " + e3.getLocalizedMessage(), new Object[0]);
                                }
                            }
                            if (exception) {
                                if (connection == null) {
                                    return null;
                                }
                                connection.disconnect();
                                return null;
                            }
                        }
                    }
                } catch (IOException e32) {
                    Snitcher.start().log(6, TAG, "finally " + e32.getStackTrace().toString() + " " + e32.getLocalizedMessage(), new Object[0]);
                    e32.printStackTrace();
                    Snitcher.start().log(6, TAG, "Throwable open output stream " + e32.getStackTrace().toString() + " " + e32.getLocalizedMessage(), new Object[0]);
                } catch (Throwable th2) {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
                int statusCode = 0;
                try {
                    statusCode = connection.getResponseCode();
                } catch (IOException e322) {
                    Snitcher.start().toCrashlytics().logException(TAG, e322);
                    try {
                        statusCode = connection.getResponseCode();
                    } catch (IOException e4) {
                        response = new C07371();
                        response.setStatusCode(statusCode);
                        if (connection == null) {
                            return response;
                        }
                        connection.disconnect();
                        return response;
                    }
                }
                InputStream responseStream = null;
                if (statusCode >= 400) {
                    try {
                        responseStream = connection.getErrorStream();
                    } catch (IOException e3222) {
                        Snitcher.start().toCrashlytics().logException(TAG, e3222);
                        if (responseStream != null) {
                            try {
                                responseStream.close();
                            } catch (IOException e32222) {
                                Snitcher.start().toCrashlytics().logException(TAG, e32222);
                            }
                        }
                        if (connection != null) {
                            connection.disconnect();
                        }
                        return null;
                    } catch (Throwable th3) {
                        if (responseStream != null) {
                            try {
                                responseStream.close();
                            } catch (IOException e322222) {
                                Snitcher.start().toCrashlytics().logException(TAG, e322222);
                            }
                        }
                    }
                } else {
                    responseStream = connection.getInputStream();
                }
                response = getRequest().createResponse();
                response.setStatusCode(statusCode);
                response.setUrl(getRequest().getUrl());
                if (responseStream != null) {
                    try {
                        response.parse(APICall.convertStreamToString(responseStream));
                    } catch (NullPointerException e5) {
                        response.setSuccess(false);
                        Snitcher.start().toCrashlytics().logException(TAG, e5);
                    } catch (JsonSyntaxException e6) {
                        response.setSuccess(false);
                        Snitcher.start().toCrashlytics().logException(TAG, e6);
                    } catch (IllegalStateException e7) {
                        response.setSuccess(false);
                        Snitcher.start().toCrashlytics().logException(TAG, e7);
                    }
                    try {
                        responseStream.close();
                    } catch (IOException e3222222) {
                        Snitcher.start().toCrashlytics().logException(TAG, e3222222);
                        e3222222.printStackTrace();
                    }
                } else {
                    response.setSuccess(false);
                }
                if (responseStream != null) {
                    try {
                        responseStream.close();
                    } catch (IOException e32222222) {
                        Snitcher.start().toCrashlytics().logException(TAG, e32222222);
                    }
                }
                if (connection == null) {
                    return response;
                }
                connection.disconnect();
                return response;
            } catch (IOException e12) {
                Snitcher.start().toCrashlytics().logException(TAG, e12);
                return null;
            }
        } catch (MalformedURLException e13) {
            Snitcher.start().toCrashlytics().logException(TAG, e13);
            return null;
        }
    }
}
